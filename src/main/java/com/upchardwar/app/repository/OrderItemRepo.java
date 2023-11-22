package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.pharma.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}