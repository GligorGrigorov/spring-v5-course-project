package com.meals.mealsapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KitchenController {

    @GetMapping("/kitchen")
    public String getKitchenPage(Model model) {
        return "kitchen";
    }
}
