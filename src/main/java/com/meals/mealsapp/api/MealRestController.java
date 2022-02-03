package com.meals.mealsapp.api;

import com.meals.mealsapp.entity.Meal;
import com.meals.mealsapp.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MealRestController {
    @Autowired
    private MealService mealService;

    @PostMapping("/meals")
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        Meal created = mealService.createMeal(meal);

        return ResponseEntity.created(ServletUriComponentsBuilder
                                     .fromCurrentRequest()
                                     .pathSegment("{id}")
                                     .build(created.getId()))
                             .body(created);
    }

    @GetMapping("/meals")
    public List<Meal> getMeals() {
        return mealService.findAll();
    }
}
