# spring-boot-starter-webmagic

## 根据 [webmagic](https://github.com/code4craft/webmagic) 0.7.4 做的一个spring-boot-starter封装,并加入以下特性。

- 去掉slf4j,以spring boot 的logback作为日志引擎

- IErrorCodeHandler : 当页面下载返回错误代码时的处理接口

## 一些默认实现

- SilentPipeline.java 可以覆盖ConsolePipeline
- UserAgent.java 常用的UserAgent 信息
- WebmagicProxyProperties.java : 提供yaml 配置代理信息


## 2021.06.21
- 新增TimerSpider.java:用于与定时执行服务结合的抽象爬虫父类,使用demo

```java
@Component
public class MyTimerSpider extends TimerSpider{

    /**
     * 实现spider 实例的初始化
     * @return
     */
    @Override
    public Spider init() {
        Spider spider = Spider.create(this);
        spider.setPipelines(ListUtil.toList(new SilentPipeline()));
        spider.thread(100);
        return spider;
    }

    @Override
    public Map<String, Object> startTimer(Map<String, Object> param) throws Exception {
        Spider spider = getInstance(); //获取单例
        //spider.addUrl("https://www.baidu.com"); // 单例情况下,添加的url会排重，爬取过的url不会再爬取,如需反复爬取,重实现Schedule 或url后添加时间戳
        if(! spider.getStatus().equals(Spider.Status.Running)){ //判断是否running状态
            spider.run();
        }
        return param;
    }

    @Override
    public void process(Page page) {
        System.out.println();
        //解析页面
    }

    public static void main(String[] args) throws Exception {
        MyTimerSpider spider = new MyTimerSpider();
        spider.startTimer(null);
    }
}
```

```java
//在使用timer的实现类

import javax.annotation.Resource;

@Resource
MyTimerSpider spider;
//SpringContextUtils.getBean(MyTimerSpider.class)

spider.startTimer(null);

```

持续更新。

