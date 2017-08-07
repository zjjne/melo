package com.goteny.melo.http;

/**
 *
 * Created by Jankey on 2017/8/2.
 */

public final class HttpUrl
{

    private String scheme;      //协议，"http"或"https"
    private String host;        //主机, ip或域名
    private int port;           //端口，取值区间[1,65535]
    private String path;        //路径，域名:端口后面那一坨



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

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
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


    public String getUrl()
    {
        return toString();
    }


    /**
     * 设置url
     * @param url
     */
    public void setUrl(String url)
    {
        //将正规url拆分成协议、域名、端口、路径

        int schemeSplit = url.indexOf("://");
        int hostSplit = url.indexOf("/", schemeSplit + 3);
        int portIndex = url.indexOf(":", schemeSplit);

        scheme = url.substring(0, schemeSplit);
        host = url.substring(schemeSplit + 3, hostSplit);   //此时Host可能会包含端口号
        path = url.substring(hostSplit);

        if (portIndex > 0)
        {
            String[] strings = host.split(":");
            host = strings[0];
            port = Integer.parseInt(strings[1]);
        }
    }


    /**
     * 将协议、域名、端口、路径拼接成正规url
     * @return
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
        builder.append("/");
        builder.append(path);

        return builder.toString();
    }
}
