package com.codestates.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InMemoryMemberService implements MemberService {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member createMember(Member member) {
        List<GrantedAuthority> authorities = createAuthorities(Member.MemberRole.ROLE_USER.name());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());

        UserDetails userDetails = new User(member.getEmail(), encryptedPassword, authorities);

        userDetailsManager.createUser(userDetails);

        return member;
    }

    @Override
    public Member findMember(String email) {
        return null;
    }

    private List<GrantedAuthority> createAuthorities(String... roles) {
        return Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
