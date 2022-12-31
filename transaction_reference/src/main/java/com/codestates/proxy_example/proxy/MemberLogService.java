package com.codestates.proxy_example.proxy;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

/**
 * MemberService의 Proxy 클래스
 * - 타겟 클래스의 로직 수행 이 후, 로그를 출력하는 부가 기능을 수행하는 역할을 한다.
 */
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
