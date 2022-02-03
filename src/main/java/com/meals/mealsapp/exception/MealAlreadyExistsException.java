package com.meals.mealsapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Meal with this name already exists.")
public class MealAlreadyExistsException extends RuntimeException {
    public MealAlreadyExistsException() {
    }

    public MealAlreadyExistsException(String message) {
        super(message);
    }

    public MealAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
