package com.duomu.rabbitmq;

import com.duomu.rabbitmq.service.ReceiveServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(Application.class, args);
		ReceiveServer receiveServer = (ReceiveServer) ac.getBean("receiveService");

		//receiveServer.receive();
	}

}
