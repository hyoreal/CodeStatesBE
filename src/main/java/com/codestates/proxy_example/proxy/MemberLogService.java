package com.codestates.proxy_example.proxy;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberLogService implements MemberService {
    private final MemberService memberService;

    public MemberLogService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Member createMember(Member member) {
        Member resultMember = memberService.createMember(member);
        log.info("# Created Member successfully: {}:{}", resultMember.getEmail(), resultMember.getName());
        return resultMember;
    }

    @Override
    public Member updateMember(Member member) {
        Member resultMember = memberService.updateMember(member);
        return resultMember;
    }
}
