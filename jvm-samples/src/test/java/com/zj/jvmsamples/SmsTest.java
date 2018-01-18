package com.zj.jvmsamples;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/18
 * Time: 11:38
 * Description: MAIN
 */
public class SmsTest {
    @Test
    public void sendMsg() throws Exception{
        String url = "";
        String appkey = "";
        String secret = "";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("阿里大于");
        req.setSmsParamString("{\"code\":\"1234\",\"product\":\"alidayu\"}");
        req.setRecNum("13000000000");
        req.setSmsTemplateCode("SMS_585014");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
}
