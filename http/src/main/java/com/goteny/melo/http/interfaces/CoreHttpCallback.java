package com.goteny.melo.http.interfaces;

/**
 * Created by Jankey on 2017/6/19.
 */

public interface CoreHttpCallback
{
    void onSuccess(String body);
    void onFailure(Throwable throwable);
}
