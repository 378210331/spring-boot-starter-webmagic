package com.hsy.starter.webmagic.config;

import com.hsy.starter.webmagic.properties.WebmagicProxyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WebmagicProxyProperties.class)
public class WebmagicAutoConfig {
}
