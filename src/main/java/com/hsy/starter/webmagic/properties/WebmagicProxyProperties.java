package com.hsy.starter.webmagic.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "spider.proxy")
public class WebmagicProxyProperties implements Serializable {

    boolean enable = false;

    String host;

    int port;

    String username;

    String password;
}