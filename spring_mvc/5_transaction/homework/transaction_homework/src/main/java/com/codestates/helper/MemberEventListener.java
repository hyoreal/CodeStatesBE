package com.codestates.helper;

import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class MemberEventListener {
    private final EmailSender emailSender;
    private final MemberService memberService;

    @EventListener
    @Async
    public void memberEventListener(MemberEvent event) {
        try {
            emailSender.sendEmail("any email message");
        } catch (Exception e) {
            log.error("MailSendException happened: ", e);
            Member member = event.getMember();
            memberService.deleteMember(member.getMemberId());
        }
    }
}
