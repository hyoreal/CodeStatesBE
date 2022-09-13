package com.codestates.homework;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberMapper mapper;

    @Test
    void patchMemberTest() throws Exception {
        // TODO MemberController의 patchMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
        // given
        MemberDto.Patch patch = new MemberDto.Patch();
        patch.setName("백길동");
        patch.setPhone("010-0100-0100");
        patch.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);

        Member member = mapper.memberPatchToMember(patch);
        member.setMemberId(1L);
        member.setStamp(new Stamp());

        Mockito.when(memberService.updateMember(Mockito.any(Member.class))).thenReturn(member);

        String content = gson.toJson(patch);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/v11/members/" + member.getMemberId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(patch.getName()))
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    void getMemberTest() throws Exception {
        // TODO MemberController의 getMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
        // given
        Member member = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");
        member.setMemberId(1L);
        member.setStamp(new Stamp());

        Mockito.when(memberService.findMember(member.getMemberId())).thenReturn(member);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/v11/members/" + member.getMemberId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        // then
        MvcResult result = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.name").value(member.getName()))
                .andExpect(jsonPath("$.data.phone").value(member.getPhone()))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void getMembersTest() throws Exception {
        // TODO MemberController의 getMembers() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
        // given
        Member member1 = new Member("Imwakeup@gmail.com", "나기상", "010-8282-8282");
        Member member2 = new Member("waiting@gmail.com", "대기중", "010-5353-5353");
        member1.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
        member2.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
        member1.setStamp(new Stamp());
        member2.setStamp(new Stamp());
        member1.setMemberId(1L);
        member2.setMemberId(2L);

        Page<Member> memberPage = new PageImpl<>(List.of(member1, member2),
                PageRequest.of(0, 10, Sort.by("memberId").descending()), 2);


        given(memberService.findMembers(0, 10)).willReturn(memberPage);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/v11/members?page=1&size=10")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        // then
        MvcResult result = actions.andExpect(status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void deleteMemberTest() throws Exception {
        // TODO MemberController의 deleteMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
        // given
        Member member = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");
        member.setMemberId(1L);
        member.setStamp(new Stamp());

        // when
        ResultActions actions =
                mockMvc.perform(
                        delete("/v11/members/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON));

        // then
        MvcResult result = actions.andExpect(status().isNoContent()).andReturn();
    }
}
