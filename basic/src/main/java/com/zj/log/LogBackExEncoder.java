package com.zj.log;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/12
 * Time: 16:22
 * CopyRight: Zhouji
 */
public class LogBackExEncoder extends PatternLayoutEncoder {
    static {
        PatternLayout.defaultConverterMap.put("T", ThreadNumConverter.class.getName());
        PatternLayout.defaultConverterMap.put("threadNum", ThreadNumConverter.class.getName());
    }
    @Override
    public void doEncode(ILoggingEvent event) throws IOException {
        super.doEncode(event);
    }
}