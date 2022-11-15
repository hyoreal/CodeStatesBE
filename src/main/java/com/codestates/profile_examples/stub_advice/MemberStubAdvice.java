package com.codestates.profile_examples.stub_advice;

import com.codestates.dto.SingleResponseDto;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.stamp.Stamp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Profile(value = {"local", "development"}) // local, development 프로파일이 활성화 될 경우에만 실행된다.
@Aspect
@Component
public class MemberStubAdvice {
    @PostConstruct
    public void init() {
        log.info("# Run MemberStubAdvice by a profile!");
    }

    @Pointcut(
            "execution(* com.codestates.profile_examples.stub_advice.MemberControllerV13.postMember(..))"

    )
    public void postMemberMethod(){}

    @Around(value = "postMemberMethod()")
    public ResponseEntity aroundPostMember(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("### Apply Around Join Point");
        MemberDto.Response response = (MemberDto.Response) joinPoint.proceed();
        response = new MemberDto.Response(1L,
                "hgd3@gmail.com",
                "Hong Gil Dong",
                "010-3333-3333",
                Member.MemberStatus.MEMBER_ACTIVE,
                new Stamp());
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
}
