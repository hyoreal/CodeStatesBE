package com.codestates;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class PasswordEncoderTest {
    @Test
    public void encodePasswordTest() {
        String idForEncode = "bcrypt";
        Map encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        String bcryptPassword = passwordEncoder.encode("hello");
        System.out.println("bcrypt encoded: " + bcryptPassword);

        passwordEncoder = new DelegatingPasswordEncoder("noop", encoders);
        String noopPassword = passwordEncoder.encode("hello");
        System.out.println("noop encoded: " + noopPassword);

        passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        String pbkdf2Password = passwordEncoder.encode("hello");
        System.out.println("pbkdf2Password encoded: " + pbkdf2Password);

        passwordEncoder = new DelegatingPasswordEncoder("scrypt", encoders);
        String scryptPassword = passwordEncoder.encode("hello"); // NoClassesDefFoundError 발생!
        System.out.println("scryptPassword encoded: " + scryptPassword);

        passwordEncoder = new DelegatingPasswordEncoder("sha256", encoders);
        String sha256Password = passwordEncoder.encode("hello");
        System.out.println("sha256Password encoded: " + sha256Password);
    }
}
