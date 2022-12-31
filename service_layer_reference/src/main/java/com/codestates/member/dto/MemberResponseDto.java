package com.codestates.member.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private long memberId;

    private String email;

    private String name;

    private String phone;
}
