package com.goteny.melo.http;

/**
 * Created by Jankey on 2017/6/21.
 */

public interface HttpCallback<T>
{
    void onSuccess(T responseModel);
    void onFinish();
    void onFailure(Throwable t);
}
