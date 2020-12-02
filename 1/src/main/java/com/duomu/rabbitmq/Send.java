package com.duomu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //rabbitmq连接信息
        factory.setHost("192.168.102.128");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("root");
        // 定义连接
        Connection connection = null;
        Channel channel = null;
        //获取连接
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            /*
            *  声明一个队列
            * 1、队名
            * 2、是否持久
            * 3、是否排外，排外只有一个消费者监听着
            * 4、是否自动删除，队列没消息和没消费者自动删除
            * 5、队列属性，通常为null
            *
            * ****声明队列名已存在就会放弃声明

            * */
            channel.queueDeclare("myQueueDirect",true,false,false,null);

            String message= "direct直连的消息RoutingKey！！！！！";


            /*
            * 1、交换名
            * 2、交换机形式：direct、fanout、topic
            * */
            channel.exchangeDeclare("exchangeDirect","direct",true);
            /*
            * 1、队列名
            * 2、交换机名
            * 3、BingingKey
            * */
            channel.queueBind("myQueueDirect","exchangeDirect","directRoutingKey");

            /*
             * 发送消息到MQ
             * 1、交换机名
             * 2、队列名或RoutingKey，指定交换机就是RoutingKey
             * 3、消息属性，通常为null
             * 4、消息字节组数
             *
             *  */
            channel.basicPublish("exchangeDirect","directRoutingKey",null,message.getBytes("UTF-8"));
            System.out.println("消息发送成功！！！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if(channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
