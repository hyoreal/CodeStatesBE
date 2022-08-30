package com.codestates.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MemberPaginationResponseDto {
    List<MemberResponseDto> data;
    PageInfo pageInfo;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static public class PageInfo { // 페이지네이션을 위한 이너클래스
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
