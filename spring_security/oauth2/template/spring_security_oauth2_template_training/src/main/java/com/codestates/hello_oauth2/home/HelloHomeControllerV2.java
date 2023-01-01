package com.codestates.hello_oauth2.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// SecurityContext를 이용하는 방법
//@Controller
public class HelloHomeControllerV2 {
    @GetMapping("/hello-oauth2")
    public String home() {
        var oAuth2User = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(oAuth2User.getAttributes().get("email"));
        return "hello-oauth2";
    }
}
