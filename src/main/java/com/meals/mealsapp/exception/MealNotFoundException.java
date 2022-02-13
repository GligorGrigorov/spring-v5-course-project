package com.meals.mealsapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Meal with this ID does not exists.")
public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException() {
    }

    public MealNotFoundException(String message) {
        super(message);
    }

    public MealNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealNotFoundException(Throwable cause) {
        super(cause);
    }
}
