package com.codestates.coffee.mapper;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.dto.CoffeeResponseDto;
import com.codestates.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * <h3>API 계층과 서비스 계층 연동 실습 과제용 Solution 코드 포함</h3>
 *
 * CoffeeMapper는 실습 과제를 위한 네 개의 Solution 매핑 메서드 시그니처를 포함하고 있습니다.
 * CoffeeMapper는 커피 정보를 위한 DTO 객체를 Entity 변환하거나 Entity 객체를 response body 전용 DTO 객체로 변환하는 기능을 수행합니다.
 *
 * <h4>CoffeeMapper에서의 {@literal @}Mapping 애너테이션 설명</h4>
 * <ul>
 *     <li>
 *         mapstruct는 기본적으로 매핑 메서드 시그니처의 파라미터의 객체(source)와
 *         return하는 객체(target)의 필드의 이름과 타입이 같으면 내부적으로 자동으로 매핑을 수행합니다.
 *     </li>
 *     <li>
 *         만약 매핑하려는 필드의 이름 또는 타입이 다르면 {@literal @}Mapping 애너테이션을 이용해 1대1로 매핑을 해줄 수 있습니다.
 *         <ul>
 *             <li>
 *                 이 때, 필드의 이름은 달라도 상관없지만 필드의 타입은 동일한 타입으로 맞춰주는 과정이 필요합니다.
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 * <p>&nbsp;</p>
 * <h4>데이터 타입이 다른 필드의 매핑을 위한 {@literal @}Mapping 애너테이션 사용 예</h4>
 * <pre>
 *     // CoffeePostDto의 price 필드 타입은 int 타입이지만 Coffee의 price는 Money 타입이기 때문에 타입이 다릅니다.
 *     // 따라서 두 필드 간의 타입을 맞춰 주어야 하며, Money 클래스의 value 필드 타입이 int 타입이므로, `price.value`와 같은 접근 방법으로 타입을 맞춰줍니다.
 *     {@literal @}Mapping(source = "price", target = "price.value")
 *     Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
 * </pre>
 * <p>&nbsp;</p>
 * <h4>Money 클래스 설명</h4>
 * <ul>
 *     <li>Money 클래스는 말 그대로 돈을 표현하는 클래스입니다.</li>
 *     <li>커피 가격인 price의 경우, int 형으로 표현하는 것 보다 Money로 표현할 경우 필드의 의도가 명확해집니다.</li>
 *     <li>이 처럼 필드의 의도에 맞게 지정하는 객체를 값 <b>객체(Value Object)</b>라고 합니다.</li>
 * </ul>
 * @see <a href="https://mapstruct.org/documentation/stable/api/org/mapstruct/Mapper.html" target="_blank">@Mapper</a>
 * @see <a href="https://mapstruct.org/documentation/stable/api/org/mapstruct/Mapping.html" target="_blank">@Mapping</a>
 * @author  황정식
 * @version 1.0.0
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CoffeeMapper {
    // Money 사용 전
//    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
//    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
//    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);

    /**
     * CoffeePostDto를 Coffee로 변환합니다.
     *
     * @param coffeePostDto source 객체
     * @return target 객체
     */
    @Mapping(source = "price", target = "price.value")
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);

    /**
     * CoffeePatchDto를 Coffee로 변환합니다.
     * @param coffeePatchDto    source 객체
     * @return  target 객체
     */
    @Mapping(source = "price", target = "price.value")
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);

    /**
     * Coffee를 CoffeeResponseDto로 변환합니다.
     * @param coffee source 객체
     * @return  target 객체
     */
    @Mapping(source = "price.value", target = "price")
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);

    /**
     * List&lt;Coffee&gt;를 List&lt;CoffeeResponseDto&gt;로 변환합니다.
     * @param coffees source 객체
     * @return target 객체
     */
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
