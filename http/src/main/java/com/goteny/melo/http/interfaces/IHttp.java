package com.goteny.melo.http.interfaces;


/**
 *
 * Created by Jankey on 2017/6/20.
 */

public interface IHttp<T> extends Executable
{
    IHttp callback(HttpCallback<T> callback);
}
