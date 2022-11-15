package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //Получаем всех людей (Read) из сервиса:
    @GetMapping()
    public String back(Model model) {
        model.addAttribute("users", userService.back());
        return "allUsers";
    }

    //Получаем одного человека (Read) из сервиса:
    @GetMapping(value = "/{id}")
    public String backByID(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.backByID(id));
        return "poId";
    }

    //Получаем страницу с формой для создания нового Юзера:
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    //Post-запрос, принимаем нового человека и добавляем его в БД:
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    //Создаем страницу для редактирования Usera:
    @GetMapping("/{id}/edit")
    public String updateUserGetForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.backByID(id));
        return "update";
    }

    //Patch-запрос, принимаем исправленного человека и добавляем его в БД:
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    //Delete-запрос:
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(userService.backByID(id));
        return "redirect:/admin";
    }
}
