package com.codestates.member.entity.entity_v2;

import lombok.*;

/**
 * - V2
 *  - 멤버 변수 추가
 *  - lombok 추가
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long memberId;

    private String email;

    private String name;

    private String phone;
}
