package com.codestates.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtTokenizer {
    // (1) Plain Text 형태인 Secret Key의 byte[]를 Base64 형식의 문자열로 인코딩
    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // (2) 인증된 사용자에게 JWT를 최초로 발급해주기 위한 JWT 생성 메서드
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        // (2-1) Base64 형식 Secret Key 문자열을 이용해 Key(java.security.Key) 객체를 얻음
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims) // (2-2) 인증된 사용자와 관련된 정보를 추가
                .setSubject(subject) // (2-3) JWT에 대한 제목을 추가
                .setIssuedAt(Calendar.getInstance().getTime()) // (2-4) JWT 발행 일자 설정
                .setExpiration(expiration) // (2-5) JWT 만료일시 지정
                .signWith(key) // (2-6) signWith()에 서명 위한 Key(java.security.Key) 객체 설정
                .compact(); // (2-7) JWT 생성, 직렬화
    }

    // (3) Access Token이 만료된 경우, Access Token 새로 생성하게 해주는 Refresh Token을 생성하는 메서드
    public String generateRefreshToken(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key) // 서명에 사용된 Secret Key를 설정
                .build()
                .parseClaimsJwt(jws); // JWT를 파싱해서 Claims를 얻음
    }

    // (4)  JWT 서명에 사용할 Secret Key 생성
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey); // (4-1) Base64 형식으로 인코딩 된 Secret Key 디코딩 후, byte array 반환
        Key key = Keys.hmacShaKeyFor(keyBytes); // (4-2) key byte array 기반 적절 HMAC 알고리즘 적용한 Key(java.security.Key) 객체 생성

        return key;
    }
}
