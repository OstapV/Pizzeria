package com.company.controllers;

import com.company.models.User;
import com.company.service.RoleService;
import com.company.service.UserService;
import com.company.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final UserStatusService userStatusService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, UserStatusService userStatusService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userStatusService = userStatusService;
    }

    @GetMapping("/myPage")
    public String myPage(Model model){
        model.addAttribute("roles", roleService.findAll().toArray());
        model.addAttribute("user",userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("myPage",true);
        return "users/details";
    }
    @GetMapping("/users/edit")
    public String edit(@RequestParam("id") Long id, Model model){
        model.addAttribute("user",userService.getById(id));
        return "users/edit";
    }

    @GetMapping("/users/details")
    public String details(@RequestParam("id") Long id, Model model){
        model.addAttribute("user",userService.getById(id));
        return "users/details";
    }

    @PostMapping("/users/edit")
    public String editPost(@RequestParam("id") Long id,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("email") String email) {
        User user = userService.getById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.save(user);

        if(user.getRole().getName().equals("Повар"))
            return "redirect://localhost:8080/employees";
        else
            return "redirect://localhost:8080/clients";

    }

    @GetMapping("/users/create")
    public String create(Model model){
        model.addAttribute("roles",roleService.findAll());
        return "users/create";
    }

    @PostMapping("/users/create")
    public String createPost(@RequestParam("login") String login,
                               @RequestParam("password") String password,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("idRole") Long idRole){

        User user = new User.Builder()
                .withLogin(login)
                .withPassword(new BCryptPasswordEncoder().encode(password))
                .withFirstName(firstName)
                .withLastName(lastName)
                .withRole(roleService.getById(idRole))
                .withStatus(userStatusService.getByName("Вільний"))
                .withAvatar("../imgs/avatar.png")
                .withEmail(email).build();


        userService.save(user);

        if(user.getRole().getName().equals("Повар"))
            return "redirect://localhost:8080/employees";
        else
            return "redirect://localhost:8080/clients";
    }
}
