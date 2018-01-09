package com.zj.rabbitmq.controller.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.zj.rabbitmq.controller.RabbitConfig.helloQueue;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/5
 * Time: 20:16
 * CopyRight: Zhouji
 */
@Component
@RabbitListener(queues = {helloQueue})
public class Receiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : "  + new Date() + "==>" + hello);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
