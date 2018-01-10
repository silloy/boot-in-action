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
        for (int j = 0; j <= 1; j++ ) {
            String context = "hello " + new Date() + "; numer=" + j;
            System.out.println("Sender : " + context);
            rabbitTemplate.convertAndSend(RabbitConfig.routeKey, context);
        }
    }







}
