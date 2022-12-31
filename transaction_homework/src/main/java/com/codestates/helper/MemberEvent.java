package com.codestates.helper;

import com.codestates.member.entity.Member;
import lombok.Getter;

// Event Model Code

@Getter
public class MemberEvent{
    private final Member member;

    public MemberEvent(Member member) {
        this.member = member;
    }
}
