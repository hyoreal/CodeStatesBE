package com.codestates.proxy_example.proxy_factory_bean.cglib_based;


import com.codestates.member.repository.MemberRepository;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dynamic Proxy를 이용해 생성된 Proxy는 Spring Bean으로 등록해서 사용할 수 없으므로, Proxy를 Spring Bean으로
 * 등록해서 사용하고 싶다면 Spring에서 제공하는 ProxyFactoryBean 기능을 이용하면 된다.
 *  - ProxyFactoryBean의 경우 타겟 클래스가 인터페이스의 구현 클래스가 아니어도 CGLIB 방식으로 Proxy 생성이 가능하다.
 *  - Dynamic Proxy의 경우, InvocationHandler의 구현 클래스가 필요한 반면 ProxyFactorBean에서는 MethodInterceptor의 구현 클래스가 필요하다.
 */
@Configuration
public class ProxyConfig {
    private final MemberRepository memberRepository;

    public ProxyConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //
    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();   // ProxyFactorBean 객체 생성
        proxyFactoryBean.setProxyTargetClass(true);                   // CGLIB 방식을 타겟 클래스를 직접 Proxy 하도록 설정
        proxyFactoryBean.setTarget(new MemberService(memberRepository));  // 타겟 클래스를 설정한다.
        proxyFactoryBean.addAdvice(new LogAdvice());                   // 부가 기능을 할 Advice를 추가한다.

        return proxyFactoryBean;
    }
}
