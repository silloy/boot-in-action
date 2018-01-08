package com.zj.rockmq.mq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/3
 * Time: 16:47
 * CopyRight: Zhouji
 */
public class RocketMQListener  implements MessageListenerConcurrently {

    public final SimpleDateFormat df = ThreadLocal.withInitial(() -> new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")).get();

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//        System.out.println("get data from rocketMQ:" + msgs);
        for (MessageExt message : msgs) {

            String msg = new String(message.getBody());
            System.out.println("msg data from rocketMQ:" + msg + ", time=" + df.format(new Date()));
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}