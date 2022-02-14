package com.meals.mealsapp.entity;

import com.meals.mealsapp.status.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_username")
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "meal_name")
    @NonNull
    private Meal meal;

    private OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

    private Timestamp orderedOn = Timestamp.from(Instant.now());
    private Timestamp preparedOn;
}
