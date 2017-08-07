package com.goteny.melo.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by Jankey on 2017/6/21.
 */

@SuppressWarnings("unchecked")
public class BaseHttp<T> implements IHttp<T>, HttpCoreCallback
{
    protected Request request;
    protected HttpCallback<T> callback;
    private Class<T> clss;

    @Override
    public void execute()
    {
        callCore();
    }

    @Override
    public void execute(HttpCallback<T> callback)
    {
        this.callback = callback;
        execute();
    }

    private void callCore()
    {
        Log.i("HttpRequest", "url:" + request.getUrl());
        Log.i("HttpRequest", "requestType:" + request.getRequestType());
        Log.i("HttpRequest", "bodyContentType:" + request.getBodyContentType());

        Log.i("Http", "XsHttpUtil.getInstance(context).fetchIndex(modelNew) before okhttp");

        OkhttpUtil.getInstance().send(request, this);
    }

    @Override
    public void onSuccess(String body)
    {
        Log.v("LOG_HTTP_RESPONSE11111", "返回结果:\n" + body);

        try
        {
            Gson gson = new Gson();
            T t = gson.fromJson(body, clss);

            if (callback != null) callbackSuccess(t);

        }catch (Throwable e)
        {
            Log.v("LOG_HTTP_RESPONSE1111", e.toString());
        }
    }



    @Override
    public void onFailure()
    {
        if (callback != null) callbackFailure();
    }




    private void callbackSuccess(final T t)
    {
        postRunable(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onSuccess(t);
            }
        });
    }


    private void callbackFailure()
    {
        postRunable(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onFailure(null);
            }
        });
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private void postRunable(Runnable r)
    {
        mHandler.post(r);
    }


    public Request getRequest()
    {
        return request;
    }

    public void setRequest(Request request)
    {
        this.request = request;
    }

    public void setClss(Class<T> clss)
    {
        this.clss = clss;
    }
}
