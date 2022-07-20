package com.codestates.example;

import reactor.core.publisher.Mono;

// 리액티브 프로그래밍 기본 구조
public class HelloReactiveExample01 {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactive");
        mono.subscribe(message -> System.out.println(message));
    }
}
