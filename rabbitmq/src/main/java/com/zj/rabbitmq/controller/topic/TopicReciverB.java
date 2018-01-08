package com.zj.rabbitmq.controller.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/6
 * Time: 20:39
 * CopyRight: Zhouji
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReciverB {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("TopicReceiverB : " + hello);
    }
}
