package com.goteny.melo.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.goteny.melo.http.interfaces.CookieCallback;
import com.goteny.melo.http.interfaces.CoreCookieCallback;
import com.goteny.melo.http.interfaces.HttpCallback;
import com.goteny.melo.http.interfaces.CoreHttpCallback;
import com.goteny.melo.http.interfaces.IHttpWithCookie;
import com.goteny.melo.utils.log.LogMelo;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Cookie;

/**
 * Created by Jankey on 2017/6/21.
 */

@SuppressWarnings("unchecked")
public class BaseHttp<T> implements IHttpWithCookie<T>, CoreHttpCallback, CoreCookieCallback
{
    protected Request request;
    protected CookieCallback cookieCallback;
    protected HttpCallback<T> httpCallback;
    protected Type type;

    @Override
    public IHttpWithCookie callback(HttpCallback<T> callback)
    {
        this.httpCallback = callback;
        return this;
    }

    @Override
    public IHttpWithCookie callback(CookieCallback callback)
    {
        this.cookieCallback = callback;
        return this;
    }

    @Override
    public void execute()
    {
        callCore();
    }

    private void callCore()
    {
        LogMelo.i("url:" + request.getUrl());
        LogMelo.i("requestType:" + request.getRequestType());
        LogMelo.i("bodyContentType:" + request.getBodyContentType());

        CoreCallbakcs coreCallbakcs = new CoreCallbakcs(this, this);

        OkhttpUtil.getInstance().send(request, coreCallbakcs);
    }

    @Override
    public void onSuccess(String body)
    {
        LogMelo.v("返回结果:\n" + body);

        try
        {
            if (httpCallback != null)
            {
                Gson gson = new Gson();
                T t = gson.fromJson(body, type);

                callbackSuccess(t);
            }

        }catch (Throwable e)
        {
            LogMelo.i(e.toString());

            if (httpCallback != null) callbackFailure(e);
        }
    }



    @Override
    public void onFailure(Throwable throwable)
    {
        if (httpCallback != null) callbackFailure(throwable);
    }



    @Override
    public void cookies(List<Cookie> cookies)
    {
        if (cookieCallback != null) cookieCallback(cookies);
    }




    protected void cookieCallback(final List<Cookie> cookies)
    {
        if (cookieCallback == null) return;

        postRunable(new Runnable()
        {
            @Override
            public void run()
            {
                cookieCallback.cookies(cookies);
            }
        });
    }

    protected void callbackSuccess(final T t)
    {
        if (httpCallback == null) return;

        postRunable(new Runnable()
        {
            @Override
            public void run()
            {
                httpCallback.onSuccess(t);
            }
        });
    }


    protected void callbackFailure(final Throwable throwable)
    {
        postRunable(new Runnable()
        {
            @Override
            public void run()
            {
                httpCallback.onFailure(throwable);
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

    public void setType(Type type)
    {
        this.type = type;
    }

}
