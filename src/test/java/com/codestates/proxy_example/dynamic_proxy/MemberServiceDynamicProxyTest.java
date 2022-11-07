package com.codestates.proxy_example.dynamic_proxy;

import com.codestates.proxy_example.Member;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class MemberServiceDynamicProxyTest {
    @Test
    public void createMemberTest() {
        MemberService proxyMemberService =
                (MemberService) Proxy.newProxyInstance(
                        getClass().getClassLoader(),
                        new Class[]{MemberService.class},
                        new DynamicProxyInvocationHandler(new MemberTargetService()));

        Member member = new Member("hgd@gmail.com", "Hong Gil Dong", "010-1111-1111");
        Member resultMember = proxyMemberService.createMember(member);
    }

    @Test
    public void updateMemberTest() {
        MemberService proxyMemberService =
                (MemberService) Proxy.newProxyInstance(
                        getClass().getClassLoader(),
                        new Class[]{MemberService.class},
                        new DynamicProxyInvocationHandler(new MemberTargetService()));

        Member member = new Member("hgd1@gmail.com", "Hong Gil Dong", "010-2222-2222");
        Member resultMember = proxyMemberService.updateMember(member);
    }
}
