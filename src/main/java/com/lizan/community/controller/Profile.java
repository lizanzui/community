package com.lizan.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Profile {

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action, Model model) {
        return "profile";
    }
}
