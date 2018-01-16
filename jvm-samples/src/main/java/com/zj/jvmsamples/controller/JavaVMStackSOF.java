package com.zj.jvmsamples.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/12
 * Time: 13:50
 * CopyRight: Zhouji
 * VM args: -Xss128k
 */
public class JavaVMStackSOF {
    private Integer stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws InterruptedException {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            fillHeap(10000);
        } catch (Throwable e) {
            System.out.println("stack Length: " + oom.stackLength);
            throw e;
        }
    }


    // vm args: -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a4 = new byte[4 * _1MB]; // 出现一次MinorGC
    }

    public static final int _1MB = 1024 * 1024;

    // vm args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
    // -XX:PretenureSizeThreshold=3145728
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[6 * _1MB];
    }

    // vm args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1
    // -XX:+PrintTenuringDistribution
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }


    static class OOMObjec {
        public byte[] placehplder = new byte[64 * 1024];
    }

    // vm args: -verbose:gc -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:gc.log
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObjec> list = new ArrayList<>();
        for (int i = 0; i < num; i++){
            Thread.sleep(1000);
            list.add(new OOMObjec());
        }
        System.gc();
    }


}
