package com.codestates;


import com.codestates.coffee.entity.Coffee;
import com.codestates.member.entity.Member;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Profile("mapping")
@EntityScan(basePackageClasses = {JpaMappingConfig.class})
@Configuration
public class JpaMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();

            Member member = new Member("hgd@gmail.com", "Hong Gil Dong","010-1111-1111");
            em.persist(member);

            Order order = new Order();
            order.addMember(member);
            em.persist(order);

            member.addOrder(order);
            order.addMember(member);

            Coffee coffee = new Coffee();
            coffee.setKorName("카페라떼");
            coffee.setEngName("Caffe Latte");
            coffee.setPrice(4500);
            coffee.setCoffeeCode("CFL");
            em.persist(coffee);

            OrderCoffee orderCoffee = new OrderCoffee();
            coffee.addOrderCoffees(orderCoffee);
            order.addOrderCoffees(orderCoffee);
            em.persist(orderCoffee);

            tx.commit();
        };
    }

    private void mappingTest
}
