package com.tharbad.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class LoginController {

    @GetMapping("/user")
    public String getUser() {
        return "Welcome user";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Welcome admin";
    }
}
