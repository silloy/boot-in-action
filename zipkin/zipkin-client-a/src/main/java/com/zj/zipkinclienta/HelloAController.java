package com.zj.zipkinclienta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
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
public class HelloAController {
    private static final Logger log = LoggerFactory.getLogger(HelloAController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Tracer tracer;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/infos")
    public String info(){
        log.info("calling trace service-A ");
        return "i'm service-A";
    }

    @RequestMapping("/callB")
    public String callHome(){
        tracer.addTag("ad", "hello");
        log.info("calling trace service-hi=====>" + System.currentTimeMillis());
        return restTemplate.getForObject("http://localhost:9002/infos", String.class);
    }


}
