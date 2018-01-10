package com.zj.rabbitmq.controller.timing;

import com.zj.rabbitmq.controller.TimingRabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static com.zj.rabbitmq.controller.TimingRabbitConfig.DELAY_QUEUE_PER_MESSAGE_TTL_NAME_A;
import static com.zj.rabbitmq.controller.TimingRabbitConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME_A;
import static com.zj.rabbitmq.controller.TimingRabbitConfig.PROCESS_QUEUE;
import static com.zj.rabbitmq.controller.timing.demoConfig.QueueConfig.QUEUE_EXPIRATION;

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


    public void sendMessage() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            System.out.println(new Date() + " sender: " + i);
            long expiration = i * 1000;
            rabbitTemplate.convertAndSend(DELAY_QUEUE_PER_MESSAGE_TTL_NAME_A,
                    (Object) ("Message From delay_queue_per_message_ttl with expiration " + expiration), new ProcessSender(expiration));
        }
        ProcessReceiver.latch.await();
    }


    public void sendMessageTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            System.out.println(new Date() + " sender: " + i);
            rabbitTemplate.convertAndSend(DELAY_QUEUE_PER_QUEUE_TTL_NAME_A,
                    ("Message From delay_queue_per_queue_ttl with expiration " + QUEUE_EXPIRATION));
        }
        ProcessReceiver.latch.await();
    }


    public void testFailMessage() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(6);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(PROCESS_QUEUE, ProcessReceiver.FAIL_MESSAGE);
        }
        ProcessReceiver.latch.await();
    }


}
