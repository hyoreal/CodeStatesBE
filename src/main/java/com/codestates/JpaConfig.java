//package com.codestates;
//
//
//import com.codestates.coffee.entity.Coffee;
//import com.codestates.member.entity.Member;
//import com.codestates.order.entity.Order;
//import com.codestates.order.entity.OrderCoffee;
//import com.codestates.stamp.entity.Stamp;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//
//@Profile("mapping")
//@EntityScan(basePackageClasses = {JpaConfig.class})
//@Configuration
//public class JpaConfig {
//    private EntityManager em;
//    private EntityTransaction tx;
//
//    @Bean
//    public CommandLineRunner testJpaRunner(EntityManagerFactory emFactory) {
//        this.em = emFactory.createEntityManager();
//        this.tx = em.getTransaction();
//
//        return args -> {
//            settings();
//            mappingTest();
//            stampTest();
//        };
//    }
//
//    private void settings() {
//        tx.begin();
//
//        Member member = new Member("hgd@gmail.com",
//                "홍길동",
//                "010-1111-1111");
//
//        Stamp stamp = new Stamp();
//        member.addStamp(stamp);
//
//        Order order = new Order();
//        order.addMember(member);
//        member.addOrder(order);
//
//        em.persist(member);
//        em.persist(order);
//        em.persist(stamp);
//
//        Coffee coffee1 = new Coffee();
//        coffee1.setKorName("카페라떼");
//        coffee1.setEngName("CaffeLatte");
//        coffee1.setPrice(4500);
//        coffee1.setCoffeeCode("CFL");
//
//        Coffee coffee2 = new Coffee();
//        coffee2.setKorName("아메리카노");
//        coffee2.setEngName("Americano");
//        coffee2.setPrice(4000);
//        coffee2.setCoffeeCode("AMR");
//
//        OrderCoffee orderCoffee1 = new OrderCoffee();
//        orderCoffee1.addCoffee(coffee1);
//        order.addOrderCoffees(orderCoffee1);
//
//        OrderCoffee orderCoffee2 = new OrderCoffee();
//        orderCoffee2.addCoffee(coffee2);
//        order.addOrderCoffees(orderCoffee2);
//
//        em.persist(coffee1);
//        em.persist(coffee2);
//        em.persist(orderCoffee1);
//        em.persist(orderCoffee2);
//
//        tx.commit();
//    }
//
//    private void mappingTest() {
//        Order findOrder = em.find(Order.class, 1L);
//
//        findOrder.getOrderCoffees()
//                .stream()
//                .forEach(findOrderCoffee -> {
//                    System.out.println("주문 정보 : " + findOrderCoffee.getCoffee().getKorName() +
//                            " " + findOrderCoffee.getCoffee().getEngName());
//                });
//    }
//
//    private void stampTest() {
//        Member findMember = em.find(Member.class, 1L);
//
//        System.out.println(findMember.getName() + "님의 스탬프는 " + findMember.getStamp().getStampCount() + "개 입니다.");
//    }
//}
