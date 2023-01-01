package com.codestates.proxy_example.dynamic_proxy;

import com.codestates.proxy_example.Member;
import lombok.extern.slf4j.Slf4j;

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
