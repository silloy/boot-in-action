package com.zj.rabbitmq.controller.direct;

import com.zj.rabbitmq.controller.DirectRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/5
 * Time: 20:16
 * CopyRight: Zhouji
 */
@Component
@RabbitListener(queues = DirectRabbitConfig.daQueue)
public class DirectAReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("ReceiverA : " + hello);
    }


}
