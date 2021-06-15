package com.hsy.starter.webmagic.processor;

import org.apache.http.cookie.Cookie;

import java.util.List;
import java.util.Map;

/**
 * 标准化的流程处理
 */
public interface IStandardProcessor {



        /**
         * 登录并获取cookies
         *
         * @return
         */
        public List<Cookie> LoginAndGetCookies();

        /**
         * 登出
         *
         * @return
         */
        public boolean LoginOut();

        /**
         * 开启爬取
         *
         * @param param
         * @return
         */
        public Integer start(Map<String, Object> param) throws Exception;

}
