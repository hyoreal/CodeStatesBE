package com.codestates.proxy_example.proxy;

import com.codestates.proxy_example.Member;
import org.junit.jupiter.api.Test;

public class MemberServiceProxyTest {
    @Test
    public void createMemberTest() {
        MemberService memberService = new MemberLogService(new MemberTargetService());
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong", "010-1111-1111");
        memberService.createMember(member);
    }

    @Test
    public void updateMemberTest() {
        MemberService memberService = new MemberLogService(new MemberTargetService());
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong", "010-1111-1111");
        memberService.updateMember(member);
    }
}
