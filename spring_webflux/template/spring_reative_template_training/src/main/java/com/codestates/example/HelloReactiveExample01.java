package com.codestates.example;

import reactor.core.publisher.Mono;

public class HelloReactiveExample01 {
    public static void main(String[] args) {
        // (1) Publisher 역할 (데이터를 내보내는 역할, emit 한다)
        Mono<String> mono = Mono.just("Hello, Reactive");

        // (2) Subscriber 역할 (Publisher 로부터 내보내진 데이터를 소비하는 역할)
        mono.subscribe(System.out::println);
    }
}
