package com.meals.mealsapp.web;

import com.meals.mealsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/profile")
    public String getAdminProfile() {
        return "admin-profile";
    }
    @GetMapping("/admin/users")
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin-users";
    }

    @GetMapping("/admin/remove/{id}")
    public String removeUser(@PathVariable("id") String id, Model model) {
        userService.delete(id);
        return getUsersPage(model);
    }
}
