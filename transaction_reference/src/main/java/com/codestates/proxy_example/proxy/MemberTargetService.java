package com.codestates.proxy_example.proxy;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

/**
 * Proxy가 부가 기능을 수행한 이 후, 핵심 기능을 처리하는 타겟 클래스
 */
@Slf4j
public class MemberTargetService implements MemberService {
    @Override
    public Member createMember(Member member) {
        // 데이터베이스에 저장
        log.info("# Saved member");
        return member;
    }

    @Override
    public Member updateMember(Member member) {
        // 데이터베이스에 저장
        log.info("# Updated member");
        return member;
    }
}
