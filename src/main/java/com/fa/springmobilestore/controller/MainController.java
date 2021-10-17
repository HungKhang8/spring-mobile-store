package com.fa.springmobilestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
public class MainController {

    @GetMapping("/")
    public String index() {
        return "redirect:/product/list";
    }

}
