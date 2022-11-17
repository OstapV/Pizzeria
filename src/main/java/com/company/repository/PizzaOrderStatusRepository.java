package com.company.repository;

import com.company.models.PizzaOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaOrderStatusRepository extends JpaRepository<PizzaOrderStatus,Long> {
    PizzaOrderStatus findByName(String name);
}

