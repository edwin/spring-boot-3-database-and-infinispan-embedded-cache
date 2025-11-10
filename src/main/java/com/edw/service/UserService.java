package com.edw.service;

import com.edw.bean.User;
import com.edw.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;
import java.util.stream.Collectors;


import java.util.List;

/**
 * <pre>
 *  com.edw.service.UserService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 10 Nov 2025 9:16
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "user-cache", key = "#name")
    public User getUser(String name) {
        return userRepository.findById(name).orElse(null);
    }

    @Cacheable(value = "user-cache", key = "#root.methodName")
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @CachePut(value = "user-cache", key = "#user.name")
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (userRepository.existsById(user.getName())) {
            throw new IllegalArgumentException("User with name '" + user.getName() + "' already exist");
        }

        return userRepository.save(user);
    }

    @Transactional
    @CachePut(value = "user-cache", key = "#user.name")
    public User updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (!userRepository.existsById(user.getName())) {
            throw new IllegalArgumentException("User with name '" + user.getName() + "' does not exist");
        }

        return userRepository.save(user);
    }

    @Transactional
    @CacheEvict(value = "user-cache", key = "#name")
    public void deleteUser(String name) {
        if (!userRepository.existsById(name)) {
            throw new IllegalArgumentException("User with name '" + name + "' does not exist");
        }

        userRepository.deleteById(name);
    }

}
