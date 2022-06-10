package com.codestates.coffee.repository;

import com.codestates.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> { // TODO 수정된 부분
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    // TODO 수정된 부분
    @Query(value = "FROM Coffee c WHERE c.coffeeId = :coffeeId")
    Optional<Coffee> findByCoffeeId(long coffeeId);
}
