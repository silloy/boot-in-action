package com.zj.rabbitmq.controller;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/6
 * Time: 19:51
 * CopyRight: Zhouji
 *
 *
 1.一般情况可以使用rabbitMQ自带的Exchange：”"(该Exchange的名字为空字符串，下文称其为default Exchange)。
 2.这种模式下不需要将Exchange进行任何绑定(binding)操作
 3.消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
 4.如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
 */
@Configuration
public class DirectRabbitConfig {


    public static final String directExchange = "directExchange";


    public static final String daQueue = "directA";
    public static final String dbQueue = "directB";

    public static final String orangeRoutingKey = "direct.orange";
    public static final String blankRoutingKey = "direct.black";
    public static final String greenRoutingKey = "direct.green";

    @Bean
    public Queue daMessage() {
        return new Queue(daQueue, true, false, false);
    }

    @Bean
    public Queue dbMessage() {
        return new Queue(dbQueue, true, false, false);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(directExchange, true, false);
    }

    @Bean
    Binding bindingMessage (Queue daMessage, DirectExchange directExchange) {
        return BindingBuilder.bind(daMessage).to(directExchange).with(orangeRoutingKey);
    }


    @Bean
    Binding blankBind (Queue dbMessage, DirectExchange directExchange) {
        return BindingBuilder.bind(dbMessage).to(directExchange).with(blankRoutingKey);
    }

    @Bean
    Binding greenBind (Queue dbMessage, DirectExchange directExchange) {
        return BindingBuilder.bind(dbMessage).to(directExchange).with(greenRoutingKey);
    }

}
