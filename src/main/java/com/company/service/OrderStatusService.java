package com.company.service;

import com.company.models.OrderStatus;
import com.company.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {
    @Autowired
    OrderStatusRepository orderStatusRepository;
    public List<OrderStatus> findAll() {return orderStatusRepository.findAll();}
    public OrderStatus getById(Long id) {return orderStatusRepository.getById(id);}
    public OrderStatus getByName(String name) {return orderStatusRepository.findByName(name);}
    public void save(OrderStatus orderStatus) {orderStatusRepository.save(orderStatus);}
    public void deleteById(Long id) {orderStatusRepository.deleteById(id);}
}
