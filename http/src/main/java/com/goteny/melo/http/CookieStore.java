package com.goteny.melo.http;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;

/**
 * Created by Jankey on 2017/8/1.
 */

public class CookieStore
{
    private final Map<String, List<Cookie>> cookiesMap;
    private final Map<String, Boolean> urlMap;

    public CookieStore()
    {
        cookiesMap = new ConcurrentHashMap<>();
        urlMap = new ConcurrentHashMap<>();
    }

    public void addCookies(String host, List<Cookie> cookies)
    {
        if (cookiesMap.containsKey(host))
            cookiesMap.remove(host);
        
        cookiesMap.put(host, cookies);
    }



    public List<Cookie> getCookies(String host)
    {
        List<Cookie> result = new ArrayList<>();

        if (cookiesMap.containsKey(host))
            result.addAll(cookiesMap.get(host));

        return result;
    }


    public void removeCookies(String host)
    {
        if (cookiesMap.containsKey(host))
        {
            cookiesMap.remove(host);
        }
    }



    public void removeAllCookies()
    {
        cookiesMap.clear();
    }



    public void addUrl(String url)
    {
        addUrl(url, false);
    }

    public void addUrl(String url, boolean enableCookies)
    {
        if (urlMap.containsKey(url))
            urlMap.remove(url);

        urlMap.put(url, enableCookies);
    }


    public boolean isEnableCookies(String url)
    {
        if (urlMap.containsKey(url))
            return urlMap.get(url);

        return false;
    }


    public void removeAllUrl()
    {
        urlMap.clear();
    }

    public void removeAll()
    {
        cookiesMap.clear();
        urlMap.clear();
    }
}
