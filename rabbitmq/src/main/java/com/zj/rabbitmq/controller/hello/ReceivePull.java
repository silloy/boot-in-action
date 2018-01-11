package com.zj.rabbitmq.controller.hello;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.GetResponse;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import static com.zj.rabbitmq.controller.RabbitConfig.helloQueue;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/11
 * Time: 14:41
 * CopyRight: Zhouji
 * 检索模式（pull）
 */
//@Component
//public class ReceivePull {
//
//    @Autowired
//    ConnectionFactory factory;
//
//    @PostConstruct
//    public void receive() throws Exception {
//        Connection connection = factory.createConnection();
//        Channel channel = connection.createChannel(false);
//
//        GetResponse response = channel.basicGet(helloQueue, false);
//        while (true) {
//            for (int i = 0; i <= 10; i++) {
//                if (Objects.nonNull(response)) {
//                    AMQP.BasicProperties props = response.getProps();
//                    long deliveryTag = response.getEnvelope().getDeliveryTag();
//                    System.out.println(new Date() + " receive: " + new String(response.getBody()) + " " + i);
////                channel.basicAck(response.getEnvelope().getDeliveryTag(),false);
//                }
//            }
//            Thread.sleep(5000);
//            break;
//        }
//        connection.close();
//
//    }
//}
