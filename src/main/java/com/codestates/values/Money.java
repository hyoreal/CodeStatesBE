package com.codestates.values;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Builder             // Mapstruct를 위해 @Builder를 사용. setter는 불변 객체를 만들 수 없으므로 제외
@AllArgsConstructor  // Mapstruct는 @NoArgsConstructor와 함께 사용하면 @AllArgsConstructor가 정상 동작하지 않는다.
@NoArgsConstructor   // Spring Data는 디폴트 생성자가 필요하다.
public class Money {
    Integer value;  // 수정될수도 있고, 안될수도 있는 값으로 사용되기 때문에 Wrapper 타입을 사용한다.
}
