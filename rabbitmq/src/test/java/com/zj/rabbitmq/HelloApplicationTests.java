package com.zj.rabbitmq;

import com.zj.rabbitmq.controller.direct.DirectSender;
import com.zj.rabbitmq.controller.fanout.FanoutSender;
import com.zj.rabbitmq.controller.header.HeaderSender;
import com.zj.rabbitmq.controller.hello.Sender;
import com.zj.rabbitmq.controller.many.MSender;
import com.zj.rabbitmq.controller.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/5
 * Time: 20:21
 * CopyRight: Zhouji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitmqApplication.class)
public class HelloApplicationTests {

    @Autowired
    Sender sender;

    @Autowired
    FanoutSender fanoutSender;

    @Autowired
    MSender mSender;

    @Autowired
    TopicSender topicSender;

    @Autowired
    DirectSender directSender;


    @Test
    public void hello() throws Exception {
        sender.send(5);
    }

    /**
     * 不能保证FIFO, 平均分配到几个receiver中
     * @throws Exception
     */
    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<10;i++){
            mSender.send(i);
        }
    }



    @Test
    public void topicQueue() throws Exception {
        topicSender.sendTopic1();
        topicSender.sendTopic2();
    }

    @Test
    public void fanQueue() {
        fanoutSender.sendFanout();
    }

    @Test
    public void directQueue() {
        directSender.sendADirect();
        directSender.sendB1Direct();
        directSender.sendB2Direct();
    }

    @Autowired
    HeaderSender headerSender;

    @Test
    public void headerQueue() {
        headerSender.send();
    }

}
