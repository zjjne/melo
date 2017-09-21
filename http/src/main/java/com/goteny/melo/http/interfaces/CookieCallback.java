package com.goteny.melo.http.interfaces;


import java.util.List;

import okhttp3.Cookie;

/**
 *
 * Created by Jankey on 2017/6/20.
 */

public interface CookieCallback
{
    void cookies(List<Cookie> cookies);
}
