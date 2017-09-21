package com.goteny.melo.http.interfaces;


/**
 *
 * Created by Jankey on 2017/9/12.
 */

public interface ICookie extends Executable
{
    ICookie callback(CookieCallback callback);
}
