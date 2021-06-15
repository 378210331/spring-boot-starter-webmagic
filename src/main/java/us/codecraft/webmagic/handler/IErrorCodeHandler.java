package us.codecraft.webmagic.handler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

/**
 * 爬虫爬取报错处理接口
 */
public interface IErrorCodeHandler {

    public void handle(Request request, Page page);

    public int getErrorCode();

}
