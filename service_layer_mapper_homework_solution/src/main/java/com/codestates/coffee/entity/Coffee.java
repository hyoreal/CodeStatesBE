package com.codestates.coffee.entity;

import com.codestates.values.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h3>API 계층과 서비스 계층 연동 실습 과제용 Solution 코드 포함</h3>
 *
 * Coffee는 서비스 계층에서 사용되는 Entity 클래스입니다.
 *
 * <h4>price 필드 설명</h4>
 * <ul>
 *     <li>커피 가격을 의미하는 price 필드는 경우, int 형으로 표현하는 것 보다 Money로 표현할 경우 필드의 의도가 명확해집니다.</li>
 *     <li>이 처럼 필드의 의도에 맞게 지정하는 객체를 값 <b>객체(Value Object)</b>라고 합니다.</li>
 * </ul>
 * @author  황정식
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public class Coffee {
    private long coffeeId;
    private String korName;
    private String engName;
    private Money price;
}
