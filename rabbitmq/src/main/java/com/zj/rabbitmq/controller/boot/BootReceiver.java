package com.zj.rabbitmq.controller.boot;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/8
 * Time: 20:19
 * CopyRight: Zhouji
 */
@Component
public class BootReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
