package com.zj.rabbitmq.controller;

import com.zj.rabbitmq.controller.direct.DirectAReceiver;
import com.zj.rabbitmq.controller.direct.DirectSender;
import com.zj.rabbitmq.controller.fanout.FanoutSender;
import com.zj.rabbitmq.controller.header.HeaderSender;
import com.zj.rabbitmq.controller.hello.Sender;
import com.zj.rabbitmq.controller.many.MSender;
import com.zj.rabbitmq.controller.topic.TopicSender;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 10:08
 * CopyRight: Zhouji
 */
@RestController
public class BasicController {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    Sender sender;

    @Autowired
    FanoutSender fanoutSender;

    @Autowired
    MSender mSender;

    @Autowired
    TopicSender topicSender;

    @Autowired
    DirectSender directSender;

    @RequestMapping("/hello")
    public String hello() {
        sender.send(5);
        return "SUCCESS";
    }


    @RequestMapping("/many")
    public String oneToMany() {
        for (int i=0;i<10;i++){
            mSender.send(i);
        }
        return "SUCCESS";
    }




    @RequestMapping("/topic")
    public String topicQueue() {
        topicSender.sendTopic1();
        topicSender.sendTopic2();
        return "SUCCESS";
    }


    @RequestMapping("/fanout")
    public String fanQueue() {
        fanoutSender.sendFanout();
        return "SUCCESS";
    }



    @RequestMapping("/direct")
    public String directQueue() {
        directSender.sendADirect();
        directSender.sendB1Direct();
        directSender.sendB2Direct();
        return "SUCCESS";
    }


    @Autowired
    HeaderSender headerSender;

    @RequestMapping("/head")
    public String headQueue() {
        headerSender.send();
        return "SUCCESS";
    }
}
