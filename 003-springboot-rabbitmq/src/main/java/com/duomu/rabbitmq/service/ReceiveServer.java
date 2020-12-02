package com.duomu.rabbitmq.service;

public interface ReceiveServer {
    void receive();
    void directReceive(String message);
}

