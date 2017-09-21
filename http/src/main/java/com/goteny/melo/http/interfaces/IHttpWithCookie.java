package com.goteny.melo.http.interfaces;


/**
 *
 * Created by Jankey on 2017/9/12.
 */

public interface IHttpWithCookie<T> extends IHttp<T>, ICookie
{
    @Override
    IHttpWithCookie callback(HttpCallback<T> callback);

    @Override
    IHttpWithCookie callback(CookieCallback callback);
}
