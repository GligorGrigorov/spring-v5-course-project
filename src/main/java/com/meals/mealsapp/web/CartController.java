package com.meals.mealsapp.web;

import com.meals.mealsapp.service.CartService;
import com.meals.mealsapp.service.KitchenService;
import com.meals.mealsapp.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class CartController {
    private CartService cartService;
    private MealService mealService;
    private final KitchenService kitchenService;

    @Autowired
    public CartController(CartService cartService, MealService mealService, KitchenService kitchenService) {
        this.cartService = cartService;
        this.mealService = mealService;
        this.kitchenService = kitchenService;
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("products", cartService.getProductsInCart());
        model.addAttribute("total",cartService.getTotal());
        return "cart";
    }

    @GetMapping("/cart/addMeal/{mealId}")
    public String addProductToCart(@PathVariable("mealId") String mealId, Model model) {
        cartService.addProduct(mealService.findById(mealId));

        return getCart(model);
    }

    @GetMapping("/cart/removeMeal/{mealId}")
    public String removeProductFromCart(@PathVariable("mealId") String mealId, Model model) {
        cartService.removeMeal(mealService.findById(mealId));
        return getCart(model);
    }

    @GetMapping("/cart/clear")
    public String removeProductFromCart(Model model) {
        cartService.clear();

        return getCart(model);
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model) {
        cartService.order();

        return getCart(model);
    }
}
