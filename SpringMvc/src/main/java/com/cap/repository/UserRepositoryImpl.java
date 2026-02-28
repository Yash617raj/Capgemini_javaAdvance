package com.cap.repository;

import com.cap.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private List<User> users = new ArrayList<>();
    public UserRepositoryImpl(){
        users.add(new User(1L,"John Doe","john@gmail.com"));
        users.add(new User(2L,"Jane smith","jane@gmail.com"));
    }

    @Override
    public List<User> findAll(){
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user){
        users.add(user);
    }
}
