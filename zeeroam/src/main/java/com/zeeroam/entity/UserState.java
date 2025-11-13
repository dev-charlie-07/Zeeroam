package com.zeeroam.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_state")
@Getter
@Setter
public class UserState {

    @Id
    private Long userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String state; // Store JSON string of user state

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Constructors
    public UserState() {}

    public UserState(Long userId, String state, Date updatedAt) {
        this.userId = userId;
        this.state = state;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
}