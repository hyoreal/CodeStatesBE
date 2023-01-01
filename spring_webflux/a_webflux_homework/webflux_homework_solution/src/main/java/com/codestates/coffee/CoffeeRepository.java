package com.codestates.coffee;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface CoffeeRepository extends R2dbcRepository<Coffee, Long> {
    Flux<Coffee> findAllBy(Pageable pageable);
}
