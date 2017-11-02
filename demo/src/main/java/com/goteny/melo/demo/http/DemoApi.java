package com.goteny.melo.demo.http;


import com.goteny.melo.demo.bean.GitHubIssue;
import com.goteny.melo.demo.bean.GitHubMain;
import com.goteny.melo.http.BaseHttp;
import com.goteny.melo.http.annotations.Api;
import com.goteny.melo.http.annotations.Field;
import com.goteny.melo.http.annotations.Headers;
import com.goteny.melo.http.annotations.Host;
import com.goteny.melo.http.annotations.RequestType;
import com.goteny.melo.http.annotations.Timeout;
import com.goteny.melo.http.enums.RequestTypes;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;



/**
 * Demo接口类
 * Created by ZJJ on 2017/9/18.
 */

@Host("https://api.github.com")
@Timeout(timeout = 30, timeUnit = TimeUnit.SECONDS)
public interface DemoApi
{

    @Api("repos/square/okhttp/issues")
    @RequestType(RequestTypes.GET)
    BaseHttp<List<GitHubIssue>> fetchIssues(
            @Field("state") String state
    );


    @RequestType(RequestTypes.GET)
    BaseHttp<GitHubMain> fetchMain(
    );

    @Host("https://api2.github.com")
    @RequestType(RequestTypes.GET)
    BaseHttp testHost(
            @Host String host
    );


    @Headers({
            "Accept: application/json",
            "User-Agent: hahahahaha"
    })
    @RequestType(RequestTypes.GET)
    BaseHttp testHeader(
    );

    @RequestType(RequestTypes.GET)
    BaseHttp testHeader2(
            @Headers String[] headers
    );

}
