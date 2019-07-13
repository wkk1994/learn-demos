package com.wkk.lean.demo.springboot.rabbitmq.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Description 自定义健康指标
 * @Author wkk
 * @Date 2019-03-03 21:22
 **/
@Component
public class RocketMQHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();

        }
        return Health.up().build();
    }

    private int check() {
        return 0;
    }
}
