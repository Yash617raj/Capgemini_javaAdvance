package com.example.LibraryManagement.Model;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

public class User {
    private Long id;

    @NotBlank(message = "Name field should not be empty")
    @Size(min=3,message = "Name should be min of 3 character")
    private String name;

    @Email(message = "Email is in wrong format")
    @NotBlank(message = "Email field cannot be empty")
    private String email;

    @Size(min=6, message = "Password should be min of 6 character")
    @NotBlank(message = "Password field cannot be empty")
    private String password;

    public User(){}

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
