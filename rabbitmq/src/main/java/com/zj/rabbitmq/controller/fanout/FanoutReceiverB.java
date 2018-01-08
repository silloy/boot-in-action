package com.zj.rabbitmq.controller.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.zj.rabbitmq.controller.FanoutRabbitConfig.bQueue;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/6
 * Time: 20:18
 * CopyRight: Zhouji
 */
@Component
@RabbitListener(queues = bQueue)
public class FanoutReceiverB {

    @RabbitHandler
    public void process(String message) {
        System.out.println("fanout Receiver B  : " + message);
    }

}
