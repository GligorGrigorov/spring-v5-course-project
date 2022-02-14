package com.meals.mealsapp.service;

import com.meals.mealsapp.dao.OrderRepository;
import com.meals.mealsapp.entity.Order;
import com.meals.mealsapp.exception.OrderNotFoundException;
import com.meals.mealsapp.status.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KitchenService {
    @Autowired
    private OrderRepository orderRepository;

    public void submitOrder(Order order) {
        orderRepository.saveAndFlush(order);
    }

    public List<Order> findAll() {
        return  orderRepository.findAll()
                               .stream()
                               .filter(order -> order.getOrderStatus() != OrderStatus.READY)
                               .collect(Collectors.toList());
    }

    public void prepare(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(optionalOrder.isEmpty()) {
            throw new OrderNotFoundException();
        }
        Order order = optionalOrder.get();
        order.setOrderStatus(OrderStatus.READY);

        orderRepository.saveAndFlush(order);
    }
}
