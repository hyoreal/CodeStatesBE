package com.codestates.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurationV1 {
    @Order(3)
    @Bean
    public SecurityFilterChain filterChainBasic(HttpSecurity http) throws Exception {
        http
                .csrf().disable()                 // (1) csrf 공격에 대한 security 설정 비활성화
                .formLogin()                      // (2) 기본 인증방법 : 폼 로그인 으로 지정
                .loginPage("/auths/login-form")   // (3) 만들어둔 커스텀 로그인 페이지 사용 설정
                .loginProcessingUrl("/process_login")    // (4) 로그인 인증 요청 수행 URL 지정
                .failureUrl("/auths/login-form?error")   // (5) 인증 실패시 변환할 화면 지정
                .and()                                   // (6) Security 보안설정을 메서드체인형태로 구성
                 // 클라이언트 요청 대해 접근 권한 확인
                .authorizeHttpRequests()                     // (7) 클라이언트 요청에 대한 접근권한 확인하겠다는 정의
                .anyRequest()                            // (8) 클라이언트의 모든 요청에 대한 접근 허용
                .permitAll();                            // (9) 클라이언트의 모든 요청에 대한 접근 허용

        return http.build();
    }

    @Order(2)
    @Bean
    public SecurityFilterChain filterChainRole(HttpSecurity http) throws Exception {
        http
                .csrf().disable()                 // (1) csrf 공격에 대한 security 설정 비활성화
                .formLogin()                      // (2) 기본 인증방법 : 폼 로그인 으로 지정
                .loginPage("/auths/login-form")   // (3) 만들어둔 커스텀 로그인 페이지 사용 설정
                .loginProcessingUrl("/process_login")    // (4) 로그인 인증 요청 수행 URL 지정
                .failureUrl("/auths/login-form?error")   // (5) 인증 실패시 변환할 화면 지정
                .and()                                   // (6) Security 보안설정을 메서드체인형태로 구성
                // role을 이용하여 샘플 애플리케이션의 request URI 접근권한 부여
                .exceptionHandling().accessDeniedPage("/auths/access-denied")
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/orders/**").hasRole("ADMIN")
                        .antMatchers("/members/my-page").hasRole("USER")
                        .antMatchers("/**").permitAll());
        return http.build();
    }

    @Order(1)
    @Bean
    public SecurityFilterChain filterChainLogout(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/auths/login-form")
                .loginProcessingUrl("/process_login")
                .failureUrl("/auths/login-form?error")
                .and()
                .logout()                        // (1)
                .logoutUrl("/logout")            // (2)
                .logoutSuccessUrl("/")  // (3)
                .and()
                .exceptionHandling().accessDeniedPage("/auths/access-denied")
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/orders/**").hasRole("ADMIN")
                        .antMatchers("/members/my-page").hasRole("USER")
                        .antMatchers("/**").permitAll());
        return http.build();
    }



//    // InMemory User를 위한 설정. DB 사용 시 제거대상
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        // (1) UserDetails 인터페이스 : 인증된 사용자의 핵심 정보 포함
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("hyoreal51@gmail.com")
//                        .password("1111")
//                        .roles("USER")
//                        .build();
//
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin@gmail.com")
//                        .password("2222")
//                        .roles("ADMIN")
//                        .build();
//
//        // (2) 사용자 인증 정보 생성
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
