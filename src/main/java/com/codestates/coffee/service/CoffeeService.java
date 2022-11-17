package com.codestates.coffee.service;

import com.codestates.coffee.entity.Coffee;
import com.codestates.values.Money;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>API 계층과 서비스 계층 연동 실습 과제용 Solution 코드 포함</h3>
 *
 * CoffeeService는 실습 과제를 위한 다섯 개의 Solution 메서드를 포함하고 있습니다.
 * CoffeeService는 의존하는 데이터 액세스 계층의 클래스는 존재하지 않으며, 따라서 구체적인 비즈니스 로직을 포함하고 있지 않습니다.
 *
 * <h4>{@literal @}Service 애너테이션 설명</h4>
 * <ul>
 *     <li>
 *         서비스 클래스가 Sprig Bean 등록을 위한 컴포넌트 스캔의 대상이 되도록 지정하는 애너테이션입니다.
 *     </li>
 *     <li>
 *         따라서 애플리케이션 실행 시, 컴포넌트 스캔이 일어나고 CoffeeService 클래스는 ApplicationContext에 Bean으로 등록됩니다.
 *     </li>
 * </ul>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html" target="_blank">@Service</a>
 * @author 황정식
 * @version 1.0.0
 */
@Service
public class CoffeeService {
    /**
     * 커피 정보 등록을 위한 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>현재까지는 데이터 액세스 계층과 연동되지 않으므로 파라미터로 전달 받은 Coffee coffee를 return 값으로 그대로 전달합니다.</li>
     * </ul>
     *
     * @param coffee    CoffeeController로부터 전달 받은 커피 정보
     * @return  CoffeeController로부터 전달받은 Coffee coffee 객체
     */
    public Coffee createCoffee(Coffee coffee) {
        return coffee;
    }

    /**
     * 커피 정보 수정을 위한 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>현재까지는 데이터 액세스 계층과 연동되지 않으므로 파라미터로 전달 받은 Coffee coffee를 return 값으로 그대로 전달합니다.</li>
     * </ul>
     *
     * @param coffee    CoffeeController로부터 전달 받은 커피 정보
     * @return  CoffeeController로부터 전달받은 Coffee coffee 객체
     */
    public Coffee updateCoffee(Coffee coffee) {
        return coffee;
    }

    /**
     * 특정 커피 정보 조회를 위한 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>
     *         현재까지는 데이터 액세스 계층과 연동되지 않으므로 Coffee stub을 생성해 return 값으로 전달합니다.
     *     </li>
     * </ul>
     * @param coffeeId CoffeeController로부터 전달 받은 조회하고자 하는 커피의 식별자 값
     * @return  Coffee stub 정보가 포함된 Coffee 객체
     */
    public Coffee findCoffee(long coffeeId) {
        return new Coffee(coffeeId, "아메리카노", "Americano", new Money(2500));
    }

    /**
     * 여러 건의 커피 정보 목록을 조회하기 위한 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>
     *         현재까지는 데이터 액세스 계층과 연동되지 않으므로 Coffee stub을 생성해 List&lt;Coffee&gt;을 return 값으로 전달합니다.
     *     </li>
     * </ul>
     * <p>
     * <b>TO DO</b>
     * <ul>
     *     <li>
     *         현재 버전에서는 findCoffees() 메서드에 페이지네이션에 대한 파라미터를 포함하지 않습니다.
     *         페이지네이션 관련 정보는 데이터액세스 계층 학습 때 추가 예정이니 참고하세요.
     *     </li>
     * </ul>
     * @return  Coffee stub 정보가 포함된 List&lt;Coffee&gt; 객체
     */
    public List<Coffee> findCoffees() {
        return List.of(
                new Coffee(1L, "아메리카노", "Americano", new Money(2500)),
                new Coffee(2L, "카라멜 라떼", "Caramel Latte", new Money(5000))
        );
    }

    /**
     * 특정 커피 정보를 삭제하기 위한 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>
     *         현재 버전에는 데이터 액세스 계층 연동이 없으므로 특별한 로직이 필요하지 않습니다.
     *     </li>
     * </ul>
     * <p>
     * <b>TO DO</b>
     * <ul>
     *     <li>
     *         현재 버전에서는 coffeeId에 해당하는 특정 커피 정보를 삭제하는 로직이 없습니다.
     *         특정 커피 정보의 삭제 로직은 데이터액세스 계층 학습 때 추가 예정이니 참고하세요.
     *     </li>
     * </ul>
     *
     *  @param coffeeId 삭제하고자 하는 커피의 식별자 값.
     */
    public void deleteCoffee(long coffeeId) {
    }
}
