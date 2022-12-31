package com.codestates.profile_examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Profile("local") // local 프로파일이 활성화 될 경우에만 실행된다.
//@Profile(value = {"local", "server"}) // 여러 개의 프로파일을 지정해서 이 중에 하나의 프로파일이 활성화 될 경우에만 실행 시킨다.
@Configuration
public class ProfileTestConfiguration {
    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> log.info("# RunProfileTestConfiguration by a profile!");
    }
}
