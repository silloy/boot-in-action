package com.zj.rabbitmq.controller.hello;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.zj.rabbitmq.controller.RabbitConfig.helloQueue;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/11
 * Time: 14:41
 * CopyRight: Zhouji
 * 订阅模式（push）
 */
//@Component
//public class ReceivePush {
//
//    @Autowired
//    ConnectionFactory factory;
//
//    // 不再使用 QueueingConsumer
//    // <code>http://blog.csdn.net/u013256816/article/details/71342622</code>
//    @PostConstruct
//    public void receive() throws Exception {
//        Connection connection = factory.createConnection();
//        Channel channel = connection.createChannel(false);
//        channel.basicQos(1);
//        channel.basicConsume(helloQueue, false, "myConsumerTag",
//                new DefaultConsumer(channel) {
//                    @Override
//                    public void handleDelivery(String consumerTag,
//                                               Envelope envelope,
//                                               AMQP.BasicProperties properties,
//                                               byte[] body)
//                            throws IOException {
//                        String routingKey = envelope.getRoutingKey();
//                        String contentType = properties.getContentType();
//
//                        System.out.println("routingKey: " + routingKey + "\n" +
//                                "contentType: " + contentType + "\n" +
//                                "deliveryTag: " + envelope.getDeliveryTag() + "\n" +
//                                "body: " + new String(body) + "\n"
//                        );
//                        channel.basicAck(envelope.getDeliveryTag(), false);
//                    }
//                });
//        channel.close();
//        connection.close();
//    }
//}
