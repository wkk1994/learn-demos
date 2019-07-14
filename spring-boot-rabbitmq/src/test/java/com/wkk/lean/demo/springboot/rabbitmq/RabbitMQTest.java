package com.wkk.lean.demo.springboot.rabbitmq;

import com.wkk.lean.demo.springboot.rabbitmq.message.SendMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Description rabbitmq发送消息测试
 * @Author wkk
 * @Date 2019-07-13 21:25
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
@WebAppConfiguration
public class RabbitMQTest {

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @description 发送direct消息
     */
    @Test
    public void sendDirectMessage() {
        sendMessageService.sendMessage("direct_exchange","direct_routing_key","direct消息测试");
    }

    /**
     * @description 发送fanout消息
     */
    @Test
    public void sendFanoutMessage() {
        sendMessageService.sendMessage("fanout_exchange",null,"fanout消息测试");
    }

    /**
     * @description 发送topic消息
     */
    @Test
    public void sendTopicMessage1() {
        sendMessageService.sendMessage("topic_exchange","topic.routing.key.*","topic消息测试");
    }

    /**
     * @description 发送topic消息
     */
    @Test
    public void sendTopicMessage2() {
        sendMessageService.sendMessage("topic_exchange","topic.routing.key.test","topic消息测试");
    }

    /**
     * @description 发送topic消息
     */
    @Test
    public void sendTopicMessage3() {
        sendMessageService.sendMessage("topic_exchange","topic.routing.key","topic消息测试");
    }

    /**
     * @description 发送headers消息
     */
    @Test
    public void sendHeadersMessage1() {
        Map<String, Object> args = new HashMap<>();
        args.put("one", "one");
        args.put("two", "two");
        sendMessageService.sendMessage("headers_exchange",null,"headers消息测试", args);
    }

    @Test
    public void sendHeadersMessage2() {
        Map<String, Object> args = new HashMap<>();
        args.put("one", "one");
        args.put("two", "one");
        sendMessageService.sendMessage("headers_exchange",null,"headers消息测试", args);
    }

    @Test
    public void sendHeadersMessage3() {
        Map<String, Object> args = new HashMap<>();
        args.put("three", "three");
        sendMessageService.sendMessage("headers_exchange",null,"headers消息测试", args);
    }

    /**
     * 同步RPC
     */
    @Test
    public void sendSyncRpcMessage(){
        String result = (String) rabbitTemplate.convertSendAndReceive("rpc_sync_queue", "sync消息");
        System.out.println("响应： "+result);

    }

    /**
     * 异步RPC
     */
    @Test
    public void sendRpcAsyncMessage(){
        MessagePostProcessor message = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setCorrelationId("id123");
                message.getMessageProperties().setReplyTo("reply_to_queue");
                return message;
            }
        };
        rabbitTemplate.convertAndSend("direct_rpc_exchange", "direct_rpc_routing_key","rpc消息", message);
    }

    /**
     * 发送延迟消息
     */
    @Test
    public void sendDelayMessage(){
        MessagePostProcessor message = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("15000");
                return message;
            }
        };
        System.out.println("now time : "+new Date());
        rabbitTemplate.convertAndSend("direct_delay_exchange", "direct_delay_routing_key","延迟消息", message);
    }

    /**
     * 发送插件延迟消息
     */
    @Test
    public void sendDelaypluginMessage(){
        MessagePostProcessor message = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 10000);
                return message;
            }
        };
        System.out.println("now time : "+new Date());
        rabbitTemplate.convertAndSend("direct_delay_plugin_exchange", "direct_delay_plugin_routing_key","10插件延迟消息", message);
    }
}
