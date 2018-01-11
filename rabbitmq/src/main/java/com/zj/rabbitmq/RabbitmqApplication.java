package com.zj.rabbitmq;

import com.zj.rabbitmq.controller.hello.Sender;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.zj")
@EnableAdminServer
public class RabbitmqApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(RabbitmqApplication.class, args);

		Sender sender = app.getBean(Sender.class);
		sender.send(5);
    }


}
