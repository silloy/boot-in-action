package com.zj.rockmq.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/3
 * Time: 16:48
 * CopyRight: Zhouji
 */
public class RocketMQProducer {

    private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";

    public final SimpleDateFormat df = ThreadLocal.withInitial(() -> new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")).get();


    private DefaultMQProducer sender;

    protected String nameServer;

    protected String groupName;

    protected String topics;

    public void init() {
        sender = new DefaultMQProducer(groupName);
        sender.setNamesrvAddr(nameServer);
        sender.setInstanceName(UUID.randomUUID().toString());
//        sender.setRetryTimesWhenSendAsyncFailed(3);
        try {
            sender.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public RocketMQProducer(String nameServer, String groupName, String topics) {
        this.nameServer = nameServer;
        this.groupName = groupName;
        this.topics = topics;
    }

    public void send(Message message) {

        message.setTopic(topics);

        try {
            message.setDelayTimeLevel(6);
            SendResult result = sender.send(message);
            SendStatus status = result.getSendStatus();
            System.out.println("messageId=" + result.getMsgId() + ", status=" + status + ", time=" + df.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
