package com.zj.rabbitmq.controller;

import com.zj.rabbitmq.controller.timing.ProcessReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static com.zj.rabbitmq.controller.FanoutRabbitConfig.fanoutExchange;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 14:18
 * CopyRight: Zhouji
 * 定时消息测试
 * 死信消息:
 * 消息被拒绝（basic.reject or basic.nack）并且requeue=false
 * 消息过期
 * 队列长度超过限制
 * 有了DLX，就可以将需要延迟的操作设置下次执行时间（如消息的TTL时间）放入一个存储队列中，消息过期后会经由DLX进入监听的队列中。有消费方进行相关的操作，结束或者再次进入存储队列中。
 */
@Configuration
public class TimingRabbitConfig {

    public static final String taQueue = "tQueueA";
    public static final String timingExange = "timingExchange";
    public static final String timingRoute = "timingRoute";

    @Bean //(name = "queueA")
    public Queue queueA() {
        return new Queue(taQueue);
    }

    @Bean
    DirectExchange headersExchange() {
        Map<String, Object> headers = new Hashtable<>();
        headers.put("x-message-ttl", 60000);  // 60 seconds

        // 一旦消息失效，则消息会被自动转发到你设置的 x-dead-letter-exchange 上的同名队列
        headers.put("x-dead-letter-exchange", fanoutExchange);
//        headers.put("x-dead-letter-routing-key", "some-routing-key");
        return new DirectExchange(timingExange, true, false, headers);
    }

    @Bean                // @Qualifier("queueA")
    Binding bindingExchange(Queue queueA, DirectExchange headersExchange) {
        return BindingBuilder.bind(queueA).to(headersExchange).with(timingRoute);
    }





    /*****************delayqueue******************/

    /**
     * 发送消息到 routingKey: DELAY_QUEUE_PER_MESSAGE_TTL_NAME_A
     * 由队列 PROCESS_QUEUE 处理
     */

    public final static String DELAY_QUEUE_PER_MESSAGE_TTL_NAME_A = "delay_queue_per_message_ttl";

    public final static String PROCESS_QUEUE = "delay_process_queue";

    public final static String DELAY_EXCHANGE = "delay_exchange";


    @Bean
    Queue delayQueuePerMessageTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_MESSAGE_TTL_NAME_A)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE) // DLX，dead letter发送到的exchange
                .withArgument("x-dead-letter-routing-key", PROCESS_QUEUE) // dead letter携带的routing key
                .build();
    }

    @Bean
    Queue delayProcessQueue() {
        return QueueBuilder.durable(PROCESS_QUEUE)
                .build();
    }

    @Bean
    Binding dlxBinding(Queue delayProcessQueue) {
        return BindingBuilder.bind(delayProcessQueue)
                .to(new DirectExchange(DELAY_EXCHANGE))
                .with(PROCESS_QUEUE);
    }



    /*****************delayqueueTimin******************/

    /**
     * 发送消息到 routingKey: DELAY_QUEUE_PER_MESSAGE_TTL_NAME_A
     * 由队列 PROCESS_QUEUE 处理
     */
    public final static String DELAY_QUEUE_PER_QUEUE_TTL_NAME_A = "delay_queue_per_queue_ttl";
    public final static int QUEUE_EXPIRATION = 4000;
    public final static String PER_QUEUE_TTL_EXCHANGE_NAME = "per_queue_ttl_exchange";

    @Bean
    DirectExchange perQueueTTLExchange() {
        return new DirectExchange(PER_QUEUE_TTL_EXCHANGE_NAME);
    }

    @Bean
    Queue delayQueuePerQueueTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_TTL_NAME_A)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE) // DLX
                .withArgument("x-dead-letter-routing-key", PROCESS_QUEUE) // dead letter携带的routing key
                .withArgument("x-message-ttl", QUEUE_EXPIRATION) // 设置队列的过期时间
                .build();
    }

    @Bean
    Binding queueTTLBinding(Queue delayQueuePerQueueTTL, DirectExchange perQueueTTLExchange) {
        return BindingBuilder.bind(delayQueuePerQueueTTL)
                .to(new DirectExchange(PER_QUEUE_TTL_EXCHANGE_NAME))
                .with(DELAY_QUEUE_PER_QUEUE_TTL_NAME_A);
    }


    /**
     * 与ProcessReceiver配合使用，如果不适用该方法，可以使用注解 @RabbitListener 和 @RabbitHandler 注解处理  PROCESS_QUEUE
     * @param connectionFactory
     * @param processReceiver
     * @return
     */
    @Bean
    SimpleMessageListenerContainer processContainer(ConnectionFactory connectionFactory, ProcessReceiver processReceiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(PROCESS_QUEUE);
        container.setMessageListener(new MessageListenerAdapter(processReceiver));
        return container;
    }


}
