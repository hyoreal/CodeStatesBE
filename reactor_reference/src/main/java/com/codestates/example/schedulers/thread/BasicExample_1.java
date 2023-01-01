package com.codestates.example.schedulers.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicExample_1 {
    public static void main(String[] args) {
        log.info("# start!");
        Thread thread = new Thread(){
            @Override
            public void run() {
                log.info("Hello Thread!");
            }
        };

        thread.start();
    }
}
