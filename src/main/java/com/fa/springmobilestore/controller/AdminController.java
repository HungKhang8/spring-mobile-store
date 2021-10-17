package com.fa.springmobilestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
public class AdminController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
