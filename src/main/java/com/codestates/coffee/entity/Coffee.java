package com.codestates.coffee.entity;

import com.codestates.audit.Auditable;
import com.codestates.order.entity.OrderCoffee;
import com.codestates.values.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Coffee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coffeeId;

    @Column(length = 100, nullable = false)
    private String korName;

    @Column(length = 100, nullable = false)
    private String engName;

    // 레거시 코드
    /*
    @Column(length = 5, nullable = false)
    private Integer price;
    */

    // Value Object를 사용하는 예제
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false, length = 5))
    private Money price;

    @Column(length = 3, nullable = false, unique = true)
    private String coffeeCode;

    // 커피 상태 추가
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    // homework solution 추가
    @OneToMany(mappedBy = "coffee")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    /**
     * 클래스 레벨에 @Setter 애너테이션으로 setter를 추가했지만 양방향 연관 관계를 안전하게 매핑하기 위해 orderCoffee 쪽에도 coffee를 추가한다.
     */
    public void setOrderCoffee(OrderCoffee orderCoffee) {
        this.orderCoffees.add(orderCoffee);
        if (orderCoffee.getCoffee() != this) {
            orderCoffee.setCoffee(this);
        }
    }
    // 커피 상태 추가
    public enum CoffeeStatus {
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매중지");

        @Getter
        private String status;

        CoffeeStatus(String status) {
            this.status = status;
        }
    }
}
