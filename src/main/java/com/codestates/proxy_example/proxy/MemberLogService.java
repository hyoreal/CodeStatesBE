package com.codestates.proxy_example.proxy;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberLogService implements MemberService {
    private final MemberService memberTargetService;

    public MemberLogService(MemberService memberTargetService) {
        this.memberTargetService = memberTargetService;
    }

    @Override
    public Member createMember(Member member) {
        Member resultMember = memberTargetService.createMember(member);
        log.info("# Created Member successfully: {}:{}", resultMember.getEmail(), resultMember.getName());
        return resultMember;
    }

    @Override
    public Member updateMember(Member member) {
        Member resultMember = memberTargetService.updateMember(member);
        return resultMember;
    }
}
