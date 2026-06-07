package com.example.springcore.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
public class MyPrototypeBean {
    
    private final String instanceId;

    public MyPrototypeBean() {
        this.instanceId = UUID.randomUUID().toString();
    }

    public String getInstanceId() {
        return instanceId;
    }
}
