package com.zeeroam.controller;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeeroam.entity.RefreshToken;
import com.zeeroam.entity.User;
import com.zeeroam.repository.RefreshTokenRepository;
import com.zeeroam.request.LoginRequest;
import com.zeeroam.service.UserService;
import com.zeeroam.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
    	System.out.println("Hii");
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());
        
        // Check if the user already has a refresh token
        System.out.println(userDetails.getUsername());
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUsername(userDetails.getUsername());

        if (existingToken.isPresent()) {
            // Update existing token
            RefreshToken refreshTokenEntity = existingToken.get();
            refreshTokenEntity.setToken(refreshToken);
            refreshTokenEntity.setExpiryDate(new Date(System.currentTimeMillis()  + 30L * 24 * 60 * 60 * 1000)); // 30 days
            refreshTokenRepository.save(refreshTokenEntity);
        } else {
            // Save new refresh token
            refreshTokenRepository.save(new RefreshToken(userDetails.getUsername(), refreshToken, new Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000)));
        }

        System.out.println("New Refresh Token: " + refreshToken);
        System.out.println("Generated Refresh Token Expiry: " + new Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000));

        return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
    }

    
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        try {
        	System.out.println(refreshToken);
            String username = jwtUtil.extractUsername(refreshToken);

            Optional<RefreshToken> storedToken = refreshTokenRepository.findByUsername(username);
            if (storedToken.isEmpty() || !storedToken.get().getToken().equals(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
            }
            
            // Check if the refresh token is expired
            if (storedToken.get().getExpiryDate().before(new Date())) {
                refreshTokenRepository.delete(storedToken.get()); // Remove expired token
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh Token Expired. Please log in again.");
            }

            String newAccessToken = jwtUtil.generateAccessToken(userDetailsService.loadUserByUsername(username));
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));

        } catch (ExpiredJwtException e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh Token Expired. Please log in again.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
        }
    }
    
    @PostMapping("/logout")
    public String logout() {
        // Clear authentication from SecurityContext
        SecurityContextHolder.clearContext();
        
        // Return a response to inform the user that logout was successful
        return "Logged out successfully";
    }

}
