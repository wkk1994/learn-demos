package com.wkk.lean.demo.springboot.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description simple-test-queue监听
 * @Author wkk
 * @Date 2019-07-13 19:45
 **/
@Component
public class QueueListener {

    /**
     * direct_queue消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "direct_queue")
    public void directQueueListener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("监听到消息："+body);
        try {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * fanout_queue1消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "fanout_queue1")
    public void fanoutQueue1Listener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("fanout_queue1监听到消息："+body);
    }

    /**
     * fanout_queue2消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "fanout_queue2")
    public void fanoutQueue2Listener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("fanout_queue2监听到消息："+body);
    }

    /**
     * fanout_queue1消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "topic_queue1")
    public void topicQueue1Listener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("topic_queue1监听到消息："+body);
    }

    /**
     * fanout_queue2消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "topic_queue2")
    public void topicQueue2Listener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("topic_queue2监听到消息："+body);
    }

    /**
     * headers_queue1消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "headers_queue1")
    public void headersQueue1Listener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("headers_queue1监听到消息："+body);
    }

    /**
     * headers_queue2消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "headers_queue2")
    public void headersQueue2Listener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("headers_queue2监听到消息："+body);
    }
}
