package com.charles.mocktrial.model;
import java.util.UUID;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity 
@Table(name = "users")
public class User {
    @Id 
    @GeneratedValue 
    private UUID id; 

    private String name;
    private String email;

    @Column(unique = true)
    private UUID authID;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setID(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getAuthID() {
        return authID;
    }

    public void setAuthId(UUID authId) {
        this.authID = authId;
    }
}
