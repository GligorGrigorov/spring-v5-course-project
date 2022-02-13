package com.meals.mealsapp.service;

import com.meals.mealsapp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(String username, String password) {
        log.info("Username " + username);
        log.info("Password " + password);
        User user = userService.findByUsername(username);

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
