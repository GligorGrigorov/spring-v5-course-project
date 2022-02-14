package com.meals.mealsapp.service;

import com.meals.mealsapp.dao.MealRepository;
import com.meals.mealsapp.dao.OrderRepository;
import com.meals.mealsapp.entity.Meal;
import com.meals.mealsapp.entity.Order;
import com.meals.mealsapp.exception.MealAlreadyExistsException;
import com.meals.mealsapp.exception.MealNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MealService {
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public MealService(MealRepository mealRepository, OrderRepository orderRepository) {
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
    }

    public Meal createMeal(Meal meal) {
        if (!mealRepository.findByName(meal.getName()).isEmpty()) {
            throw new MealAlreadyExistsException();
        }

        return mealRepository.saveAndFlush(meal);
    }

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public Meal findById(String mealId) {
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);

        if (optionalMeal.isEmpty()) {
            throw new MealNotFoundException();
        }

        return optionalMeal.get();
    }

    public List<Order> findOrdersForUser(String username) {
        return orderRepository.findAll()
                              .stream()
                              .filter(order -> Objects.equals(order.getUser()
                                                                   .getUsername(), username))
                              .collect(Collectors.toList());
    }
}
