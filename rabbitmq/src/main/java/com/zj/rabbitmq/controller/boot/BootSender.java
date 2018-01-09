package com.zj.rabbitmq.controller.boot;

import com.zj.rabbitmq.RabbitmqApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 20:23
 * CopyRight: Zhouji
 */
@Component
public class BootSender implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final BootReceiver receiver;
    private final ConfigurableApplicationContext context;

    public BootSender(BootReceiver receiver, RabbitTemplate rabbitTemplate,
                      ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Starter.queueName, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        context.close();
    }

}
