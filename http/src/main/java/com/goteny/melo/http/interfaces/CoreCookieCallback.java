package com.goteny.melo.http.interfaces;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by Jankey on 2017/9/12.
 */

public interface CoreCookieCallback
{
    void cookies(List<Cookie> cookies);
}
