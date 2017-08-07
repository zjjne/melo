package com.goteny.melo.http;

/**
 * Created by Jankey on 2017/6/19.
 */

public interface HttpCoreCallback
{
    void onSuccess(String body);
    void onFailure();
}
