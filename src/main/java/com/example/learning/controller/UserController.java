package com.example.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning.service.UserService;

// @Controller
// public class UserController {

//     // @GetMapping("/login")
//     // public String login(Model model) {
//     // model.addAttribute("message", "Hello, world!");
//     // return "login";
//     // }

//     @RequestMapping("/")
//     public String getHomePage() {
//         return "hello from controller";
//     }
// }

@RestController
public class UserController {

    // @GetMapping("/login")
    // public String login(Model model) {
    // model.addAttribute("message", "Hello, world!");
    // return "login";
    // }

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return this.userService.getUser();
    }
}
