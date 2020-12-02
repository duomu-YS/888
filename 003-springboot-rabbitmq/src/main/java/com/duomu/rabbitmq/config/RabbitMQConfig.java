package com.duomu.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //创建一个名字为myQueueDirect的队列
    @Bean
    public Queue queue(){
        return new Queue("myQueueDirect");
    }
    //创建一个名字为BootDirectExchange的交换机
    @Bean
    public Exchange myChange(){
        return new DirectExchange("bootDirectExchange");
    }
    //将队列绑定到交换机
    @Bean("binding")
//参数1 为自定义队列对象，参数名queue为自定义队列Bean 的id
//参数 2 为自定义的交换机，参数名myChange 为自定义交换机Bean 的id
    public Binding binding(Queue queue,Exchange myChange){
// 将队列绑定到交换机，参数BootRouting为RoutingKey
        return BindingBuilder.bind(queue).to(myChange).with("bootDirectRoutingKey").noargs();
    }
}