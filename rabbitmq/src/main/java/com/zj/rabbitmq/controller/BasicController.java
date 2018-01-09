package com.zj.rabbitmq.controller;

import com.zj.rabbitmq.controller.direct.DirectSender;
import com.zj.rabbitmq.controller.fanout.FanoutSender;
import com.zj.rabbitmq.controller.header.HeaderSender;
import com.zj.rabbitmq.controller.hello.Sender;
import com.zj.rabbitmq.controller.many.MSender;
import com.zj.rabbitmq.controller.timing.ProcessReceiver;
import com.zj.rabbitmq.controller.timing.ProcessSender;
import com.zj.rabbitmq.controller.timing.TimingSender;
import com.zj.rabbitmq.controller.topic.TopicSender;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static com.zj.rabbitmq.controller.timing.demoConfig.QueueConfig.DELAY_QUEUE_PER_MESSAGE_TTL_NAME;

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


    @Autowired
    TimingSender timingSender;

    @RequestMapping("/timing")
    public String timingQueue() {
        timingSender.send();
        return "SUCCESS";
    }



    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/delay")
    public String perMessageTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            System.out.println(new Date() + " sender: " + i);
            long expiration = i * 1000;
            rabbitTemplate.convertAndSend(DELAY_QUEUE_PER_MESSAGE_TTL_NAME,
                    (Object) ("Message From delay_queue_per_message_ttl with expiration " + expiration), new ProcessSender(expiration));
        }
        ProcessReceiver.latch.await();
        return "SUCCESS";
    }
}
