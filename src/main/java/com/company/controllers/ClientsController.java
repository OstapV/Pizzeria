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
public class ClientsController {

    private final UserService userService;
    private final UserStatusService userStatusService;

    @Autowired
    public ClientsController(UserService userService, UserStatusService userStatusService) {
        this.userService = userService;
        this.userStatusService = userStatusService;
    }

    @GetMapping("/clients")
    public String myPage(Model model){
        model.addAttribute("employees",userService.findAll().stream().filter(x->x.getRole().getName().equals("Клієнт")).collect(Collectors.toList()));
        return  "clients/index";
    }

    @GetMapping("/clients/stop")
    public String stop(@RequestParam("id") Long id, Model model){

        User user = userService.getById(id);
        user.setStatus(userStatusService.getByName("Заблокований"));

        userService.save(user);

        model.addAttribute("employees",userService.findAll().stream().filter(x->x.getRole().getName().equals("Клієнт")).collect(Collectors.toList()));
        return "clients/index";
    }

    @GetMapping("/clients/start")
    public String start(@RequestParam("id") Long id, Model model){
        User user = userService.getById(id);
        user.setStatus(userStatusService.getByName("Вільний"));
        userService.save(user);
        model.addAttribute("employees",userService.findAll().stream().filter(x->x.getRole().getName().equals("Клієнт")).collect(Collectors.toList()));
        return "clients/index";
    }
}
