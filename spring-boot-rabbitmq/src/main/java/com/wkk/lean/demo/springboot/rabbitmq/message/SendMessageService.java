package com.wkk.lean.demo.springboot.rabbitmq.message;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description 发送消息service
 * @Author wkk
 * @Date 2019-07-13 21:04
 **/
@Service
public class SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, Object message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void sendMessage(String exchange, String routingKey, Object message, Map<String, Object> headers){
        MessagePostProcessor map = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                headers.forEach((key, value) -> {
                    message.getMessageProperties().setHeader(key, value);
                });
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchange, routingKey, message, map);
    }
}
