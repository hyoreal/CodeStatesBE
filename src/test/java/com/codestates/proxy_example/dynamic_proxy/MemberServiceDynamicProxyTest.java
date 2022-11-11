package com.codestates.proxy_example.dynamic_proxy;

import com.codestates.proxy_example.Member;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class MemberServiceDynamicProxyTest {
    @Test
    public void createMemberTest() {
        MemberService proxyMemberService =
                (MemberService) Proxy.newProxyInstance(
                        getClass().getClassLoader(),     // 생성된 프록시 객체를 로딩하기 위한 클래스 로더 지정
                        new Class[]{MemberService.class},   // 프록시로 구현하고자 하는 인터페이스의 Class 정보 지정
                        new DynamicProxyInvocationHandler(new MemberTargetService()));  // 프록시에서 부가 기능 적용 후, 다음 동작을 위임할 타겟 클래스

        Member member = new Member("hgd@gmail.com", "Hong Gil Dong", "010-1111-1111");

        /**
         * 프록시 객체의 메서드를 호출하면 InvocationHandler를 구현한 구현 클래스(DynamicProxyInvocationHandler)의
         * invoke() 메서드에서 부가 기능을 수행하고, Method 객체를 통해 핵심 기능(MemberTargetService)의 메서드(createMember())를 호출한다.
         *
         */
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
