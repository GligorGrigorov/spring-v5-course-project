package com.meals.mealsapp.web;

import com.meals.mealsapp.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @GetMapping("/kitchen")
    public String getKitchenPage(Model model) {
        model.addAttribute("orders", kitchenService.findAll());

        return "kitchen";
    }
}
