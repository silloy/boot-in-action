package com.zj.rabbitmq.controller.timing;

import com.zj.rabbitmq.controller.TimingRabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 14:22
 * CopyRight: Zhouji
 */
@Component
public class TimingSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send() {
        Message message = MessageBuilder.withBody(("sender:" + new Date()).getBytes())
//                .setExpiration(String.valueOf(30 * 1000))   // 毫秒
//                .setHeader("name", "jobs")
                .build();
        System.out.println("Sender : " + new String(message.getBody()));
        rabbitTemplate.convertAndSend(TimingRabbitConfig.timingExange, TimingRabbitConfig.timingRoute, message);
    }


}
