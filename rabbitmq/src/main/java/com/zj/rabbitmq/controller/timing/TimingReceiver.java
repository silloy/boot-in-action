package com.zj.rabbitmq.controller.timing;

import com.zj.rabbitmq.controller.HeadersRabbitConfig;
import com.zj.rabbitmq.controller.TimingRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 14:24
 * CopyRight: Zhouji
 */
@Component
@RabbitListener(queues = TimingRabbitConfig.taQueue)
public class TimingReceiver {

    @RabbitHandler
    public void process(byte[] message) {
        String receiveStr = new String(message);
        System.out.println("receiver: " + new Date() + "===>" + receiveStr);
    }

}
