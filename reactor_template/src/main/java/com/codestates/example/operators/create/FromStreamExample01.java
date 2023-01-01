package com.codestates.example.operators.create;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/*
* fromStream() 기본 예제
* */
public class FromStreamExample01 {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500, 600))
                .reduce(Integer::sum)
                .subscribe(System.out::println);
    }
}
