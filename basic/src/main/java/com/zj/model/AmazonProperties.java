package com.zj.model;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/12
 * Time: 13:59
 * CopyRight: Zhouji
 */
@Component
@ConfigurationProperties(prefix = "amazon")
public class AmazonProperties {
    @Getter private String associateId;

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
}
