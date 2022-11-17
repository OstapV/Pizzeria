package com.company.service;

import com.company.models.Pizza;
import com.company.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> findAll() {return pizzaRepository.findAll();}
    public Pizza getById(Long id) {return pizzaRepository.getById(id);}
    public void save(Pizza order) {pizzaRepository.save(order);}
    public void deleteById(Long id) {pizzaRepository.deleteById(id);}
}
