package com.codestates.homework;

import com.codestates.helper.RandomPasswordGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPasswordGeneratorTest {
    @DisplayName("실습 3: 랜덤 패스워드 생성 테스트")
    @Test
    public void generateTest() {
        // TODO 여기에 테스트 케이스를 작성해주세요.

        // given
        int numberOfUpperCaseLetters = 2;
        int numberOfLowerCaseLetters = 5;
        int numberOfNumeric = 2;
        int numberOfSpecialChars = 1;
        int pWLength = numberOfUpperCaseLetters +
                numberOfLowerCaseLetters +
                numberOfNumeric +
                numberOfSpecialChars;

        // when
        String randomPW = RandomPasswordGenerator.generate(
                numberOfUpperCaseLetters,
                numberOfLowerCaseLetters,
                numberOfNumeric,
                numberOfSpecialChars);

        int randomPWLength = randomPW.length();
        int randomPWUpperCaseLetters = (int) randomPW.chars()
                        .filter(Character::isUpperCase)
                        .count();
        int randomPWLowerCaseLetters = (int) randomPW.chars()
                        .filter(Character::isLowerCase)
                        .count();
        int randomPWNumeric = (int) randomPW.chars()
                        .filter(Character::isDigit)
                        .count();
        int randomPWSpecialChars = (int) randomPW.chars()
                        .filter(c -> c>= 33 && c <= 47)
                        .count();

        // then
        assertEquals(pWLength, randomPWLength);
        assertEquals(numberOfUpperCaseLetters, randomPWUpperCaseLetters);
        assertEquals(numberOfLowerCaseLetters, randomPWLowerCaseLetters);
        assertEquals(numberOfNumeric, randomPWNumeric);
        assertEquals(numberOfSpecialChars, randomPWSpecialChars);
    }
}
