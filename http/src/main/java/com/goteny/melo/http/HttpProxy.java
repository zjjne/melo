package com.goteny.melo.http;


import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 接口代理类
 * <p>
 * 创建API接口代理对象，通过代理取得接口方法参数值和返回值，
 * 从而构建单次请求的BaseRequest对象
 * <p>
 * Created by Jankey on 2017/6/20.
 */

@SuppressWarnings("unchecked")
public class HttpProxy implements InvocationHandler
{

    private static HttpProxy mInstance;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Log.i("HttpProxy", "method toGenericString:" + method.toGenericString());
        Log.i("HttpProxy", "method name:" + method.getName());

        return RequestHandler.createRequest(method, args);
    }

    public static HttpProxy getInstance()
    {
        if (mInstance == null)
            mInstance = new HttpProxy();

        return mInstance;
    }

    private HttpProxy()
    {

    }

    /**
     * 创建API接口代理对象
     *
     * @param api
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> api)
    {
        //创建代理对象
        return (T) Proxy.newProxyInstance(api.getClassLoader(), new Class<?>[]{api}, this);
    }

}
