package com.codestates.example;

import java.util.List;

public class ImperativeProgrammingExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 3, 6, 7, 8, 11);
        int sum = 0;

        for (int number : numbers) {
            if (number > 4 && (number % 2 == 0)) sum += number;
        }
        System.out.println("# 명령형 프로그래밍: " + sum);
    }
}
