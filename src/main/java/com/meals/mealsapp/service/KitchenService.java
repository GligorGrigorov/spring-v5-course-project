package com.meals.mealsapp.service;

import com.meals.mealsapp.dao.OrderRepository;
import com.meals.mealsapp.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {
    @Autowired
    private OrderRepository orderRepository;

    public void submitOrder(Order order) {
        orderRepository.saveAndFlush(order);
    }

    public List<Order> findAll() {
        return  orderRepository.findAll();
    }
}
