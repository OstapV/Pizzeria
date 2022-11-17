package com.company.repository;

import com.company.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    OrderStatus findByName(String name);
}

