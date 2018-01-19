package com.zj;


import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/27
 * Time: 20:01
 * CopyRight: Zhouji
 */
@ThreadSafe
public class SynchronizedInteger {

    @GuardedBy("this") private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
