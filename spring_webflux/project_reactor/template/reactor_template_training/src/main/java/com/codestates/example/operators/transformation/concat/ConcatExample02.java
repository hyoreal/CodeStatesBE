package com.codestates.example.operators.transformation.concat;

import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* concat() 기본 예제 2
* */
@Slf4j
public class ConcatExample02 {
    public static void main(String[] args) {
        Flux
                .concat(Flux.fromIterable(SampleData.salesOfCafeA),
                        Flux.fromIterable(SampleData.salesOfCafeB),
                        Flux.fromIterable(SampleData.salesOfCafeC))
                .reduce(Integer::sum)
                .subscribe(data -> log.info("# total sales: {}", data));
    }
}
