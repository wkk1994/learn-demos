package com.wkk.lean.demo.springboot.rabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description 消息消费反馈
 * @Author wkk
 * @Date 2019-07-13 23:14
 **/
@Component
public class CustomReturnCallback implements RabbitTemplate.ReturnCallback{

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息主体 message : "+message);
        System.out.println("消息主体 message : "+replyCode);
        System.out.println("描述："+replyText);
        System.out.println("消息使用的交换器 exchange : "+exchange);
        System.out.println("消息使用的路由键 routing : "+routingKey);
    }
}
