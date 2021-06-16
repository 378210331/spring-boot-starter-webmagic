package com.hsy.starter.webmagic.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;


@Data
@ConfigurationProperties(prefix = "spider.proxy")
public class WebmagicProxyProperties implements Serializable {

    /**
     * 是否开启代理
     */
    boolean enable = false;

    /**
     * 代理的服务ip
     */
    String host;

    /**
     *  代理服务端口
     */
    Integer port;

    /**
     * 代理用户名
     */
    String username;

    /**
     * 代理密码
     */
    String password;
}
