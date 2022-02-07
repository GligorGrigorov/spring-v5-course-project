package com.meals.mealsapp.web;

import com.meals.mealsapp.entity.Meal;
import com.meals.mealsapp.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MealController {

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

    @PostMapping("/create-meal")
    public String addMeal(@ModelAttribute("meal") Meal meal) {
        mealService.createMeal(meal);
        return "redirect:meals";
    }
}
