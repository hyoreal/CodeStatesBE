package com.codestates.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ReactiveSystemEchoService {
    @SneakyThrows
    public void doTask1() {
        log.info("# Doing task 1");

        // 오래 걸리는 작업을 한다고 가정
        for (int i = 1; i <= 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            log.info("# Doing task 1: {}%", i * 10);
        }
        log.info("# Done task 1");
        log.info("==========================");
    }

    public void doTask2() {
        log.info("# Doing task 2");
        log.info("# Done task 2");
    }
}
