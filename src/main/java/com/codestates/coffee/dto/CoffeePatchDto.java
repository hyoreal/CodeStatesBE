package com.codestates.coffee.dto;

import com.codestates.coffee.entity.Coffee;
import com.codestates.validator.NotSpace;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
public class CoffeePatchDto {
    private long coffeeId;

    @NotSpace(message = "커피명(한글)은 공백이 아니어야 합니다.")
    private String korName;

    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", message = "커피명(영문)은 영문이어야 합니다. 예) Cafe Latte")
    private String engName;

    private Optional<@Range(min= 100, max= 50000) Integer> price = Optional.empty();

    // 추가된 부분. 커피 상태 값을 사전에 체크하는 Custom Validator를 만들수도 있다.
    private Coffee.CoffeeStatus coffeeStatus;

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    // 수정을 할 수도 있고 안할 수도 있으므로 값이 없을 경우에는 null로 표현되어야 한다. 만약 null이 아닐 경우, mapstruct에서 매핑 시, 0을 매핑하므로 주의해야한다.
    public Integer getPrice() {
        return price.orElse(null);
    }
}
