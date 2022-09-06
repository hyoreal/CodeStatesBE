package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.helper.event.MemberRegistrationApplicationEvent;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.utils.CustomBeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *  - 메서드 구현
 *  - DI 적용
 *  - Spring Data JPA 적용
 *  - 트랜잭션 적용
 */
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberService(MemberRepository memberRepository,
                         ApplicationEventPublisher publisher,
                         CustomBeanUtils<Member> beanUtils) {
        this.memberRepository = memberRepository;
        this.publisher = publisher;

        this.beanUtils = beanUtils;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        Member savedMember = memberRepository.save(member);

        // 추가된 부분
        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, savedMember));
        return savedMember;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        // 리팩토링 전 코드
//        Optional.ofNullable(member.getName())
//                .ifPresent(name -> findMember.setName(name));
//        Optional.ofNullable(member.getPhone())
//                .ifPresent(phone -> findMember.setPhone(phone));
//        Optional.ofNullable(member.getMemberStatus())
//                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        // 리펙토링 후 코드
        Member updatingMember = beanUtils.copyNonNullProperties(member, findMember);
        return memberRepository.save(updatingMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
