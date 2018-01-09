package com.zj.rabbitmq.controller.timing;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/9
 * Time: 13:31
 * CopyRight: Zhouji
 */
@Data
public class DLXMessage implements Serializable {
    public DLXMessage() {
        super();
    }

    public DLXMessage(String queueName, String content, long times) {
        super();
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }

    public DLXMessage(String exchange, String queueName, String content, long times) {
        super();
        this.exchange = exchange;
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }


    private String exchange;

    private String queueName;

    private String content;

    private long times;
}
