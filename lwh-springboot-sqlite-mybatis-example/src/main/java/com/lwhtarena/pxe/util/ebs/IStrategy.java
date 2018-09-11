package com.lwhtarena.pxe.util.ebs;


/**
 * @author： liwh
 * @Date: 2017/4/1.
 * @Description：<p>策略接口</P>
 */
public interface IStrategy {

    /*校验ip是否可用*/
    boolean checkIp(String ip) throws Exception;

    @Deprecated
    String postRest(String url, String postBody) throws Exception;
    String postRest(String ip, String url, String postBody) throws Exception;


}
