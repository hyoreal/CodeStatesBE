package com.codestates.coffee;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePostDto {

    @NotBlank(message = "공백만으로 구성되지 않아야 합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$")
    private String korName;

    @NotBlank(message = "공백만으로 구성되지 않아야 합니다.")
    @Pattern(regexp = "^\\w+(\\s?\\w)*$", message = "워드 사이에 한칸의 공백만 허용될 수 있습니다.")
    private String engName;

    @Min(value = 100, message = "100이상의 숫자만 허용합니다.")
    @Max(value = 50000, message = "50000이하의 숫자만 허용합니다.")
    private int price;

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
