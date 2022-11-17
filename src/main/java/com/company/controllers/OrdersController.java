package com.company.controllers;

import com.company.models.*;
import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class OrdersController {

    private final UserService userService;

    private final OrderService orderService;

    private final PizzaOrderService pizzaOrderService;

    private final PizzaService pizzaService;

    private final OrderStatusService orderStatusService;

    private final PizzaOrderStatusService pizzaOrderStatusService;

    @Autowired
    public OrdersController(UserService userService, OrderService orderService, PizzaOrderService pizzaOrderService,
                            PizzaService pizzaService, OrderStatusService orderStatusService,
                            PizzaOrderStatusService pizzaOrderStatusService) {
        this.userService = userService;
        this.orderService = orderService;
        this.pizzaOrderService = pizzaOrderService;
        this.pizzaService = pizzaService;
        this.orderStatusService = orderStatusService;
        this.pizzaOrderStatusService = pizzaOrderStatusService;
    }

    @GetMapping("/orders")
    public String orders(Model model){
        model.addAttribute("orders",orderService.findAll());
        return "orders/index";
    }

    @GetMapping("/activeOrders")
    public String activeOrders(Model model){
        model.addAttribute("orders",orderService.findAll().stream().filter(x->x.getStatus().getName().equals("Відкрите") || x.getStatus().getName().equals("Прийняте")).collect(Collectors.toList()));
        return "orders/index";
    }

    @GetMapping("/myOrders")
    public String myOrders(Model model){
        model.addAttribute("orders",orderService.findAll().stream().filter(x->x.getUser().equals(userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()))).collect(Collectors.toList()));
        return "orders/index";
    }

    @GetMapping("/orders/bucket")
    public String bucket(Model model){
        List<Order> orders = orderService.findAll().stream().filter(x->x.getUser().equals(userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())) && x.getStatus().getName().equals("Нове")).collect(Collectors.toList());

        if(orders.size()!=0){
            model.addAttribute("pizzaOrders",orders.get(0).getPizzaOrders());
        }

        else{
            model.addAttribute("pizzaOrders",new ArrayList<PizzaOrder>());
        }

        return "orders/bucket";
    }

    @GetMapping("/orders/addToBucket")
    public String stop(@RequestParam("id") Long id, Model model){

        List<Order> orders = orderService.findAll().stream().filter(x->x.getUser().equals(userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())) && x.getStatus().getName().equals("Нове")).collect(Collectors.toList());

        if(orders.size() != 0){
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setOrder(orders.get(0));
            pizzaOrder.setPizza(pizzaService.getById(id));
            pizzaOrder.setAddingDate(new Date(new java.util.Date().getTime()));
            pizzaOrder.setCount(1);
            pizzaOrder.setStatus(pizzaOrderStatusService.getByName("У черзі"));
            pizzaOrderService.save(pizzaOrder);
        }

        else{

            Order order = new Order();
            order.setUser(userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
            order.setStatus(orderStatusService.getByName("Нове"));
            order.setOrderDate(new Date(new java.util.Date().getTime()));

            orderService.save(order);

            List<Order> orderUser = orderService.findAll().stream().filter(x->x.getUser().equals(userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())) && x.getStatus().getName().equals("Нове")).collect(Collectors.toList());

            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setOrder(orderUser.get(0));
            pizzaOrder.setPizza(pizzaService.getById(id));
            pizzaOrder.setAddingDate(new Date(new java.util.Date().getTime()));
            pizzaOrder.setStatus(pizzaOrderStatusService.getByName("У черзі"));
            pizzaOrder.setCount(1);
            pizzaOrderService.save(pizzaOrder);

        }

        model.addAttribute("pizzas", pizzaService.findAll().toArray());

        return "pizzas/index";
    }

    @GetMapping("/orders/deleteFromBucket")
    public String deleteFromBucket(@RequestParam("id") Long id, Model model){
        pizzaOrderService.deleteById(id);

        List<Order> orders = orderService.findAll().stream().filter(x->x.getUser().equals(userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())) && x.getStatus().getName().equals("Нове")).collect(Collectors.toList());

        if(orders.size()!=0){
            model.addAttribute("pizzaOrders",orders.get(0).getPizzaOrders());
        }

        else{
            model.addAttribute("pizzaOrders",new ArrayList<PizzaOrder>());
        }

        return "orders/bucket";
    }

    @GetMapping("/orders/start")
    public String start(Model model){
        List<Order> orders = orderService.findAll().stream().filter(x->x.getUser().equals(userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())) && x.getStatus().getName().equals("Нове")).collect(Collectors.toList());
        Order order = orderService.getById(orders.get(0).getId());
        order.setStatus(orderStatusService.getByName("Відкрите"));
        orderService.save(order);
        model.addAttribute("pizzas", pizzaService.findAll().toArray());
        return "pizzas/index";
    }

    @GetMapping("/orders/details")
    public String details(@RequestParam("id") Long id, Model model){
        model.addAttribute("order",orderService.getById(id));
        return "orders/details";
    }

    @GetMapping("/orders/startPrepare")
    public String startPrepare(@RequestParam("id") Long id, Model model){
        PizzaOrder pizzaOrder = pizzaOrderService.getById(id);
        pizzaOrder.setStatus(pizzaOrderStatusService.getByName("Готується"));
        pizzaOrderService.save(pizzaOrder);

        Order order = orderService.getById(pizzaOrder.getOrder().getId());
        order.setStatus(orderStatusService.getByName("Прийняте"));
        orderService.save(order);

        model.addAttribute("order",orderService.getById(pizzaOrder.getOrder().getId()));
        return "orders/details";
    }

    @GetMapping("/orders/finishPrepare")
    public String finishPrepare(@RequestParam("id") Long id, Model model){
        PizzaOrder pizzaOrder = pizzaOrderService.getById(id);
        pizzaOrder.setStatus(pizzaOrderStatusService.getByName("Готова"));
        pizzaOrderService.save(pizzaOrder);

        List<PizzaOrder> pizzaOrders = pizzaOrderService.findAll().stream().filter(x-> Objects.equals(x.getOrder().getId(), pizzaOrder.getOrder().getId())).collect(Collectors.toList());
        List<PizzaOrder> pizzaOrdersReady = pizzaOrderService.findAll().stream().filter(x-> Objects.equals(x.getOrder().getId(), pizzaOrder.getOrder().getId()) && x.getStatus().getName().equals("Готова")).collect(Collectors.toList());

        if(pizzaOrders.size() == pizzaOrdersReady.size()){
            Order order = orderService.getById(pizzaOrder.getOrder().getId());
            order.setStatus(orderStatusService.getByName("Завершене"));
            orderService.save(order);
        }

        model.addAttribute("order",orderService.getById(pizzaOrder.getOrder().getId()));
        return "orders/details";
    }
}
