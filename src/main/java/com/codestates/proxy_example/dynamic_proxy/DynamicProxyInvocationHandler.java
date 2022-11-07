package com.codestates.proxy_example.dynamic_proxy;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class DynamicProxyInvocationHandler implements InvocationHandler {
    private final MemberService target;

    public DynamicProxyInvocationHandler(MemberService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Member member = (Member) method.invoke(target, args);
        if (method.getName().equals("createMember")) {
            log.info("# Created Member successfully: {}:{}", member.getEmail(), member.getName());
        }
        return member;
    }
}
