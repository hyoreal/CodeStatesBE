package com.codestates.hello_oauth2.home;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// OAuth2AuthorizedClient를 핸들러 메서드의 파라미터로 전달 받는 방법
@RestController
public class HelloHomeControllerV6 {
    @GetMapping("/hello-oauth2")
    public String home(@RegisteredOAuth2AuthorizedClient("google")OAuth2AuthorizedClient authorizedClient) {
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token Value: " + accessToken.getTokenValue());
        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());
        System.out.println("Access Token Scopes: " + accessToken.getScopes());
        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());
        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());

        return "hello-oauth2";
    }
}
