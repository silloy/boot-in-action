package com.zj.rockmq.mq;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/3
 * Time: 16:51
 * CopyRight: Zhouji
 */
public class RocketMQConsumerTest {
    /**
     *
     RocketMQ的重复问题解决方式：
     a.MQ的消费端执行的操作具有幂等性，即无论多少次重复执行，其结果是一样的；
     b.MQ的消费端做重复校验，比如将受到MQ消息的唯一编号保存到Redis中，即每次收到消息时，将检查唯一编号是否已经在Redis中，如果存在说明消息重复；
     否则将唯一编号放入到Redis中，可以根据系统需要设置唯一编号在Redis中的过期时间，以防止Redis溢出。
     */
    public static void main(String[] args) {


        String mqNameServer = "192.168.89.200:9876";
        String mqTopics = "MQ-MSG-TOPICS-TEST";

        String consumerMqGroupName = "CONSUMER-MQ-GROUP";
        RocketMQListener mqListener = new RocketMQListener();
        RocketMQConsumer mqConsumer = new RocketMQConsumer(mqListener, mqNameServer, consumerMqGroupName, mqTopics);
        mqConsumer.init();


        try {
            Thread.sleep(1000 * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
