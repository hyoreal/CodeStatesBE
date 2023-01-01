package com.codestates.hello_oauth2.home;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// OAuth2AuthorizedClientService를 DI 받는 방법
//@RestController // Controller 와의 차이 블로깅하기
@RequiredArgsConstructor
public class HelloHomeControllerV5 {
    private final OAuth2AuthorizedClientService auth2AuthorizedClientService;

    @GetMapping("/hello-oauth2")
    public String home(Authentication authentication) {
        var authorizedClient = auth2AuthorizedClientService.loadAuthorizedClient("google", authentication.getName());

        OAuth2AccessToken auth2AccessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token Value: " + auth2AccessToken.getTokenValue());
        System.out.println("Access Token Type: " + auth2AccessToken.getTokenType().getValue());
        System.out.println("Access Token Scopes: " + auth2AccessToken.getScopes());
        System.out.println("Access Token Issued At: " + auth2AccessToken.getIssuedAt());
        System.out.println("Access Token Expires At: " + auth2AccessToken.getExpiresAt());

        return "hello_oauth2";
    }
}
