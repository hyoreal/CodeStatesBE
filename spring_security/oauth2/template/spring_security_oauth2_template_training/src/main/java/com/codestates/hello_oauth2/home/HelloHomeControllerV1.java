package com.codestates.hello_oauth2.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// hello-oauth2 화면에 대한 뷰를 리턴하는 HelloHomeController
//@Controller
public class HelloHomeControllerV1 {
    @GetMapping("/hello-oauth2")
    public String home() {
        return "hello-oauth2";
    }
}
