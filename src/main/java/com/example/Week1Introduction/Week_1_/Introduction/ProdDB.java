package com.example.Week1Introduction.Week_1_.Introduction;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        name = "my.DB.enabled",
        havingValue = "PROD"
)
public class ProdDB implements DB {
    @Override
    public void run() {
        System.out.println("Running Prod DB ");
    }
}
