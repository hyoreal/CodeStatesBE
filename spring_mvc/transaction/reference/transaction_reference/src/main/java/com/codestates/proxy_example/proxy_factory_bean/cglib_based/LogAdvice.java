package com.codestates.proxy_example.proxy_factory_bean.cglib_based;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Spring의 ProxyFactoryBean에 의해 생성된 Proxy를 통해 호출되는 Advice
 * - MethodInterceptor를 구현해야 한다.
 * - 타겟 클래스에 공통의 부가 기능을 적용할 수 있다.
 */
@Slf4j
public class LogAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("# Execute LogAdvice before proceeding target: {} > {}",
                invocation.getThis().getClass().getName(),
                invocation.getMethod().getName());

        Object resultObject = invocation.proceed();

        log.info("# Execute LogAdvice after proceeding target: {} > {}",
                                        invocation.getThis().getClass().getName(),
                                        invocation.getMethod().getName());
        return resultObject;
    }
}
