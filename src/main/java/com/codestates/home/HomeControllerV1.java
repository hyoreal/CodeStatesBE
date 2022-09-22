package com.codestates.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class HomeControllerV1 {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
