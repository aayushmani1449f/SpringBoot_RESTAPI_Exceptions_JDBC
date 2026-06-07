package com.example.springcore.service;

import com.example.springcore.component.MyPrototypeBean;
import com.example.springcore.dto.UserDTO;
import com.example.springcore.exception.ResourceNotFoundException;
import com.example.springcore.model.User;
import com.example.springcore.repository.UserJdbcRepository;
import com.example.springcore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserJdbcRepository userJdbcRepository;
    private final ObjectProvider<MyPrototypeBean> prototypeBeanProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, 
                           UserJdbcRepository userJdbcRepository,
                           ObjectProvider<MyPrototypeBean> prototypeBeanProvider) {
        this.userRepository = userRepository;
        this.userJdbcRepository = userJdbcRepository;
        this.prototypeBeanProvider = prototypeBeanProvider;
    }

    @Override
    @Transactional
    public User createUser(UserDTO userDTO) {
        log.info("Creating a new user with email: {}", userDTO.getEmail());
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserDTO userDTO) {
        log.info("Updating user with id: {}", id);
        User user = getUserById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getUsersByAgeGreaterThan(int age) {
        log.info("Fetching users with age greater than {} using JDBC Template", age);
        return userJdbcRepository.findAllUsersByAgeGreaterThan(age);
    }

    @Override
    public String getPrototypeBeanId() {
        MyPrototypeBean bean = prototypeBeanProvider.getObject();
        String id = bean.getInstanceId();
        log.info("Fetched new prototype bean with instance ID: {}", id);
        return id;
    }
}
