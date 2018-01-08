package com.zj.rabbitmq.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/5
 * Time: 20:17
 * CopyRight: Zhouji
 */
@Configuration
public class RabbitConfig {

    public static final String helloQueue = "hello";
    public static final String worldQueue = "world";
    public static final String otmQueue = "otm";
    public static final String objQueue = "obj";

    public static final String routeKey = "hello";


    @Bean
    public Queue helloQueue() {
        return new Queue(helloQueue);
    }

    @Bean
    public Queue worldQueue() {
        return new Queue(worldQueue);
    }


    @Bean
    public Queue otmQueue() {
        return new Queue(otmQueue);
    }

    @Bean
    public Queue objQueue() {
        return new Queue(objQueue);
    }

}
