package com.company.service;

import com.company.models.PizzaOrderStatus;
import com.company.repository.PizzaOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaOrderStatusService {
    @Autowired
    PizzaOrderStatusRepository pizzaOrderStatusRepository;
    public List<PizzaOrderStatus> findAll() {return pizzaOrderStatusRepository.findAll();}
    public PizzaOrderStatus getById(Long id) {return pizzaOrderStatusRepository.getById(id);}
    public PizzaOrderStatus getByName(String name) {return pizzaOrderStatusRepository.findByName(name);}
    public void save(PizzaOrderStatus pizzaOrderStatus) {pizzaOrderStatusRepository.save(pizzaOrderStatus);}
    public void deleteById(Long id) {pizzaOrderStatusRepository.deleteById(id);}
}
