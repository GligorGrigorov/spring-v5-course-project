package com.meals.mealsapp.init;

import com.meals.mealsapp.dao.UserRepository;
import com.meals.mealsapp.entity.User;
import com.meals.mealsapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findById("admin").isEmpty()) {
            log.info("Initialized admin user");
            User user = new User("admin", "admin","ADMIN");
            userService.addUser(user);
        }
    }
}
