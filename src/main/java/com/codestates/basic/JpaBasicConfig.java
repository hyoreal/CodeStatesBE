package com.codestates.basic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Profile("basic")
@EntityScan(basePackageClasses = {JpaBasicConfig.class})
@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        System.out.println("# Active Profile: basic");
        return args -> {
			persistGeneratedAUTO();
//			persistGeneratedIdentity();
//			persistAndCommitGeneratedIdentity();
//			insertLazilyEagerly();
//            updateEntity();
//            deleteEntity();
        };
    }

    private void persistGeneratedAUTO() {
        System.out.println("------------------------------");
        Member member = new Member("hgd@gmail.com");

        /**
         * - commit을 안했으므로, 1차 캐시에 저장되고, 테이블에는 저장이 안된다.
         *
         * @GeneratedValue(strategy = GenerationType.IDENTITY)이므로,
         * Hibernate: call next value for hibernate_sequence 테이블에서 식별자용 시퀀스를 가져와서 1차
         * 캐시에 채운다.
         */
        em.persist(member);

        System.out.println("------------------------------");

        Member resultMember = em.find(Member.class, 1L);
        System.out.println("Id: " + resultMember.getMemberId() + ", email: " +
                resultMember.getEmail());
        System.out.println("------------------------------");
    }

    private void persistGeneratedIdentity() {
        System.out.println("------------------------------");
        Member member = new Member("hgd@gmail.com");

        /**
         * - commit을 안했으므로, 1차 캐시에 저장되고, 테이블에는 저장이 안된다.
         * @GeneratedValue(strategy = GenerationType.IDENTITY)이므로,
         * 테이블에 저장하기 전까지는 식별자를 알 수 없다.
         * - memberId가 비어 있는 상태로 저장된다.
         */
        em.persist(member);

        System.out.println("------------------------------");

        /**
         * - 식별자가 없으므로, memberId는 비어 있다. 따라서 1차 캐시에 원하는 데이터가 없다.
         * - 그래서 테이블에서 조회한다.
         * - 그런데 commit이 아직 되지 않았으므로, 테이블에도 없다.
         */
        Member resultMember = em.find(Member.class, 1L);

        // 따라서 NullPointerException 발생
        System.out.println("Id: " + resultMember.getMemberId() + ", email: " +
                resultMember.getEmail());
        System.out.println("------------------------------");
    }

    private void persistAndCommitGeneratedIdentity() {
        tx.begin();
        System.out.println(" ------------------------------ 1");
        Member member = new Member("hgd@gmail.com");

        /**
         * - commit을 했으므로 테이블에 저장이 되고, 1차 캐시에 저장된다.
         * @GeneratedValue(strategy = GenerationType.IDENTITY)이므로,
         * 테이블에 저장했으므로 식별자까지 1차 캐시에 저장된다.
         * - memberId가 비어 있는 상태로 저장된다.
         */
        em.persist(member);
        tx.commit();
        System.out.println(" ------------------------------ 2");

        /**
         * - 식별자가 있다. memberId가 1차 캐시에 채워져있다.
         * - 그래서 1차 캐시에서 조회한다.
         * - 쿼리가 실행되지 않는다.
         */
        Member resultMember1 = em.find(Member.class, 1L);
        System.out.println(" ------------------------------ 3");

        System.out.println("Id: " + resultMember1.getMemberId() + ", email: " + resultMember1.getEmail());
        System.out.println(" ------------------------------ 4");

        Member resultMember2 = em.find(Member.class, 2L);
        System.out.println(resultMember2 == null);
        System.out.println(" ------------------------------ 5");
    }

    private void insertLazilyEagerly() {
        //엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
        tx.begin();
        /**
         * - Strategy AUTO일 경우 아래와 같이 동작, 그런데 IDENTITY 일 경우는 식별자 때문에 persist하면 테이블에
         * insert한다.
         * - 이유는 엔티티가 영속상태가 되려면 식별자가 필요하기 때문에 persist()하면 commit안해도 insert한다.
         * - 따라서 쓰기 지연 안됨.
         */
        //
        //
        System.out.println("TX begin: ------------------------------");
        Member member1 = new Member("hgd1@gmail.com");
        Member member2 = new Member("hgd2@gmail.com");

        em.persist(member1);
        em.persist(member2);
        //여기까지 INSERT SQL을 데이터베이스에 보내지 않는다. 쓰기 지연 SQL 저장소에 모아둔다.
        System.out.println("persisted: ------------------------------");
        tx.commit();
        System.out.println("TX committed: ------------------------------");
    }

    private void updateEntity() {
        tx.begin();

        System.out.println("TX1 began: ------------------------------");
        em.persist(new Member("hgd1@gmail.com"));
        System.out.println("persisted: ------------------------------");
        tx.commit();
        System.out.println("TX1 committed: ------------------------------");

        tx.begin();
        System.out.println("TX2 began: ------------------------------");
        Member member1 = em.find(Member.class, 1L);
        member1.setEmail("hgd1@yahoo.co.kr");

        tx.commit();
        System.out.println("TX2 committed: ------------------------------");
    }

    private void deleteEntity() {
        tx.begin();

        System.out.println("TX1 began: ------------------------------");
        em.persist(new Member("hgd1@gmail.com"));
        System.out.println("persisted: ------------------------------");
        tx.commit();
        System.out.println("TX1 committed: ------------------------------");

        tx.begin();
        System.out.println("TX2 began: ------------------------------");
        Member member = em.find(Member.class, 1L);
        em.remove(member);
        tx.commit();
        System.out.println("TX2 committed: ------------------------------");
    }
}
