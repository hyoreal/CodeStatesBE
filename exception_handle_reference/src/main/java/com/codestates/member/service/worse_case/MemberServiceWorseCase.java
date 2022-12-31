package com.codestates.member.service.worse_case;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceWorseCase {
    // Better case
    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        return member; // 실제로는 DB에 회원 정보를 저장합니다.
    }

    // Worse case
    public Member createMember2(Member member) {
        if (existsMember(member.getEmail())) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
        return member; // 실제로는 DB에 회원 정보를 저장합니다.
    }

    public boolean existsMember(String email) {
        Member resultMember = findMemberByEmail(email); // 실제로는 DB에서 조회해야 합니다.
        if (resultMember == null) {
            return false;
        }
        return true;
    }

    private Member findMemberByEmail(String email) {
        return null;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = Optional.ofNullable(null); // 실제로는 DB에서 조회해야 합니다.
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
