package com.codestates.example.schedulers.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleTask implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            log.info(i + "");
        }
    }
}
