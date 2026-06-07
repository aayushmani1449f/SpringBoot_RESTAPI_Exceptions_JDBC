package com.example.springcore.service;

import com.example.springcore.dto.UserDTO;
import com.example.springcore.model.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    List<User> getUsersByAgeGreaterThan(int age);
    String getPrototypeBeanId();
}
