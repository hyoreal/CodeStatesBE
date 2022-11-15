package com.codestates.proxy_example.proxy_factory_bean;

import com.codestates.proxy_example.Member;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Spring의 ProxyFactoryBean을 이용한 Proxy 생성 테스트
 */
public class MemberServiceProxyFactoryBeanTest {
    @Test
    public void createMemberTest() {
        // given
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong", "010-1111-1111");


        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new MemberTargetService()); // 부가 기능을 적용할 타겟 클래스 지정
        proxyFactoryBean.addAdvice(new LogAdvice());    // 부가 기능인 Advice 추가

        // MemberService 인터페이스에 대한 Proxy를 ProxyFactorBean에서 꺼낸다.
        MemberService memberServiceProxy = (MemberService) proxyFactoryBean.getObject();


        // when
        // Proxy로 부가 기능 적용 대상 메서드 호출. 부가 기능을 처리하는 Advice가 먼저 실행된 뒤 타겟 클래스가 실행된다.
        Member resultMember = memberServiceProxy.createMember(member);

        // then
        assertThat(resultMember.getEmail(), is(member.getEmail()));
        assertThat(resultMember.getName(), is(member.getName()));
        assertThat(resultMember.getPhone(), is(member.getPhone()));
    }
}
