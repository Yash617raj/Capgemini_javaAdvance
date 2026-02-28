package com.cap.service;

import com.cap.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(Long id);
    void addUser(User user);
}
