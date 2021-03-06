package com.meals.mealsapp.service;

import com.meals.mealsapp.dao.UserRepository;
import com.meals.mealsapp.entity.User;
import com.meals.mealsapp.exception.UserAlreadyExistsException;
import com.meals.mealsapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return optionalUser.get();
    }

    public void addUser(User user) {
        if(userRepository.findById(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
