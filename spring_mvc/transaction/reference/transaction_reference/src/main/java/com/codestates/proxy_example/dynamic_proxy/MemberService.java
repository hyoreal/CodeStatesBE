package com.codestates.proxy_example.dynamic_proxy;

import com.codestates.proxy_example.Member;

public interface MemberService {
    Member createMember(Member member);
    Member updateMember(Member member);
}
