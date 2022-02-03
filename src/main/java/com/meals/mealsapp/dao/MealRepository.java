package com.meals.mealsapp.dao;

import com.meals.mealsapp.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByName(String name);
}
