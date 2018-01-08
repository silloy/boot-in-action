package com.zj.rabbitmq.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/6
 * Time: 20:14
 * CopyRight: Zhouji
 */
@Data
public class User implements Serializable {
    private String name;
    private Integer age;
}
