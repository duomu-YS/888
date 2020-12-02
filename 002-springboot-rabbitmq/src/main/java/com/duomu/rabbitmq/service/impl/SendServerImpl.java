package com.duomu.rabbitmq.service.impl;

import com.duomu.rabbitmq.service.SendServer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sendService")
public class SendServerImpl implements SendServer {

    @Resource
    private AmqpTemplate amqpTemplate;
    @Override
    public void sendMessage(String message) {
        /*
        * 1、交换机名
        * 2、RoutingKey
        * 3、具体消息
        * */
        amqpTemplate.convertAndSend("bootDirectExchange","bootDirectRoutingKey",message);
    }

    @Override
    public void sendFanoutMessage(String message) {
        amqpTemplate.convertAndSend("fanoutExchange","",message);
    }

    @Override
    public void sendTopicMessage(String message) {
        amqpTemplate.convertAndSend("topicExchange","aa.bb.cc",message);
    }
}
