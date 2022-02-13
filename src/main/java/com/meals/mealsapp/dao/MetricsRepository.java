package com.meals.mealsapp.dao;

import com.meals.mealsapp.entity.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsRepository extends JpaRepository<Metrics, Long> {
}
