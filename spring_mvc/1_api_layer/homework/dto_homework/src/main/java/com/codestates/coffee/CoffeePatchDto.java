package com.codestates.coffee;

import com.codestates.member.NotSpace;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePatchDto {
    private long coffeeId;

    @NotSpace(message = "공백만으로 구성되지 않아야 합니다.")
    @Pattern(regexp = "^\\w+(\\s?\\w)*$", message = "워드 사이에 한칸의 공백만 허용될 수 있습니다.")
    private String engName;

    @NotSpace(message = "공백만으로 구성되지 않아야 합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$")
    private String korName;

    @Min(value = 100, message = "100이상의 숫자만 허용됩니다.")
    @Max(value = 50000, message = "50000이하의 숫자만 허용됩니다.")
    private int price;


    public String getEngName() {
        return engName;
    }


    public String getKorName() {
        return korName;
    }


    public int getPrice() {
        return price;
    }


    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }
}
