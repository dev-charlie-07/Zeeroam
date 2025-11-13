//package com.zeeroam.controller;
//
//import com.zeeroam.entity.User;
//import com.zeeroam.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//    	System.out.println("Hii");
//        User savedUser = userService.registerUser(user);
//        return ResponseEntity.ok(savedUser);
//    }
//}
