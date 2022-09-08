package com.codestates.homework;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private MemberRepository memberRepository;

    Member member1 = new Member("lmr@gmail.com", "이몽룡", "010-3054-7265");
    Member member2 = new Member("lch@gmail.com", "이춘향", "010-2222-2222");
    Member member3 = new Member("4tto@gmail.com", "사또", "010-3333-3333");

    @BeforeEach
    void init() {
        member1.setStamp(new Stamp());
        member2.setStamp(new Stamp());
        member3.setStamp(new Stamp());

        memberRepository.saveAll(List.of(member1, member2, member3));
    }

    @DisplayName("회원 정보 등록 테스트")
    @Test
    void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");
        String content = gson.toJson(post);


        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/v11/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        MvcResult result = actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

    @DisplayName("특정 회원 정보 수정 테스트")
    @Test
    void patchMemberTest() throws Exception {
        // TODO MemberController의 patchMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // given
        MemberDto.Patch patch = new MemberDto.Patch();
        patch.setName("홍길동");
        patch.setPhone("010-1111-1111");

        long memberId = 1L;

        String content = gson.toJson(patch);

        // when
        ResultActions actions =
                mockMvc.perform(patch("/v11/members/" + memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        // then
        MvcResult result = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(patch.getName()))
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @DisplayName("특정 회원 정보 조회 테스트")
    @Test
    void getMemberTest() throws Exception {
        // TODO MemberController의 getMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // given
        long memberId = member2.getMemberId();

        // when
        ResultActions actions =
                mockMvc.perform(get("/v11/members/" + memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        MvcResult result = actions.andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @DisplayName("회원 정보 조회 테스트")
    @Test
    void getMembersTest() throws Exception {
        // TODO MemberController의 getMembers() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // given
        int page = 1;
        int size = 10;

        // when
        ResultActions actions = mockMvc.perform(
                get("/v11/members?page=" + page + "&size=" +size)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        MvcResult result = actions.andExpect(status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @DisplayName("특정 회원 정보 삭제 테스트")
    @Test
    void deleteMemberTest() throws Exception {
        // TODO MemberController의 deleteMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        long memberId = member3.getMemberId();

        ResultActions actions = mockMvc.perform(delete("/v11/members/" + memberId));

        MvcResult result = actions.andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
