package com.zj.rabbitmq.controller;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.zj.rabbitmq.controller.TimingRabbitConfig.taQueue;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/6
 * Time: 20:03
 * CopyRight: Zhouji
 * <p>Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。</p>
 *
 1.可以理解为路由表的模式

 2.这种模式不需要RouteKey

 3.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定。

 4.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃
 */
@Configuration
public class
FanoutRabbitConfig {
    public static final String aQueue = "fanout.AA";
    public static final String bQueue = "fanout.BB";
    public static final String cQueue = "fanout.CC";
    public static final String fanoutExchange = "fanoutExchange";


    @Bean
    public Queue AMessage() {
        return new Queue(aQueue);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(bQueue);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(cQueue);
    }


    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

    @Bean
    Binding timingExchangeC(FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(new Queue(taQueue)).to(fanoutExchange);
    }

}
