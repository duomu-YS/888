package com.duomu.rabbitmq;

import com.duomu.rabbitmq.service.SendServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext ac = SpringApplication.run(Application.class, args);
		SendServer sendServer = (SendServer) ac.getBean("sendService");
		sendServer.sendTopicMessage("准备睡觉Q！！！！");
	}

}
