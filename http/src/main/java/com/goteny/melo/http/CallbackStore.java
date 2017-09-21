package com.goteny.melo.http;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jankey on 2017/9/12.
 */

public class CallbackStore
{
    private final Map<String, CoreCallbakcs> callbacksMap;

    public CallbackStore()
    {
        callbacksMap = new ConcurrentHashMap<>();
    }

    public void addCallbacks(String url, CoreCallbakcs callbacks)
    {
        if (callbacksMap.containsKey(url))
            callbacksMap.remove(url);

        callbacksMap.put(url, callbacks);
    }


    public CoreCallbakcs getCallbacks(String url)
    {
        CoreCallbakcs result = null;

        if (callbacksMap.containsKey(url))
            result = callbacksMap.get(url);

        return result;
    }


    public void removeCallbacks(String url)
    {
        if (callbacksMap.containsKey(url))
        {
            callbacksMap.remove(url);
        }
    }


    public void removeAllCallbacks()
    {
        callbacksMap.clear();
    }
}
