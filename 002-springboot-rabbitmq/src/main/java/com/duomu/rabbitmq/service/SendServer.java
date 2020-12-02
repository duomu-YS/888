package com.duomu.rabbitmq.service;

public interface SendServer {
    void sendMessage(String message);
    void sendFanoutMessage(String message);

    void sendTopicMessage(String message);

}
