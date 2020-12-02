package com.duomu.rabbitmq.service.impl;

import com.duomu.rabbitmq.service.ReceiveServer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("receiveService")
public class ReceiveServerImpl implements ReceiveServer {

    @Resource
    private AmqpTemplate amqpTemplate;
    @Override
    public void receive() {

       String message = (String) amqpTemplate.receiveAndConvert("myQueueDirect");
       System.out.println(message);
    }

    @RabbitListener(queues = {"myQueueDirect"})
    @Override
    public void directReceive(String message) {
    System.out.println("监听器接收的消息-----"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue,
                                                exchange = @Exchange(name="fanoutExchange", type = "fanout")
                                                )})
    public void fanoutReceive(String message) {
        System.out.println("监听器接收的消息-----"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue,
            exchange = @Exchange(name="fanoutExchange", type = "fanout")
    )})
    public void fanoutReceive01(String message) {
        System.out.println("监听器01接收的消息-----"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue,
            exchange = @Exchange(name="fanoutExchange", type = "fanout")
    )})
    public void fanoutReceive02(String message) {
        System.out.println("监听器02接收的消息-----"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue("topic01"),key={"aa"},
            exchange = @Exchange(name="topicExchange", type = "topic")
    )})
    public void topicReceive01(String message) {
        System.out.println("topicReceive01监听器接收的消息-----"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue("topic02"),key={"aa.*"},
            exchange = @Exchange(name="topicExchange", type = "topic")
    )})
    public void topicReceive02(String message) {
        System.out.println("topicReceive02监听器接收的消息--*****-----"+message);
    }


    @RabbitListener(bindings = {@QueueBinding(value = @Queue("topic03"),key={"aa.#"},
            exchange = @Exchange(name="topicExchange", type = "topic")
    )})
    public void topicReceive03(String message) {
        System.out.println("topicReceive03监听器接收的消息--######---"+message);
    }
}
