package com.codestates.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SingleResponseDto<T> {
    private T data;
}
