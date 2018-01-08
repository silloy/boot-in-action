package com.zj.rabbitmq.controller.header;

import com.zj.rabbitmq.controller.HeadersRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 14:24
 * CopyRight: Zhouji
 */
@Component
@RabbitListener(queues = HeadersRabbitConfig.haQueue)
public class HeaderReceiver {

    @RabbitHandler
    public void process(byte[] message) {
        String receiveStr = new String(message);
        System.out.println("receive-header : " + receiveStr);
    }


//    @RabbitListener(queues = HeadersRabbitConfig.haQueue)
//    public void processA(byte[] message) {
//        String receiveStr = new String(message);
//        System.out.println("receiveA : " + receiveStr);
//    }
}
