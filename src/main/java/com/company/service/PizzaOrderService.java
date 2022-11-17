package com.company.service;

import com.company.models.PizzaOrder;
import com.company.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PizzaOrderService {
    @Autowired
    PizzaOrderRepository pizzaOrderRepository;
    public List<PizzaOrder> findAll() {return pizzaOrderRepository.findAll();}
    public PizzaOrder getById(Long id) {return pizzaOrderRepository.getById(id);}
    public void save(PizzaOrder pizzaOrder) {pizzaOrderRepository.save(pizzaOrder);}
    public void deleteById(Long id) {pizzaOrderRepository.deleteById(id);}
}
