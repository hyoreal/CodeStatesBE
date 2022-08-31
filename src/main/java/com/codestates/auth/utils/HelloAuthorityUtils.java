package com.codestates.auth.utils;

import com.codestates.member.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HelloAuthorityUtils {
    @Value("${mail.address.admin}")
    private String adminMailAddress;

    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    public List<GrantedAuthority> createAuthorities(Member member) {
        if (member.getEmail().equals(adminMailAddress)) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }

    public List<String> createAuthorities(String email) {
        if (email.equals(adminMailAddress)) {
            return ADMIN_ROLES_STRING;
        }
        return USER_ROLES_STRING;
    }

    public List<GrantedAuthority> createAuthorities(List<String> roles) {
       List<GrantedAuthority> authorities = roles.stream()
               .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
               .collect(Collectors.toList());
       return authorities;
    }
}
