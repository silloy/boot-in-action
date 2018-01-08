package com.zj.rabbitmq.controller.object;

import com.zj.rabbitmq.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/6
 * Time: 20:21
 * CopyRight: Zhouji
 */
@Component
@RabbitListener(queues = "obj")
public class ObjectReceiver {

    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver object : " + user);
    }

}
