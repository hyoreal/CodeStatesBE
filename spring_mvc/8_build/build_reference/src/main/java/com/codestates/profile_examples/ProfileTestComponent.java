package com.codestates.profile_examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Profile("local") // local 프로파일이 활성화 될 경우에만 실행된다.
@Component
public class ProfileTestComponent {
    @PostConstruct
    public void init() {
        log.info("# Run ProfileTestComponent by a profile!");
    }
}
