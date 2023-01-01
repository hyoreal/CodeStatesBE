package com.codestates.example.operators.errors;

import com.codestates.example.operators.sample_data.Coffee;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 에러 기본 예제
 */
@Slf4j
public class ErrorExample01 {
    public static void main(String[] args) {
        Mono.justOrEmpty(findVerifiedCoffee())
                .switchIfEmpty(Mono.error(new RuntimeException("Not found coffee")))
                .subscribe(
                        data -> log.info("{} : {}", data.getKorName(), data.getPrice()),
                        error -> log.error("# onError: {}", error.getMessage()));
    }

    private static Coffee findVerifiedCoffee() {
        // TODO 데이터베이스에서 Coffee 정보를 조회할 수 있습니다.

        return null;
    }
}
