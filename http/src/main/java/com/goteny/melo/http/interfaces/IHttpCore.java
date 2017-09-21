package com.goteny.melo.http.interfaces;


import com.goteny.melo.http.CallbackStore;
import com.goteny.melo.http.CookieStore;
import com.goteny.melo.http.CoreCallbakcs;
import com.goteny.melo.http.Request;

/**
 * Created by Jankey on 2017/6/19.
 */

public interface IHttpCore
{
    CookieStore mCookieStore = new CookieStore();
    CallbackStore mCallbackStore = new CallbackStore();

    /**
     * 发送请求
     * @param request 请求内容对象
     * @param callbakcs 核心内部回调
     */
    void send(Request request, CoreCallbakcs callbakcs);
}
