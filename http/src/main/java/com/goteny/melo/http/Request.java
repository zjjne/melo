package com.goteny.melo.http;


import com.goteny.melo.http.enums.ContentTypes;
import com.goteny.melo.http.enums.RequestTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jankey on 2017/6/26.
 */

public class Request
{
    private HttpUrl mUrl;
    private RequestTypes mRequestType;
    private Map<String, String> mHeaders;
    private ContentTypes mBodyContentType;
    private Map<String, List<Object>> mFields;
    private boolean mEnableCookies;
    private Timeout mTimeout;

    public HttpUrl getUrl()
    {
        return mUrl;
    }

    public void setUrl(HttpUrl mUrl)
    {
        this.mUrl = mUrl;
    }

    public RequestTypes getRequestType()
    {
        return mRequestType;
    }

    public void setRequestType(RequestTypes mRequestType)
    {
        this.mRequestType = mRequestType;
    }

    public Map<String, String> getHeaders()
    {
        return mHeaders;
    }

    public void setHeaders(Map<String, String> mHeaders)
    {
        this.mHeaders = mHeaders;
    }

    public ContentTypes getBodyContentType()
    {
        return mBodyContentType;
    }

    public void setBodyContentType(ContentTypes mBodyContentType)
    {
        this.mBodyContentType = mBodyContentType;
    }

    public Map<String, List<Object>> getFields()
    {
        return mFields;
    }

    public void setFields(Map<String, List<Object>> mFields)
    {
        this.mFields = mFields;
    }

    public boolean isEnableCookies()
    {
        return mEnableCookies;
    }

    public void setEnableCookies(boolean mEnableCookies)
    {
        this.mEnableCookies = mEnableCookies;
    }

    public Timeout getTimeout()
    {
        return mTimeout;
    }

    public void setTimeout(Timeout mTimeout)
    {
        this.mTimeout = mTimeout;
    }
}
