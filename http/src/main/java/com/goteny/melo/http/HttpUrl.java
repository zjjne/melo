package com.goteny.melo.http;

import android.text.TextUtils;

import com.goteny.melo.utils.log.LogMelo;

/**
 *
 * Created by Jankey on 2017/8/2.
 */

public final class HttpUrl
{

    private String scheme = "";         //协议，"http"或"https"
    private String host = "";           //主机, ip或域名
    private int port;                   //端口，取值区间[1,65535]
    private String path = "";           //路径，域名:端口后面那一坨


    public HttpUrl()
    {
    }

    public HttpUrl(String url)
    {
        setUrl(url);
    }

    public String getScheme()
    {
        return scheme;
    }

    public void setScheme(String scheme)
    {
        this.scheme = scheme;
    }

    public String getHostWithScheme()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(scheme);
        builder.append("://");
        builder.append(host);

        if (port > 0)
        {
            builder.append(":");
            builder.append(port);
        }

        return builder.toString();
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        //正则表达式，替换字符串末尾的所有"/"符号去除
        host = (!host.trim().equals(""))? host.replaceAll("/+$", "") : "";

        //正则表达式，判断字符串开头是否含有"http://" "https://" "HTTP://" "HTTPS://"
        boolean isHasScheme = host.matches("^(?:http|https|HTTP|HTTPS)://\\S+$");

        if (isHasScheme)
        {
            int schemeSplit = host.indexOf("://");

            scheme = host.substring(0, schemeSplit);
        }

        //正则表达式，替换字符串开头的"http://" "https://" "HTTP://" "HTTPS://"
        host = (!host.trim().equals(""))? host.replaceAll("^(?:http|https|HTTP|HTTPS)://", "") : host;

        int portIndex = host.indexOf(":");

        if (portIndex >= 0)     //有端口号
        {
            String[] strings = host.split(":");

            host = strings[0];
            port = Integer.parseInt(strings[1]);
        }

        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }


    public String getFullUrl()
    {
        return toString();
    }


    /**
     * 设置url
     * @param url 传入url
     */
    public void setUrl(String url)
    {
        splitUrl(url);
    }

    private void splitUrl(String url)
    {

        //正则表达式，替换字符串末尾的所有"/"符号为单个"/"   如"////"会替换为"/"
        url = (!url.trim().equals(""))? url.replaceAll("/+$", "/") : "";

        //正则表达式，判断字符串开头是否含有"http://" "https://" "HTTP://" "HTTPS://"，不含则添加"http://"到字符串前面
        url = (!url.trim().equals("") && !url.matches("^(?:http|https|HTTP|HTTPS)://\\S+$"))? ("http://" + url) : url;


        //将正规url拆分成协议、域名、端口、路径

        int schemeSplit = url.indexOf("://");
        int hostSplit;
        int portIndex;

        if (schemeSplit >= 0)       //有协议
        {
            scheme = url.substring(0, schemeSplit);

            hostSplit = url.indexOf("/", schemeSplit + 3);
            portIndex = url.indexOf(":", schemeSplit + 3);
        }else {
            scheme = "http";

            hostSplit = url.indexOf("/");
            portIndex = url.indexOf(":");
        }


        if (hostSplit >= 0)     //中间或末尾有斜杠/
        {
            if (schemeSplit >= 0)   //有协议
            {
                host = url.substring(schemeSplit + 3, hostSplit);   //此时Host可能会包含端口号
            }else {
                host = url.substring(0, hostSplit);   //此时Host可能会包含端口号
            }

            path = url.substring(hostSplit + 1);
        }else {
            if (schemeSplit >= 0)   //有协议
            {
                host = url.substring(schemeSplit + 3);   //此时Host可能会包含端口号
            }else {
                host = url;   //此时Host可能会包含端口号
            }
        }


        if (portIndex >= 0)     //有端口号
        {
            String[] strings = host.split(":");

            host = strings[0];
            port = Integer.parseInt(strings[1]);
        }


        LogMelo.i("---------------------------------------------------------");
        LogMelo.i(" scheme【 " + scheme + " 】");
        LogMelo.i(" host  【 " + host + " 】");
        LogMelo.i(" port  【 " + port + " 】");
        LogMelo.i(" path  【 " + path + " 】");
        LogMelo.i("---------------------------------------------------------");
    }


    /**
     * 将协议、域名、端口、路径拼接成正规url
     * @return 返回完整的Url
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(scheme);
        builder.append("://");
        builder.append(host);

        if (port > 0)
        {
            builder.append(":");
            builder.append(port);
        }

        if (!TextUtils.isEmpty(path))
        {
            builder.append("/");
            builder.append(path);
        }

        return builder.toString();
    }
}
