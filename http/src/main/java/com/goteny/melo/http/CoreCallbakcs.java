package com.goteny.melo.http;

import com.goteny.melo.http.interfaces.CoreCookieCallback;
import com.goteny.melo.http.interfaces.CoreHttpCallback;

/**
 * Created by Jankey on 2017/9/12.
 */

public class CoreCallbakcs
{
    private CoreHttpCallback coreHttpCallback;
    private CoreCookieCallback coreCookieCallback;

    public CoreCallbakcs()
    {
    }

    public CoreCallbakcs(CoreHttpCallback coreHttpCallback, CoreCookieCallback coreCookieCallback)
    {
        this.coreHttpCallback = coreHttpCallback;
        this.coreCookieCallback = coreCookieCallback;
    }

    public CoreHttpCallback getCoreHttpCallback()
    {
        return coreHttpCallback;
    }

    public void setCoreHttpCallback(CoreHttpCallback coreHttpCallback)
    {
        this.coreHttpCallback = coreHttpCallback;
    }

    public CoreCookieCallback getCoreCookieCallback()
    {
        return coreCookieCallback;
    }

    public void setCoreCookieCallback(CoreCookieCallback coreCookieCallback)
    {
        this.coreCookieCallback = coreCookieCallback;
    }
}
