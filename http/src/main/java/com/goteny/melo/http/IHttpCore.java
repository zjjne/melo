package com.goteny.melo.http;


/**
 * Created by Jankey on 2017/6/19.
 */

public interface IHttpCore
{
    CookieStore mCookieStore = new CookieStore();

    /**
     * 发送请求
     * @param request
     * @param coreCallback
     */
    void send(Request request, HttpCoreCallback coreCallback);
}
