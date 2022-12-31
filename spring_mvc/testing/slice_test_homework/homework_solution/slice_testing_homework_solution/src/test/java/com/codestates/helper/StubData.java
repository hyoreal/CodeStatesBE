package com.codestates.helper;

import com.codestates.member.dto.MemberDto;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class StubData {
    private static Map<HttpMethod, Object> stubMemberDto;
    static {
        stubMemberDto = new HashMap<>();
        stubMemberDto.put(HttpMethod.POST, new MemberDto.Post("hgd@gmail.com","홍길동",
                "010-1111-1111"));
        stubMemberDto.put(HttpMethod.PATCH, new MemberDto.Patch(1, null, "010-2222-2222", null));
    }

    public static class MockMember {
        public static Object get(HttpMethod method) {
            return stubMemberDto.get(method);
        }
    }
}
