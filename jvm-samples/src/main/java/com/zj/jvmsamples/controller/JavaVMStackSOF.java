package com.zj.jvmsamples.controller;

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

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            testAllocation();
        } catch (Throwable e) {
            System.out.println("stack Length: " + oom.stackLength);
            throw e;
        }
    }


    // vm args: -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * 1024];
        a2 = new byte[2 * 1024];
        a3 = new byte[2 * 1024];
        a4 = new byte[4 * 1024]; // 出现一次MinorGC
    }
}
