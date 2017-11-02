package com.goteny.melo.demo.http;

import com.goteny.melo.demo.bean.GitHubIssue;
import com.goteny.melo.demo.bean.GitHubMain;
import com.goteny.melo.http.BaseHttp;
import com.goteny.melo.http.HttpProxy;
import com.goteny.melo.utils.log.LogMelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJJ on 2017/9/18.
 */

@SuppressWarnings("unchecked")
public class DemoHttpUtil
{

    private static DemoHttpUtil mInstance;
    private DemoApi mHttpApi;

    public static DemoHttpUtil getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new DemoHttpUtil();
        }

        return mInstance;
    }

    private DemoHttpUtil()
    {
        LogMelo.i(getClass().getSimpleName(), "HttpProxy.getInstance().create start");

        mHttpApi = HttpProxy.getInstance().create(DemoApi.class);

        LogMelo.i(getClass().getSimpleName(), "HttpProxy.getInstance().create end");
    }

    public BaseHttp<List<GitHubIssue>> fetchIssues(String issuesState)
    {
        LogMelo.i(getClass().getSimpleName(), "fetchIssues");

        return mHttpApi.fetchIssues(
                issuesState
        );
    }

    public BaseHttp<GitHubMain> fetchMain()
    {
        LogMelo.i(getClass().getSimpleName(), "fetchMain");

        return mHttpApi.fetchMain();
    }

    public BaseHttp testHost()
    {
        LogMelo.i(getClass().getSimpleName(), "testHost");

        return mHttpApi.testHost("https://api3.github.com");
    }

    public BaseHttp testHeader()
    {
        LogMelo.i(getClass().getSimpleName(), "testHeader");

        return mHttpApi.testHeader();
    }

    public BaseHttp testHeader2()
    {
        LogMelo.i(getClass().getSimpleName(), "testHeader2");

        String[] headers = new String[]{"Accept:application/text",  "User-Agent:" + System.getProperty("http.agent")};

        return mHttpApi.testHeader2(headers);
    }


}
