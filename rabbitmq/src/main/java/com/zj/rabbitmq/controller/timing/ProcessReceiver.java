package com.zj.rabbitmq.controller.timing;

import com.rabbitmq.client.Channel;
import com.zj.rabbitmq.controller.timing.demoConfig.QueueConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

@Component
//@RabbitListener(queues = DELAY_PROCESS_QUEUE_NAME_A)
public class ProcessReceiver implements ChannelAwareMessageListener {

    public static CountDownLatch latch;

    private static Logger logger = LoggerFactory.getLogger(ProcessReceiver.class);

    public static final String FAIL_MESSAGE = "This message will fail";

//    @RabbitHandler
    public void process(@Payload String message) {
        preProcess(message);
    }




    @Override
    public void onMessage(Message message, Channel channel) {
        logger.info("Received " + message.getMessageProperties().getMessageId() + " " + new Date()  +"<" + message.getBody() + ">");
    }


    public void preProcess(String message) {
        try {
            System.out.println(new Date() + " receive: " + message);
            processMessage(message);
        } catch (Exception e) {
             // 如果发生了异常，则将该消息重定向到缓冲队列，会在一定延迟之后自动重做
//            channel.basicPublish(QueueConfig.PER_QUEUE_TTL_EXCHANGE_NAME, QueueConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME, null,
//                    "The failed message will auto retry after a certain delay".getBytes());
        }
        if (latch != null) {
            latch.countDown();
        }
    }


    /**
     * 模拟消息处理。如果当消息内容为FAIL_MESSAGE的话，则需要抛出异常
     * @param message
     * @throws Exception
     */
    public void processMessage(String message) throws Exception {
        logger.info("Received " + new Date()  +"<" + message + ">");
        if (Objects.equals(message, FAIL_MESSAGE)) {
            throw new Exception("Some exception happened");
        }
    }
}
