package com.example.Week1Introduction.Week_1_.Introduction;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Apple implements InitializingBean, DisposableBean {
    Apple()
    {
        System.out.println("Apple cosnstructer");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct called");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean: afterPropertiesSet called");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy called");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean: destroy called");
    }
}
