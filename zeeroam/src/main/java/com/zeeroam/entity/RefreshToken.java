package com.zeeroam.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
public class RefreshToken {
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String token;
    private Date expiryDate;

    public RefreshToken() {}

    public RefreshToken(String username, String token, Date expiryDate) {
        this.username = username;
        this.token = token;
        this.expiryDate = expiryDate;
    }
}