package com.codestates.proxy_example.dynamic_proxy;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Dynamic Proxy에서 호출된 메서드의 구체적인 로직을 수행하는 InvocationHandler 구현 클래스.
 * - 부가 기능을 처리하는 역할을 한다.
 * - Dynamic Proxy로 생성된 Proxy 클래스는 Spring Bean으로 등록할 수 없다.
 */
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
