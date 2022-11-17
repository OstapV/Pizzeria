package com.company.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Pizza_Order_Status")
public class PizzaOrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="status")
    Set<PizzaOrder> pizzaOrders;

    public PizzaOrderStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PizzaOrder> getPizzaOrders() {
        return pizzaOrders;
    }

    public void setPizzaOrders(Set<PizzaOrder> pizzaOrders) {
        this.pizzaOrders = pizzaOrders;
    }
}
