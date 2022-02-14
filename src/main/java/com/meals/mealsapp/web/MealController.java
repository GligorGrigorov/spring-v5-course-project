package com.meals.mealsapp.web;

import com.meals.mealsapp.entity.Meal;
import com.meals.mealsapp.entity.User;
import com.meals.mealsapp.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
public class MealController {
    private final String UPLOAD_DIR = "./src/main/resources/static/uploads/";

    @Autowired
    private MealService mealService;

    @GetMapping("/meals")
    public String mealsPage(Model model) {
        model.addAttribute("name", "hello");
        model.addAttribute("list", mealService.findAll());
        return "meals";
    }

    @GetMapping("/add-meal")
    public String addMeal(Model model) {
        model.addAttribute("meal", new Meal());
        return "add-meal";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("orders", mealService.findOrdersForUser(((User) auth.getPrincipal()).getUsername()));

        return "orders";
    }

    @PostMapping("/create-meal")
    public String addMeal(@RequestParam("file") MultipartFile file, @ModelAttribute("meal") Meal meal) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        meal.setImgPath(fileName);
        mealService.createMeal(meal);
        return "redirect:meals";
    }
}
