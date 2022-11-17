package com.company.controllers;

import com.company.models.User;
import com.company.service.RoleService;
import com.company.service.UserService;
import com.company.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserStatusService userStatusService;
    @Autowired
    public RegisterController(UserService userService, RoleService roleService, UserStatusService userStatusService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userStatusService = userStatusService;
    }

    @GetMapping
    public String register(Model model){
        model.addAttribute("roles",roleService.findAll());
        return "register";
    }
    @PostMapping
    public String registerPost(@RequestParam("login") String login,
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
                .withAvatar("https://bootdey.com/img/Content/avatar/avatar7.png")
                .withEmail(email).build();


        userService.save(user);

        return "login";
    }
}
