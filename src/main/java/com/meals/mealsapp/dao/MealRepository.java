package com.meals.mealsapp.dao;

import com.meals.mealsapp.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, String> {
    List<Meal> findByName(String name);
}
