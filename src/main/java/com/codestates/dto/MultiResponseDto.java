package com.codestates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class MultiResponseDto<T, S> {
    private T data;
    private PageInfo pageInfo;
}
