package com.codestates.example.schedulers.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorServiceExample_3 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        log.info("# start: {}", LocalDateTime.now());
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int idx = i;
            executor.submit(() -> {
                doTask("batch" + idx);
                latch.countDown();
            });
        }
        executor.shutdown();
        latch.await();

        log.info("# end: {}", LocalDateTime.now());
    }

    public static void doTask(String taskName) {
        try {
            Thread.sleep(1000);
            log.info("# {} task done!", taskName);
        } catch (InterruptedException e) {}
    }
}
