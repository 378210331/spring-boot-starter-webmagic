package com.hsy.starter.webmagic.downloader;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.sun.istack.internal.tools.DefaultAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.InetSocketAddress;


@Slf4j
public class HutoolsDownloader extends HttpClientDownloader {

    @Override
    public Page download(Request request, Task task) {
        Site site = task.getSite();
        Page page = Page.fail();
        try{
            Proxy proxy = getProxyProvider() != null ? getProxyProvider().getProxy(task) : null;
            HttpRequest hutoolRequest = null;
            hutoolRequest = HttpUtil.createGet(request.getUrl()).charset(site.getCharset()).setReadTimeout(site.getTimeOut());
/*            if(proxy != null) {
                if(StringUtils.isNotBlank(proxy.getUsername()) && StringUtils.isNotBlank(proxy.getPassword()) ){
                    System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
                    System.setProperty("http.proxyUser",proxy.getUsername());
                    System.setProperty("http.proxyPassword",proxy.getPassword());
                    // 设置请求验证信息
                    Authenticator.setDefault(DefaultAuthenticator.getAuthenticator());
                }
                hutoolRequest.setProxy(new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress(proxy.getHost(),proxy.getPort())));
            }*/
            long start = System.currentTimeMillis();
            HttpResponse response = hutoolRequest.execute();
            log.debug("download page {} success,cost time {} ms",request.getUrl(),System.currentTimeMillis() - start);
            String  body = new String(response.bodyBytes(),site.getCharset());
            Html html = new Html(body,request.getUrl());
            page.setHtml(html);
            page.setStatusCode(response.getStatus());
            page.setRawText(body);
            page.setDownloadSuccess(true);
            page.setRequest(request);
            page.setCharset(site.getCharset());
            page.setUrl(new PlainText(request.getUrl()));
            page.setHeaders(response.headers());
            onSuccess(request);
            return page;
        }catch (UnsupportedEncodingException e){
            log.error("下载报错:"+e.getMessage());
            onError(request);
            return page;
        }
    }
}
