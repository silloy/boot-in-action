package com.zj.swclientb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/21
 * Time: 11:50
 * CopyRight: Zhouji
 */
@RestController
public class HelloBController {


    private static final Logger log = LoggerFactory.getLogger(HelloBController.class);


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @RequestMapping("/infos")
    public String home(){
        log.info("hi is being called ======>" + System.currentTimeMillis());
        return "hi i'm B!";
    }

    @RequestMapping("/callA")
    public String info(){
        log.info("info is being called ======>" + System.currentTimeMillis());
        return restTemplate.getForObject("http://localhost:9003/infos",String.class);
    }



}
