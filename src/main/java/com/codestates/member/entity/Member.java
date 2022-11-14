package com.codestates.member.entity;

import com.codestates.audit.Auditable;
import com.codestates.order.entity.Order;
import com.codestates.stamp.Stamp;
import lombok.AccessLevel;
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
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    // 추가된 부분
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    // homework solution 추가
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    // homework solution 추가
    /**
     * cascade 애트리뷰트
     * - CascadeType.PERSIST
     *      - member 객체만 영속성 컨텍스트에 영속화(persist)하면 member와 연관관계 매핑이 되어 있는 객체까지 영속화된다.
     *      - JPA에서는 persist()를 호출하면 영속화 되지만, Spring Data JPA에서는 memberRepository.save(member)를 호출하면
     *      member 뿐만 아니라 stamp까지 영속화 되고, 내부적으로 flush()가 호출 되므로 DB의 테이블(MEMBER, STAMP)에 모두 INSERT 된다.
     * - CascadeType.REMOVE
     *      - 만약 memberRepository.delete(member)를 호출하면 연관관계 매핑이 되어 있는 STAMP 테이블에서 stamp 정보가 먼저 삭제되고,
     *      다음에 MEMBER 테이블에서 member 정보가 삭제된다.
     */
    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Stamp stamp;

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    // homework solution 추가
    /**
     * 클래스 레벨에 @Setter 애너테이션으로 setter를 추가했지만 양방향 연관 관계를 안전하게 매핑하기 위해 order 쪽에도 member를 추가한다.
     */
    public void setOrder(Order order) {
        orders.add(order);
        if (order.getMember() != this) {
            order.setMember(this);
        }
    }

    // homework solution 추가

    /**
     * 클래스 레벨에 @Setter 애너테이션으로 setter를 추가했지만 양방향 연관 관계를 안전하게 매핑하기 위해 stamp 쪽에도 member를 추가한다.
     */
    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
        if (stamp.getMember() != this) {
            stamp.setMember(this);
        }
    }
    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
           this.status = status;
        }
    }
}
