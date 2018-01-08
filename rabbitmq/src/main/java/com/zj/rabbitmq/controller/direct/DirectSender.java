package com.zj.rabbitmq.controller.direct;

import com.zj.rabbitmq.controller.DirectRabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/6
 * Time: 20:16
 * CopyRight: Zhouji
 */
@Component
public class DirectSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendADirect() {
        String context = "hi, direct msgA";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(DirectRabbitConfig.directExchange, DirectRabbitConfig.orangeRoutingKey, context);
    }


    public void sendB1Direct() {
        String context = "hi, direct msgB1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(DirectRabbitConfig.directExchange, DirectRabbitConfig.blankRoutingKey, context);
    }

    public void sendB2Direct() {
        String context = "hi, direct msgB2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(DirectRabbitConfig.directExchange, DirectRabbitConfig.greenRoutingKey, context);
    }
}
