package com.codestates.coffee;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Service
public class CoffeeService {
    private final CustomBeanUtils<Coffee> beanUtils;
    private final R2dbcEntityTemplate template;
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CustomBeanUtils<Coffee> beanUtils, R2dbcEntityTemplate template, CoffeeRepository coffeeRepository) {
        this.beanUtils = beanUtils;
        this.template = template;
        this.coffeeRepository = coffeeRepository;
    }

    public Mono<Coffee> createCoffee(Coffee coffee) {

        return verifyExistCoffee(coffee.getCoffeeCode())
                .then(Mono.just(coffee))
                .map(creatingCoffee -> {
                    creatingCoffee.setCoffeeCode(creatingCoffee.getCoffeeCode().toUpperCase());
                    return creatingCoffee;
                })
                .flatMap(creatingCoffee -> template.insert(creatingCoffee));

    }

    public Mono<Coffee> updateCoffee(Coffee coffee) {
        return findVerifiedCoffee(coffee.getCoffeeId())
                .map(findCoffee -> beanUtils.copyNonNullProperties(coffee, findCoffee))
                .flatMap(updatingCoffee -> template.update(updatingCoffee));
    }

    public Mono<Coffee> findCoffee(long coffeeId) {
        return findVerifiedCoffee(coffeeId);
    }

    public Mono<Page<Coffee>> findCoffees(PageRequest pageRequest) {
        return coffeeRepository.findAllBy(pageRequest)
                .collectList()
                .zipWith(coffeeRepository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageRequest, tuple.getT2()));
    }

    public Mono<Void> deleteCoffee(long coffeeId) {
        return findVerifiedCoffee(coffeeId)
                .then(template.delete(query(where("COFFEE_ID").is(coffeeId)), Coffee.class))
                .flatMap(notUse -> Mono.empty());
    }

    public Mono<Coffee> findVerifiedCoffee(long coffeeId) {
        return template
                .selectOne(query(where("COFFEE_ID").is(coffeeId)), Coffee.class)
                .switchIfEmpty(Mono.error(new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND)));

    }
    private Mono<Void> verifyExistCoffee(String coffeeCode) {
        return template
                .selectOne(query(where("COFFEE_CODE").is(coffeeCode.toUpperCase())), Coffee.class)
                .flatMap(coffee -> {
                    if (coffee != null) {
                        return Mono.error(new BusinessLogicException(ExceptionCode.COFFEE_CODE_EXISTS));
                    }
                    return Mono.empty();
                });
    }
}
