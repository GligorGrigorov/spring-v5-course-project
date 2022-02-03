package com.meals.mealsapp.dao;

import com.meals.mealsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
