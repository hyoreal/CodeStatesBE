package com.codestates.proxy_example.proxy_factory_bean;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

/**
 * ProxyFactoryBean을 이용한 예제 코드에서 사용되는 핵심 기능 클래스
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
