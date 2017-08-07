package com.goteny.melo.http;


/**
 *
 * Created by Jankey on 2017/6/20.
 */

public interface IHttp<T>
{
    void execute();
    void execute(HttpCallback<T> callback);
}
