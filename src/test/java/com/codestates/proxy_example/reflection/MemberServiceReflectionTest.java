package com.codestates.proxy_example.reflection;

import com.codestates.proxy_example.Member;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Reflection 예제
 */
public class MemberServiceReflectionTest {
    @Test
    public void createMemberTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 생성자 메타 정보를 이용해 객체 생성
        MemberService memberService = MemberTargetService.class.getDeclaredConstructor().newInstance();

        Member member = new Member("hgd1@gmail.com", "Hong Gil Dong", "010-2222-2222");
        memberService.createMember(member);
    }


    @Test
    public void updateMemberTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 메서드 메타 정보를 이용해 Method 객체 생성
        Method method = MemberTargetService.class.getMethod("updateMember", Member.class);

        Member member = new Member("hgd1@gmail.com", "Hong Gil Dong", "010-2222-2222");

        // Target 클래스의 메서드 호출. Target 클래스의 객체와 호출하려는 메서드의 파라미터를 제공한다.
        method.invoke(new MemberTargetService(), member);
    }}
