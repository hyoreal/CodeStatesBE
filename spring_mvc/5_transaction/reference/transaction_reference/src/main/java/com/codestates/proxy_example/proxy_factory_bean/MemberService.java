package com.codestates.proxy_example.proxy_factory_bean;

import com.codestates.proxy_example.Member;

public interface MemberService {
    Member createMember(Member member);
    Member updateMember(Member member);
}
