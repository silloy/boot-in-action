package com.zj.rabbitmq.controller.many;

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
@RabbitListener(queues = "otm")
public class MReceiver2 {

//    @Autowired
//    private AmqpTemplate rabbitTemplate;

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello + " ==>p2");
    }

}
