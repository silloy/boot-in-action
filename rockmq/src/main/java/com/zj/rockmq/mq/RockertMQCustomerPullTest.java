package com.zj.rockmq.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.rocketmq.client.consumer.PullStatus.*;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/4
 * Time: 19:57
 * CopyRight: Zhouji
 */
public class RockertMQCustomerPullTest {

    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();

    /**
     * 主动拉取方式消费
     *
     * @throws MQClientException
     */
    public static void main(String[] args) throws MQClientException {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一 ,最好使用服务的包名区分同一服务,一类Consumer集合的名称，
         * 这类Consumer通常消费一类消息，且消费逻辑一致
         * PullConsumer：Consumer的一种，应用通常主动调用Consumer的拉取消息方法从Broker拉消息，主动权由应用控制
         */
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("ConsumerGroupName");
        // //nameserver服务
        consumer.setNamesrvAddr("192.168.89.200:9876");
        consumer.setInstanceName("Consumber");
        consumer.start();
        // 拉取订阅主题的队列，默认队列大小是4
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicTest1");
        for (MessageQueue mq : mqs) {
            System.out.println("Consume from the queue: " + mq);
            SINGLE_MQ: while (true) {
                try {
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    List<MessageExt> list = pullResult.getMsgFoundList();
                    if (list != null && list.size() < 100) {
                        for (MessageExt msg : list) {
                            System.out.println(new String(msg.getBody()));
                        }
                    }
                    System.out.println(pullResult.getNextBeginOffset());
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        consumer.shutdown();
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null) {
            System.out.println(offset);
            return offset;
        }
        return 0;
    }

}
