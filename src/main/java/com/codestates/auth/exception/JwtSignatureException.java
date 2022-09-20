package com.codestates.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtSignatureException extends AuthenticationException {
    public JwtSignatureException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
