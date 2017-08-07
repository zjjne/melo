package com.goteny.melo.http;


import com.goteny.melo.http.enums.ContentTypes;
import com.goteny.melo.http.enums.RequestTypes;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jankey on 2017/6/26.
 */

public class Request
{
    private HttpUrl mUrl;
    private RequestTypes mRequestType;
    private HashMap<String, String> mHeaders;
    private ContentTypes mBodyContentType;
    private HashMap<String, List<Object>> mFields;
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

    public HashMap<String, String> getHeaders()
    {
        return mHeaders;
    }

    public void setHeaders(HashMap<String, String> mHeaders)
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

    public HashMap<String, List<Object>> getFields()
    {
        return mFields;
    }

    public void setFields(HashMap<String, List<Object>> mFields)
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
