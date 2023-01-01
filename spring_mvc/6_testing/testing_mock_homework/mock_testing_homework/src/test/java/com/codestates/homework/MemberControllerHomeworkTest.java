package com.codestates.homework;

import com.codestates.helper.MemberControllerTestHelper;
import com.codestates.helper.StubData;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest implements MemberControllerTestHelper {
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

        // given
        long memberId = 1L;

        Map<String, String> updatedInfo = new HashMap<>();
        updatedInfo.put("phone", "010-0100-0100");
        updatedInfo.put("name", "백길동");

        MemberDto.Patch patch = (MemberDto.Patch) StubData.MockMember.getRequestBody(HttpMethod.PATCH);
        Member member = StubData.MockMember.getSingleResponseBody(memberId, updatedInfo);

        Mockito.when(memberService.updateMember(Mockito.any(Member.class))).thenReturn(member);

        String content = toJsonContent(patch);
        URI uri = getURI(memberId);

        // when
        ResultActions actions = mockMvc.perform(patchRequestBuilder(uri, content));

        // then
        MvcResult result = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(member.getName()))
                .andExpect(jsonPath("$.data.phone").value(member.getPhone()))
                .andReturn();


        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    void getMemberTest() throws Exception {
        // given
        long memberId = 1L;
        Member member = StubData.MockMember.getSingleResponseBody(memberId);

        Mockito.when(memberService.findMember(Mockito.anyLong())).thenReturn(member);

        URI uri= getURI(memberId);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(uri));

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
        // given
        Page<Member> members = StubData.MockMember.getSingleResponseBody();

        Mockito.when(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt())).thenReturn(members);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", String.valueOf(1));
        queryParams.add("size", String.valueOf(10));

        URI uri = getURI();

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(uri, queryParams));

        // then
        MvcResult result = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");

        assertThat(list.size(), is(2));

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void deleteMemberTest() throws Exception {
        // given
        Member member = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");
        member.setMemberId(1L);
        member.setStamp(new Stamp());

        doNothing().when(memberService).deleteMember(member.getMemberId());

        // when
        ResultActions actions = mockMvc.perform(deleteRequestBuilder(getURI(member.getMemberId())));

        // then
        MvcResult result = actions.andExpect(status().isNoContent()).andReturn();
    }
}
