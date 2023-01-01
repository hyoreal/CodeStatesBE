package com.codestates.example.operators.peeking;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class LogExample01 {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500, 600))
                .log()
                .reduce(Integer::sum)
                .log()
                .subscribe(System.out::println);
    }
}
