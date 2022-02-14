package com.meals.mealsapp.service;

import com.meals.mealsapp.entity.Meal;
import com.meals.mealsapp.entity.Order;
import com.meals.mealsapp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@Slf4j
public class CartService {
    private final Authentication authentication;
    private final MealService mealService;
    private final KitchenService kitchenService;
    private final UserService userService;

    @Autowired
    public CartService(Authentication authentication, MealService mealService, KitchenService kitchenService, UserService userService) {
        this.kitchenService = kitchenService;
        this.mealService = mealService;
        this.authentication = authentication;
        this.userService = userService;
    }

    private final Map<String, Integer> products = new HashMap<>();

    public void addProduct(Meal meal) {
        if (products.containsKey(meal.getName())) {
            products.put(meal.getName(), products.get(meal.getName()) + 1);
        } else {
            products.put(meal.getName(), 1);
        }
    }

    public void clear() {
        products.clear();
    }

    public void removeMeal(Meal meal) {
        log.info("Meal name: " + meal.getName());

        for (String mealName :
                products.keySet()) {
            log.info(mealName);
        }

        if (products.containsKey(meal.getName()) && products.get(meal.getName()) > 1) {
            log.info("yes");
            log.info("Meal name: " + meal.getName());
            products.put(meal.getName(), products.get(meal.getName()) - 1);
        } else {
            products.remove(meal.getName());
        }
    }

    public Map<Meal, Integer> getProductsInCart() {
        Map<Meal, Integer> resultMap = new HashMap<>();
        products.keySet().stream().map(name -> mealService.findById(name)).forEach(meal ->
                resultMap.put(meal, products.get(meal.getName())));

        return Collections.unmodifiableMap(resultMap);
    }

    public BigDecimal getTotal() {
        //TODO implement
        return BigDecimal.ONE;
    }

    public void order() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        products.keySet().forEach(key -> {
            for (int i = 0; i < products.get(key); i++) {
                kitchenService.submitOrder(new Order(userService.findByUsername(((User)auth.getPrincipal()).getUsername()), mealService.findById(key)));
            }
        });
    }
}