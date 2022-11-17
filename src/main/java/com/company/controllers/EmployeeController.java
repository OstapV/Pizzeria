package com.company.controllers;


import com.company.models.User;
import com.company.service.UserService;
import com.company.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
public class EmployeeController {


    private final UserService userService;
    private final UserStatusService userStatusService;

    @Autowired
    public EmployeeController(UserService userService, UserStatusService userStatusService) {
        this.userService = userService;
        this.userStatusService = userStatusService;
    }

    @GetMapping("/employees")
    public String myPage(Model model){
        model.addAttribute("employees",userService.findAll().stream().filter(x->x.getRole().getName().equals("Повар")).collect(Collectors.toList()));
        return "employees/index";
    }

    @GetMapping("/employees/stop")
    public String stop(@RequestParam("id") Long id, Model model){

        User user = userService.getById(id);
        user.setStatus(userStatusService.getByName("Зупинений"));

        userService.save(user);

        model.addAttribute("employees",userService.findAll().stream().filter(x->x.getRole().getName().equals("Повар")).collect(Collectors.toList()));
        return "employees/index";
    }

    @GetMapping("/employees/start")
    public String start(@RequestParam("id") Long id, Model model){
        User user = userService.getById(id);
        user.setStatus(userStatusService.getByName("Вільний"));
        userService.save(user);
        model.addAttribute("employees",userService.findAll().stream().filter(x->x.getRole().getName().equals("Повар")).collect(Collectors.toList()));
        return "employees/index";
    }

}
