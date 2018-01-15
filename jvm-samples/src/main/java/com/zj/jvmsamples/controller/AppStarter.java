package com.zj.jvmsamples.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/12
 * Time: 11:44
 * CopyRight: Zhouji
 */
@Component
public class AppStarter {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        System.out.println(list.stream().map(i -> getNewList(i)).flatMap(Collection::stream).collect(Collectors.toList()));
    }


    private static List<String> getNewList(Integer i) {
        List<String> abc = new ArrayList<>();
        abc.add("t");
        abc.add(String.valueOf(i));
        return abc;
    }
}
