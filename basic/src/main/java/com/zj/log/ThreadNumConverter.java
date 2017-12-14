package com.zj.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.lang.management.ManagementFactory;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/12
 * Time: 16:22
 * CopyRight: Zhouji
 */
public class ThreadNumConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
//        return String.valueOf(Thread.currentThread().getId());
        return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    }
}
