package com.codestates.hello_oauth2.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Authentication 객체를 핸들러 메서드 파라미터로 전달 받는 방법
//@Controller
public class HelloHomeControllerV3 {
    @GetMapping("/hello-oauth2")
    public String home(Authentication authentication) {
        var oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println(oAuth2User);
        System.out.println("User's email int Google: " + oAuth2User.getAttributes().get("email"));

        return "hello-oauth2";
    }
}
