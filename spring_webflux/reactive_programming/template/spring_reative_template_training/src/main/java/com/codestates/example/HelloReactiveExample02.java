package com.codestates.example;

import reactor.core.publisher.Mono;

// HelloReactiveExample01 을 하나의 메서드 체인 형태로 나타낸 HelloReactiveExample02
public class HelloReactiveExample02 {
    public static void main(String[] args) {
        Mono.just("Hello, Reactive")
                .subscribe(System.out::println);
    }
}
