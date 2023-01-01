package com.codestates.coffee.controller;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.entity.Coffee;
import com.codestates.coffee.mapper.CoffeeMapper;
import com.codestates.coffee.service.CoffeeService;
import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Controller
@RequestMapping("/coffees")
//@Validated
public class CoffeeController {
    private CoffeeService coffeeService;
    private CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper) {
        this.coffeeService = coffeeService;
        this.mapper = coffeeMapper;
    }

    @GetMapping("/coffee-form")
    public String coffeeHome() {
        return "coffee";
    }

    @PostMapping
    public String postCoffee(@Valid CoffeePostDto coffeePostDto,
                             BindingResult bindingResult,
                             Model model) throws InterruptedException {
        // 애너테이션 Validation을 하지 않을 경우,
        if (ObjectUtils.isEmpty(coffeePostDto.getEngName())) {
            bindingResult.addError(new FieldError("coffee"
                    , "engName"
                    , "커피명(영문)을 입력해야 합니다."));
        }
        // 다른 검증 조건 추가..

        if (bindingResult.hasErrors()) {
            // TODO 에러 정보를 Model 객체에
            return "coffee";
        }
        Thread.sleep(5000L);
        coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));
        Page<Coffee> coffees = coffeeService.findCoffees(0, 100);
        model.addAttribute("coffees", mapper.coffeesToCoffeeResponseDtos(coffees.getContent()));
        return "coffee";
    }
}
