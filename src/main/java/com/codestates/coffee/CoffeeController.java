package com.codestates.coffee;

import com.codestates.member.dto.MemberDto;
import com.codestates.utils.UriCreator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/v12/coffees")
public class CoffeeController {
    private final static String COFFEE_DEFAULT_URL = "/v11/coffees";
    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    @PostMapping
    public Mono<ResponseEntity> postCoffee(@Valid @RequestBody Mono<CoffeeDto.Post> requestBody) {
        return requestBody
                .flatMap(post -> coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(post)))
                .map(createdCoffee -> {
                    URI location = UriCreator.createUri(COFFEE_DEFAULT_URL, createdCoffee.getCoffeeId());
                    return ResponseEntity.created(location).build();
                });
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody Mono<CoffeeDto.Patch> requestBody) {
        Mono<CoffeeDto.Response> response =
                requestBody
                        .flatMap(patch -> {
                            patch.setCoffeeId(coffeeId);
                            return coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(patch));
                        })
                        .map(coffee -> mapper.coffeeToCoffeeResponseDto(coffee));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        Mono<CoffeeDto.Response> response =
                coffeeService.findCoffee(coffeeId)
                                .map(coffee -> mapper.coffeeToCoffeeResponseDto(coffee));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees(@Positive int page, @Positive int size) {
        Mono<List<CoffeeDto.Response>> response =
                coffeeService.findCoffees(PageRequest.of(page - 1, size, Sort.by("coffeeId").descending()))
                        .map(pageCoffee -> mapper.coffeesToCoffeeResponseDtos(pageCoffee.getContent()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") @Positive int coffeeId) {
        Mono<Void> response = coffeeService.deleteCoffee(coffeeId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
