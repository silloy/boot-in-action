package com.zj.jvmsamples.controller;

import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/17
 * Time: 20:19
 * CopyRight: Zhouji
 * <p>volatile变量自增运算测试</p>
 */
public class ValatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        IntStream.range(0, THREADS_COUNT).forEach(i -> {
            new Thread(() -> {
                IntStream.range(0, 10000).forEachOrdered(j -> increase());
            }).start();
        });


        // 等待所用累积线程都结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
