package com.goteny.melo.http.interfaces;

/**
 * Created by Jankey on 2017/6/21.
 */

public interface HttpCallback<T>
{
    void onSuccess(T response);
    void onFailure(Throwable t);
}
