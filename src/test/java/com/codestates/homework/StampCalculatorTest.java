package com.codestates.homework;

import com.codestates.helper.StampCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StampCalculatorTest {
    @Test
    @DisplayName("실습1: 스탬프 카운트 계산 단위 테스트")
    public void calculateStampCountTest() {
        // given
        int nowCount = 5;
        int earned = 3;

        // when
        int actual = StampCalculator.calculateStampCount(5, 3);

        int expected = 7;

        // then
        System.out.println(expected == actual);
    }

    @Test
    @DisplayName("실습1: 주문 후 누적 스탬프 카운트 계산 탄위 테스트")
    public void calculateEarnedStampCountTest(){
        // TODO 여기에 테스트 케이스를 작성해주세요.
    }
}
