package com.meals.mealsapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "meals")
public class Meal {
    @Id
    private String name;

    @NonNull
    private String description;

    private String imgPath;

    private int price;
}
