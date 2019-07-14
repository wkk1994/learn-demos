package com.wkk.lean.demo.springboot.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.IOException;
import java.util.Date;

/**
 * @Description simple-test-queue监听
 * @Author wkk
 * @Date 2019-07-13 19:45
 **/
@Component
public class QueueListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * direct_queue消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "direct_queue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void directQueueListener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("监听到消息："+body);
        try {
            // 消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
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

    /**
     * rpc_sync_queue消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "rpc_sync_queue")
    public String rpcSyncQueueListener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("rpc_sync_queue监听到消息："+body);
        int millis = (int) (Math.random() * 2 * 1000);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
        return body + " sleep for " + millis + " ms";
    }

    /**
     * rpc_queue消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "rpc_async_queue")
    public void rpcQueueListener(Channel channel, Message message, @Header(AmqpHeaders.REPLY_TO) String replyTo){
        String body = new String(message.getBody());
        System.out.println("rpc_async_queue监听到消息："+body);
        System.out.println("CorrelationId: "+message.getMessageProperties().getCorrelationId());
        System.out.println("ReplyTo: "+message.getMessageProperties().getReplyTo());
        rabbitTemplate.convertAndSend(replyTo, body);
    }

    /**
     * reply_to_queue消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "reply_to_queue")
    public void replyToQueueListener(Channel channel, Message message){
        String body = new String(message.getBody());
        System.out.println("reply_to_queue监听到消息："+body);
        System.out.println("CorrelationId: "+message.getMessageProperties().getCorrelationId());
        System.out.println("ReplyTo: "+message.getMessageProperties().getReplyTo());
    }

    /**
     * delay_to_queue消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "delay_to_queue")
    public void delayToQueueListener(Channel channel, Message message){
        System.out.println("now time : "+new Date());
        String body = new String(message.getBody());
        System.out.println("delay_to_queue监听到消息："+body);
    }

    /**
     * delay_plugin_queue消息监听
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "delay_plugin_queue")
    public void delayPluginQueueListener(Channel channel, Message message){
        System.out.println("now time : "+new Date());
        String body = new String(message.getBody());
        System.out.println("delay_plugin_queue监听到消息："+body);
    }
}
