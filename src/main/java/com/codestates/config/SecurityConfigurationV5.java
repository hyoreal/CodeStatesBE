package com.codestates.config;

import com.codestates.auth.filter.JwtAuthenticationFilter;
import com.codestates.auth.filter.JwtVerificationFilter;
import com.codestates.auth.handler.MemberAccessDeniedHandler;
import com.codestates.auth.handler.MemberAuthenticationEntryPoint;
import com.codestates.auth.jwt.JwtTokenizer;
import com.codestates.auth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SessionCreationPolicy 설정 추가
 */
//@Configuration
//@EnableWebSecurity(debug = true)
public class SecurityConfigurationV5 {
    private final JwtTokenizer jwtTokenizer;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final CustomAuthorityUtils authorityUtils; // 추가
    private final MemberAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfigurationV5(JwtTokenizer jwtTokenizer,
                                   AuthenticationSuccessHandler authenticationSuccessHandler,
                                   AuthenticationFailureHandler authenticationFailureHandler,
                                   CustomAuthorityUtils authorityUtils, MemberAuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtTokenizer = jwtTokenizer;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authorityUtils = authorityUtils;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()
            .cors(withDefaults())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 추가
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(new MemberAccessDeniedHandler())
            .and()
            .apply(new CustomFilterConfigurer())
            .and()
            .authorizeHttpRequests(authorize -> authorize
                    .antMatchers(HttpMethod.POST, "/*/members").permitAll()
                    .antMatchers("/*/members/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/v11/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            jwtAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);


            builder
                .addFilter(jwtAuthenticationFilter)
                .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
