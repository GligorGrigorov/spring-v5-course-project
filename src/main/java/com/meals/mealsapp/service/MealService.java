package com.meals.mealsapp.service;

import com.meals.mealsapp.dao.MealRepository;
import com.meals.mealsapp.entity.Meal;
import com.meals.mealsapp.exception.MealAlreadyExistsException;
import com.meals.mealsapp.exception.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;

    public Meal createMeal(Meal meal) {
        if (!mealRepository.findByName(meal.getName()).isEmpty()) {
            throw new MealAlreadyExistsException();
        }

        return mealRepository.saveAndFlush(meal);
    }

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public Meal findById(Long mealId) {
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);

        if (optionalMeal.isEmpty()) {
            throw new MealNotFoundException();
        }

        return optionalMeal.get();
    }
}
