package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>Controller 실습 과제용 Solution 코드 포함</h3>
 *
 * CoffeeController는 실습 과제에 대한 두 개의 Solution 메서드를 포함하고 있습니다.
 * CoffeeController는 의존하는 서비스 계층의 클래스는 존재하지 않으며, 따라서 구체적인 비즈니스 로직을 포함하고 있지 않습니다.
 * 클라이언트 측에서는 메모리(Map member)에 저장되어 있는 커피 정보를 수정 및 삭제할 수 있습니다.
 *
 * @author  황정식
 * @version 1.0.0
 */
@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    /**
     * CoffeeController가 의존 객체를 DI 받을 준비가 완료되면 호출됩니다.
     * <p>
     * DI 받을 객체가 없더라도 호출됩니다.
     * <P>
     * 여기서는 실습에 사용할 커피 정보를 Map에 추가하고 있습니다.
     */
    @PostConstruct
    public void init() {
        Map<String, Object> coffee = new HashMap<>();
        long coffeeId = 1L;
        coffee.put("coffeeId", coffeeId);
        coffee.put("korName", "바닐라 라떼");
        coffee.put("engName", "Vanilla Latte");
        coffee.put("price", 4500);

        coffees.put(coffeeId, coffee);
    }

    /**
     * 커피 정보 수정을 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <ul>
     *     <li>coffeeId에 해당하는 커피 정보가 Map coffee에 존재하지 않으면 404 Not Found HTTP Status를 전송합니다.</li>
     *     <li>coffeeId에 해당하는 커피 정보가 Map coffee에 존재한다면 커피 정보를 수정합니다.</li>
     * </ul>
     * @param coffeeId  URL Path Variable에 매핑되는 커피 식별자
     *                  <p>
     *                  커피 정보 수정 대상이 된다.
     * @param korName   수정하고자 하는 커피의 한글명
     * @param price     수정하고자 하는 커피의 가격
     * @return  수정된 커피 정보인 Map coffee를 포함한 ResponseEntity
     */
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                      @RequestParam("korName") String korName,
                                      @RequestParam("price") int price) {
        // No need Business logic

        Map<String, Object> coffee = coffees.get(coffeeId);

        if (coffee == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            coffee.put("korName", korName);
            coffee.put("price", price);
        }

        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    /**
     * 커피 정보 삭제를 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     *
     * @param coffeeId  URL Path Variable에 매핑되는 커피 식별자.
     *                  <p>
     *                  삭제할 대상이 된다.
     * @return  response body를 포함하지 않는 ResponseEntity.
     *          <p>
     *          커피 정보를 삭제할 경우, 삭제되어 존재하지 않으므로 204 No Content를 지정한다.
     */
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        if(coffees.containsKey(coffeeId))
            coffees.remove(coffeeId);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
