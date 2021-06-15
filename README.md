# spring-boot-starter-webmagic

## 根据 [webmagic](https://github.com/code4craft/webmagic) 0.7.4 做的一个spring-boot-starter封装,并加入以下特性。

- 去掉slf4j,以spring boot 的logback作为日志引擎

- IErrorCodeHandler : 当页面下载返回错误代码时的处理接口

## 一些默认实现

- SilentPipeline.java 可以覆盖ConsolePipeline
- UserAgent.java 常用的UserAgent 信息
- WebmagicProxyProperties.java : 提供yaml 配置代理信息

持续更新。

