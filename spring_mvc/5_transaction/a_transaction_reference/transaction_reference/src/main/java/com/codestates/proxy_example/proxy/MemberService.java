package com.codestates.proxy_example.proxy;

import com.codestates.proxy_example.Member;

public interface MemberService {
    Member createMember(Member member);
    Member updateMember(Member member);
}
