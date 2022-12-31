package com.codestates.homework;

import com.codestates.helper.MemberControllerTestHelper;
import com.codestates.helper.StubData;
import com.codestates.member.controller.MemberController;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;

import static com.codestates.util.ApiDocumentUtils.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerDocumentationTest implements MemberControllerTestHelper{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void getMemberTest() throws Exception {
        // given
        long memberId = 1L;
        Member member = StubData.MockMember.getSingleResponseBody(memberId);

        MemberDto.response responseDto = new MemberDto.response(
                1L,
                member.getEmail(),
                member.getName(),
                member.getPhone(),
                member.getMemberStatus(),
                new Stamp()
        );

        Mockito.when(memberService.findMember(Mockito.anyLong())).thenReturn(member);
        Mockito.when(mapper.memberToMemberResponse(Mockito.any(Member.class))).thenReturn(responseDto);

        String uri = getUrl(memberId);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(uri, memberId));

        // then
        actions.andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.memberId").value(memberId))
                        .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                        .andExpect(jsonPath("$.data.name").value(member.getName()))
                        .andExpect(jsonPath("$.data.phone").value(member.getPhone()))
                        .andDo(document("get-member",
                                getResponsePreProcessor(),
                                pathParameters(parameterWithName("member-id").description("회원 식별자")),
                                responseFields(
                                        List.of(fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                                                fieldWithPath("data.phone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                                                fieldWithPath("data.memberStatus").type(JsonFieldType.STRING).description("회원 상태"),
                                                fieldWithPath("data.stamp").type(JsonFieldType.NUMBER).description("스탬프 개수"))
                                )));
    }

    @Test
    public void getMembersTest() throws Exception {
        // given
        Page<Member> memberPage = StubData.MockMember.getMultiResponseBody();

        List<MemberDto.response> responses = StubData.MockMember.getMemberListMultiResponseBody();

        Mockito.when(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt())).thenReturn(memberPage);
        Mockito.when(mapper.membersToMemberResponses(Mockito.anyList())).thenReturn(responses);

        String uri = getUrl();

        MultiValueMap<String, String> para = new LinkedMultiValueMap<>();
        para.add("page", "1");
        para.add("size", "10");

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(uri, para));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-members",
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("현재 페이지"),
                                parameterWithName("size").description("페이지 사이즈")),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data[].phone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                                        fieldWithPath("data[].memberStatus").type(JsonFieldType.STRING).description("회원 상태"),
                                        fieldWithPath("data[].stamp").type(JsonFieldType.NUMBER).description("스탬프 개수"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 전체 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("조회 건 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                )
                        )));

    }

    @Test
    public void deleteMemberTest() throws Exception {
        // given
        long memberId = 1L;

        doNothing().when(memberService).deleteMember(Mockito.anyLong());

        String uri = getUrl(memberId);

        // when
        ResultActions actions = mockMvc.perform(deleteRequestBuilder(uri, memberId));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(document("delete-member",
                        pathParameters(
                                parameterWithName("member-id").description("삭제 회원 식별자")
                        )));
    }
}
