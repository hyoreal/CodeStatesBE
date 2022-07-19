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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Controller
@RequestMapping("/coffee")
@Validated
public class CoffeeController {
    private CoffeeService coffeeService;
    private CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper) {
        this.coffeeService = coffeeService;
        this.mapper = coffeeMapper;
    }

    @GetMapping
    public String coffeeHome() {
        return "/coffee";
    }

    @PostMapping
    public String postCoffee(@Valid CoffeePostDto coffeePostDto, Model model) throws InterruptedException {
        Thread.sleep(5000L);
        coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));
        Page<Coffee> coffees = coffeeService.findCoffees(0, 100);
        model.addAttribute("coffees", mapper.coffeesToCoffeeResponseDtos(coffees.getContent()));
        return "/coffee";
    }
}
