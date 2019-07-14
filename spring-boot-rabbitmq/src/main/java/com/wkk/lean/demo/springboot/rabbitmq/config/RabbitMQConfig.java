package com.wkk.lean.demo.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description RabbitMQ配置
 * @Author wkk
 * @Date 2019-07-13 19:29
 **/
@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CustomConfirmCallback customConfirmCallback;

    @Autowired
    private CustomReturnCallback customReturnCallback;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(customConfirmCallback);//指定 ConfirmCallback
        rabbitTemplate.setReturnCallback(customReturnCallback);//指定 ReturnCallback
    }

    @Bean
    public RabbitListenerContainerFactory<?> simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);//NONE 自动确认 MANUAL手动确认
        return factory;
    }

    /**
     * direct路由测试
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue("direct_queue");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct_exchange");
    }

    @Bean
    Binding bindingDirectQueue() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct_routing_key");
    }

    /**
     * fanout路由测试
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout_queue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout_queue2");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_exchange");
    }

    @Bean
    Binding bindingFanoutQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    Binding bindingFanoutQueue21() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    /**
     * topic路由测试
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue("topic_queue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic_queue2");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic_exchange");
    }

    @Bean
    Binding bindingTopicQueue1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.routing.key.*");
    }

    @Bean
    Binding bindingTopicQueue2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("*.routing.key.*");
    }

    @Bean
    Binding bindingTopicQueue21() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }

    /**
     * headers路由测试
     * @return
     */
    @Bean
    public Queue headersQueue1() {
        return new Queue("headers_queue1");
    }

    @Bean
    public Queue headersQueue2() {
        return new Queue("headers_queue2");
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("headers_exchange");
    }

    @Bean
    Binding bindingHeadlersQueue1() {
        Map<String, Object> args = new HashMap<>();
        args.put("one", "one");
        args.put("two", "two");
        args.put("three", "three");
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).whereAny(args).match();
    }

    @Bean
    Binding bindingHeadlersQueue2() {
        Map<String, Object> args = new HashMap<>();
        args.put("two", "one");
        args.put("three", "three");
        return BindingBuilder.bind(headersQueue2()).to(headersExchange()).whereAny(args).match();
    }


    /**
     * 同步RPC调用
     * @return
     */
    @Bean
    public Queue rpcSyncQueue() {
        return new Queue("rpc_sync_queue");
    }

    /**
     * 异步RPC调用
     * @return
     */
    @Bean
    public Queue rpcAsyncQueue() {
        return new Queue("rpc_async_queue");
    }

    @Bean
    public Queue replyToQueue() {
        return new Queue("reply_to_queue");
    }

    @Bean
    DirectExchange directRpcExchange() {
        return new DirectExchange("direct_rpc_exchange");
    }

    @Bean
    Binding bindingDirectRpcQueue() {
        return BindingBuilder.bind(rpcAsyncQueue()).to(directRpcExchange()).with("direct_rpc_routing_key");
    }
}
