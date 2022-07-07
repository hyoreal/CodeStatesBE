package com.codestates.homework;

import com.codestates.helper.MemberControllerTestHelper;
import com.codestates.helper.StubData;
import com.codestates.member.dto.MemberDto;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest implements MemberControllerTestHelper {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private ResultActions postResultActions;
    private MemberDto.Post post;
    private MvcResult postResult;

    @BeforeEach
    public void init() throws Exception {
        this.post = (MemberDto.Post) StubData.MockMember.get(HttpMethod.POST);
        String content = gson.toJson(post);
        URI uri = getURI();
        this.postResultActions = mockMvc.perform(postRequestBuilder(uri, content));
    }

    @Test
    public void postMemberTest() throws Exception {
        // given
        // init()

        // when
        // init()

        // then
        this.postResultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(this.post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(this.post.getName()))
                .andExpect(jsonPath("$.data.phone").value(this.post.getPhone()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void patchMemberTest() throws Exception {
        // given
        MemberDto.Patch patch =
                (MemberDto.Patch) StubData.MockMember.get(HttpMethod.PATCH);
        String content = gson.toJson(patch);
        long memberId = getResponseMemberId();
        URI uri = getURI(memberId);

        // when
        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(uri, content));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()));
    }

    @Test
    void getMemberTest() throws Exception {
        // given
        // init()

        // when
        long memberId = getResponseMemberId();
        URI uri = getURI(memberId);

        // then
        mockMvc.perform(getRequestBuilder(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(this.post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(this.post.getName()))
                .andExpect(jsonPath("$.data.phone").value(this.post.getPhone()));
    }

    @Test
    void getMembersTest() throws Exception {
        // given
        String content = gson.toJson(new MemberDto.Post("hgd2@gmail.com", "홍길동2",
                "010-2222-2222"));
        URI uri = getURI();
        mockMvc.perform(postRequestBuilder(uri, content));

        // when
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", String.valueOf(1));
        queryParams.add("size", String.valueOf(10));

        // then
        MvcResult result = mockMvc.perform(getRequestBuilder(uri, queryParams))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");

        assertThat(list.size(), is(2));
    }

    @Test
    void deleteMemberTest() throws Exception {
        // given
        // init()

        // when
        long memberId = getResponseMemberId();
        URI uri = getURI(memberId);

        // then
        mockMvc.perform(deleteRequestBuilder(uri))
                .andExpect(status().isNoContent());
    }

    private long getResponseMemberId() {
        long memberId;
        try {
            String responseMember = this.postResultActions
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
            memberId = JsonPath.parse(responseMember).read("$.data.memberId", Long.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return memberId;
    }
}
