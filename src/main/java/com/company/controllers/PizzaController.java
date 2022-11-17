package com.company.controllers;

import com.company.models.Pizza;
import com.company.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/pizzas")
    public String index(Model model){
        model.addAttribute("pizzas", pizzaService.findAll().toArray());
        return "pizzas/index";
    }

    @GetMapping("/pizzas/details")
    public String details(@RequestParam("id") Long id, Model model){
        model.addAttribute("pizza",pizzaService.getById(id));
        return "pizzas/details";
    }

    @GetMapping("/pizzas/create")
    public String details(){
        return "pizzas/create";
    }

    @PostMapping("/pizzas/create")
    public String subjectsCreatePost(@RequestParam("name") String name,
                                     @RequestParam("description") String description,
                                     @RequestParam("price") int price,
                                     @RequestParam("massa") int massa,
                                     @RequestParam("cookingTime") int cookingTime){
        Pizza pizza = new Pizza();
        pizza.setName(name);
        pizza.setDescription(description);
        pizza.setPrice(price);
        pizza.setMassa(massa);
        pizza.setCookingTime(cookingTime);
        pizza.setAvatarUrl("../imgs/pizza.png");
        pizzaService.save(pizza);
        return "redirect://localhost:8080/pizzas/";
    }

    @GetMapping("/pizzas/edit")
    public String subjectsEditGET(@RequestParam("id") Long id, Model model){
        model.addAttribute("pizza",pizzaService.getById(id));
        return "pizzas/edit";
    }

    @PostMapping("/pizzas/edit")
    public String subjectsEditPost(@RequestParam("id") Long id, @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("price") int price,
                                   @RequestParam("massa") int massa,
                                   @RequestParam("cookingTime") int cookingTime){
        Pizza pizza = pizzaService.getById(id);
        pizza.setName(name);
        pizza.setDescription(description);
        pizza.setPrice(price);
        pizza.setMassa(massa);
        pizza.setCookingTime(cookingTime);

        pizzaService.save(pizza);

        return "redirect://localhost:8080/pizzas/";
    }

}
