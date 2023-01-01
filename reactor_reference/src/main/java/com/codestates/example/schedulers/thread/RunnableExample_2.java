package com.codestates.example.schedulers.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableExample_2 {
    public static void main(String[] args) {
        log.info("# start!");
        Thread thread1 = new Thread(new SampleTask());
        Thread thread2 = new Thread(new SampleTask());

        thread1.start();
        thread2.start();
    }
}
