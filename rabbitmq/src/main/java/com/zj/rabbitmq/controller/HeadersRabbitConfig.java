package com.zj.rabbitmq.controller;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 14:18
 * CopyRight: Zhouji
 Headers类型的exchange使用的比较少，它也是忽略routingKey的一种路由方式。是使用Headers来匹配的。Headers是一个键值对，
 可以定义成Hashtable。发送者在发送的时候定义一些键值对，接收者也可以再绑定时候传入一些键值对，两者匹配的话，则对应的队列就可以收到消息。匹配有两种方式all和any。
 这两种方式是在接收端必须要用键值"x-mactch"来定义。all代表定义的多个键值对都要满足，而any则代码只要满足一个就可以了。
 */
@Configuration
public class HeadersRabbitConfig {

    public static final String haQueue = "headerQueueA";
    public static final String headersExchange = "headersExchange";

    @Bean //(name = "queueA")
    public Queue queueA() {
        return new Queue(haQueue);
    }

    @Bean
    HeadersExchange headersExchange() {
        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        //设置消息头键值对信息
        Map<String, Object> headers = new Hashtable<>();
        //这里x-match有两种类型
        //all:表示所有的键值对都匹配才能接受到消息
        //any:表示只要有键值对匹配就能接受到消息
        headers.put("x-match", "any");
        headers.put("name", "jobs");
//        headers.put("age", 31);
        return new HeadersExchange(headersExchange, durable, autoDelete);
    }

    @Bean                   // @Qualifier("queueA")
    Binding bindingExchange(Queue queueA, HeadersExchange headersExchange) {
        return BindingBuilder.bind(queueA).to(headersExchange).whereAll("name").exist();
    }
}
