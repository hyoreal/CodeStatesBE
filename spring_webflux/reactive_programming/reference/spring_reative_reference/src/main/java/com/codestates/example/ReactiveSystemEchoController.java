package com.codestates.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reactive/echo")
public class ReactiveSystemEchoController {
    private final ReactiveSystemEchoService echoService;

    public ReactiveSystemEchoController(ReactiveSystemEchoService echoService) {
        this.echoService = echoService;
    }

    @PostMapping
    public ResponseEntity echo(@RequestParam("message") String message) {
        echoService.doTask1();
        echoService.doTask2();

        return ResponseEntity.ok(message);
    }
}
