package com.zj.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/13
 * Time: 20:55
 * CopyRight: Zhouji
 */

@Component
public class ApplicationContextMetrics implements PublicMetrics {

    private ApplicationContext context;


    @Autowired
    public ApplicationContextMetrics(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = Lists.newArrayList();
        metrics.add(new Metric<Integer>("spring.beans.definitions", context.getBeanDefinitionCount()));
        metrics.add(new Metric<Integer>("spring.beans", context.getBeanNamesForType(Object.class).length));
        metrics.add(new Metric<Integer>("spring.controllers", context.getBeanNamesForAnnotation(Controller.class).length));
        return metrics;
    }
}
