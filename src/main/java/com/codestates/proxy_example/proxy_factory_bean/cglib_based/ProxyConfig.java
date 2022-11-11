package com.codestates.proxy_example.proxy_factory_bean.cglib_based;


import com.codestates.member.repository.MemberRepository;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {
    private final MemberRepository memberRepository;

    public ProxyConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //
    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setTarget(new MemberService(memberRepository));
        proxyFactoryBean.addAdvice(new LogAdvice());

        return proxyFactoryBean;
    }
}
