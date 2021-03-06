package com.zj.rabbitmq.controller.header;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.zj.rabbitmq.controller.HeadersRabbitConfig.headersExchange;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 14:22
 * CopyRight: Zhouji
 */
@Component
public class HeaderSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send() {
        Message message = MessageBuilder.withBody(("sender:" + new Date()).getBytes())
                .setHeader("name", "jobs")
                .build();
        System.out.println("Sender : " + new String(message.getBody()));
//        rabbitTemplate.send(message);
        rabbitTemplate.convertAndSend(headersExchange, "", message);
    }


}
