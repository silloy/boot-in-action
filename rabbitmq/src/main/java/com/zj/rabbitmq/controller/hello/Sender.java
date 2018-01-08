package com.zj.rabbitmq.controller.hello;

import com.zj.rabbitmq.controller.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/5
 * Time: 20:15
 * CopyRight: Zhouji
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    RabbitTemplate template;

    public void send(Integer i) {
        String context = "hello " + new Date() + " ; numer=" + i;
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(RabbitConfig.routeKey, context);
    }







}
