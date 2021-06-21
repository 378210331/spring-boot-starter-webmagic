package com.hsy.starter.webmagic.common;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

/**
 * 用于与定时执行服务结合的抽象爬虫父类
 */
public abstract class TimerSpider implements PageProcessor {

    private Spider spider = null;

    public abstract Spider init();

    public abstract Map<String,Object> startTimer(Map<String,Object> param) throws Exception;

    public Site getSite(){
        return Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(5000);
    }

    /**
     * 获取spider实例
     * @return
     */
    public Spider getInstance(){
        if(spider == null){
            synchronized (this){
                if(spider == null) {
                    spider = init();
                }
            }
        }
        return spider;
    }

}
