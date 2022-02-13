package com.meals.mealsapp.web;

import com.meals.mealsapp.service.CartService;
import com.meals.mealsapp.service.KitchenService;
import com.meals.mealsapp.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
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
        model.addAttribute("products", cartService.getProductsInCart().keySet());

        return "cart";
    }

    @GetMapping("/cart/addMeal/{mealId}")
    public String addProductToCart(@PathVariable("mealId") Long mealId, Model model) {
        cartService.addProduct(mealService.findById(mealId));

        return getCart(model);
    }

    @GetMapping("/cart/removeMeal/{mealId}")
    public String removeProductFromCart(@PathVariable("mealId") Long mealId, Model model) {
        //TODO remove only one meal by id
        cartService.removeProduct();

        return getCart(model);
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model) {
        kitchenService.submitForPreparation(cartService.getProductsInCart());

        return getCart(model);
    }
}
