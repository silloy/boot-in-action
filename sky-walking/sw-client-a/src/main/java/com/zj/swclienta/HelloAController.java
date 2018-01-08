package com.zj.swclienta;

import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/infos")
    public String info(){
        log.info("calling trace service-A ");
        return "i'm service-A";
    }



    @Trace
    @RequestMapping("/callB")
    public String callHome(ModelAndView modelAndView){
        modelAndView.addObject("traceId", TraceContext.traceId());
        log.info("calling trace service-hi=====>" + "traceId: " + TraceContext.traceId() + "<========>" + System.currentTimeMillis());
        return restTemplate.getForObject("http://localhost:9004/infos", String.class);
    }


}
