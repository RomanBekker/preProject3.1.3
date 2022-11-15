package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Вернуть именно авторизированного юзера с ролью USER или ролью ADMIN и USER
    @GetMapping()
    public String backAuthorized(Model model) {
        model.addAttribute("user", userService.backAuthorized());
        return "forUsers";
    }
}
