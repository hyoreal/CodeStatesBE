package com.codestates.helper;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.order.entity.Order;
import com.codestates.stamp.Stamp;
import org.springframework.data.domain.*;
import org.springframework.http.HttpMethod;

import java.util.*;

public class StubData {
    private static Map<HttpMethod, Object> stubMemberDto;
    static  {
        stubMemberDto = new HashMap<>();
        stubMemberDto.put(HttpMethod.POST, new MemberDto.Post("hgd@gmail.com", "홍길동", "010-1111-1111"));
        stubMemberDto.put(HttpMethod.PATCH, new MemberDto.Patch(0,  null, "010-2222-2222", null));
    }

    public static class MockMember {
        public static Object getRequestBody(HttpMethod method) {
            return stubMemberDto.get(method);
        }

        public static Page<Member> getSingleResponseBody() {
            Member member1 = new Member("Imwakeup@gmail.com", "나기상", "010-8282-8282");
            member1.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member1.setStamp(new Stamp());
            member1.setMemberId(1L);

            Member member2 = new Member("waiting@gmail.com", "대기중", "010-5353-5353");
            member2.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member2.setStamp(new Stamp());
            member2.setMemberId(2L);

            return new PageImpl<>(List.of(member1, member2),
                    PageRequest.of(0, 10, Sort.by("memberId").descending()), 2);
        }

        public static Member getSingleResponseBody(long memberId) {
            Member member = new Member(
                    "hgd@hmail.com",
                    "홍길동",
                    "010-1234-1234");
            member.setMemberId(memberId);
            member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member.setStamp(new Stamp());
            return member;
        }

        public static Member getSingleResponseBody(long memberId, Map<String, String> updatedInfo) {
            String name = Optional.ofNullable(updatedInfo.get("name")).orElse("홍길동");
            String phone = Optional.ofNullable(updatedInfo.get("phone")).orElse("010-1111-1111");
            Member member = new Member("bgd@gmail.com", name, phone);
            member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member.setMemberId(memberId);
            member.setStamp(new Stamp());
            return member;
        }
    }

    public static class MockOrder {
        public static Order getSingleResponseBody(long orderId) {
            Order order = new Order(
                    null,
                    null,
                    new Member("hgd@gmail.com", "홍길동", "010-1111-1111"),
                    new ArrayList<>()
            );

            order.setOrderId(orderId);
            order.setOrderStatus(Order.OrderStatus.ORDER_CONFIRM);

            return order;
        }
    }
}
