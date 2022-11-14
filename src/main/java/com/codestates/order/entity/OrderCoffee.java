package com.codestates.order.entity;

import com.codestates.audit.Auditable;
import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderCoffee extends Auditable { // homework solution 추가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    /**
     * 클래스 레벨에 @Setter 애너테이션으로 setter를 추가했지만 양방향 연관 관계를 안전하게 매핑하기 위해 order 쪽에도 orderCoffee를 추가한다.
     */
    public void setOrder(Order order) {
        this.order = order;
        if (!this.order.getOrderCoffees().contains(this)) {
            this.order.getOrderCoffees().add(this);
        }
    }

    /**
     * 클래스 레벨에 @Setter 애너테이션으로 setter를 추가했지만 양방향 연관 관계를 안전하게 매핑하기 위해 order 쪽에도 orderCoffee를 추가한다.
     */
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        if (!this.coffee.getOrderCoffees().contains(this)) {
            this.coffee.setOrderCoffee(this);
        }
    }
}
