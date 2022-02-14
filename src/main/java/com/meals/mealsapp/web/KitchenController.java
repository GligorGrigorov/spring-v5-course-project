package com.meals.mealsapp.web;

import com.meals.mealsapp.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @GetMapping("/kitchen")
    public String getKitchenPage(Model model) {
        model.addAttribute("orders", kitchenService.findAll());

        return "kitchen";
    }

    @GetMapping("/kitchen/prepare/{id}")
    public String addProductToCart(@PathVariable("id") Long orderId, Model model) {
        kitchenService.prepare(orderId);

        return getKitchenPage(model);
    }
}
