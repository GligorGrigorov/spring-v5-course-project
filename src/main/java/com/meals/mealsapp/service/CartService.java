package com.meals.mealsapp.service;

import com.meals.mealsapp.entity.Meal;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
    private final Map<Meal, Integer> products = new HashMap<>();

    public void addProduct(Meal product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void removeProduct() {
        products.clear();
    }

    public Map<Meal, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    public void checkout() {
    }

    public BigDecimal getTotal() {
        //TODO implement
        return BigDecimal.ONE;
    }
}