package com.codestates.values;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h3>API 계층과 서비스 계층 연동 실습 과제용 Solution 코드 포함</h3>
 *
 * Money는 Entity 클래스에서 사용되는 값 객체(Value Object)이며, 의미 그대로 돈을 표현합니다.
 *
 * @author  황정식
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public class Money {
    Integer value;
}
