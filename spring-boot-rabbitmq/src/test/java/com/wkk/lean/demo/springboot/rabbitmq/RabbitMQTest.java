package com.wkk.lean.demo.springboot.rabbitmq;

import com.wkk.lean.demo.springboot.rabbitmq.message.SendMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

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
}
