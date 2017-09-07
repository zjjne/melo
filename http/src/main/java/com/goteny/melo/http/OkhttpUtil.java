package com.goteny.melo.http;

import android.support.v4.util.LongSparseArray;
import android.util.Log;

import com.goteny.melo.http.enums.MediaTypes;
import com.goteny.melo.http.enums.RequestTypes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * OKHTTP请求工具
 * Created by Jankey on 2017/6/19.
 */

public class OkhttpUtil implements IHttpCore
{
    private static OkhttpUtil mInstance;

    public static OkhttpUtil getInstance()
    {
        if (mInstance == null)
            mInstance = new OkhttpUtil();
        return mInstance;
    }

    private LongSparseArray<OkHttpClient> mClients;   //各超时时间Client的map
    private final long DEFAULT_TIMEOUT_MILLISECOND = 30 * 1000; //默认超时时间，单位毫秒

    private OkhttpUtil()
    {
        mClients = new LongSparseArray<>();
        OkHttpClient.Builder clientBuilder = createDefaultClientBuilder();
        setBuilderTimeout(clientBuilder, DEFAULT_TIMEOUT_MILLISECOND);
        mClients.put(DEFAULT_TIMEOUT_MILLISECOND, clientBuilder.build());
    }

    private OkHttpClient.Builder createDefaultClientBuilder()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.cookieJar(new CookieJar()
        {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
            {
                if (mCookieStore.isEnableCookies(url.toString()))
                {
                    if (cookies != null && !cookies.isEmpty())
                        mCookieStore.addCookies(url.host(), cookies);
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url)
            {
                List<Cookie> cookies = null;
                
                if (mCookieStore.isEnableCookies(url.toString()))
                    cookies = mCookieStore.getCookies(url.host());
                
                if (cookies == null) cookies = new ArrayList<>();
                
                return cookies;
            }
        });

//        builder.addInterceptor(new Interceptor()
//        {
//            public okhttp3.Response intercept(Chain chain) throws IOException
//            {
//                okhttp3.Request request = chain.request();
//                okhttp3.Response response = chain.proceed(request);
//                return response;
//            }
//        });

        return builder;
    }


    private OkHttpClient.Builder setBuilderTimeout(OkHttpClient.Builder builder, long timeout)
    {
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS);
        return builder;
    }


    @Override
    public void send(Request request, HttpCoreCallback coreCallback)
    {
        mCookieStore.addUrl(request.getUrl().toString(), request.isEnableCookies());

        if (request.getRequestType() == RequestTypes.GET)
            get(request, coreCallback);
        else
            post(request, coreCallback);
    }

    public void get(Request request, HttpCoreCallback coreCallback)
    {
        String url = request.getUrl().toString();
        url = url.replace("?", "");

        StringBuilder stringBuilder = new StringBuilder();

        Set<Map.Entry<String, List<Object>>> entrySet = request.getFields().entrySet();

        for (Map.Entry<String, List<Object>> entry : entrySet)
        {
            List<Object> values = entry.getValue();

            if (stringBuilder.length() > 0)
                stringBuilder.append("&");
            else
                stringBuilder.append("?");

            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");

            String value = values.get(0).toString();

//            try
//            {
//                //对参数进行url encode，防止参数中出现中文
//                value = URLEncoder.encode(value, "UTF-8");
//            } catch (UnsupportedEncodingException e)
//            {
//                e.printStackTrace();
//            }

            stringBuilder.append(value);

            Log.i("HttpRequest", "field: " + entry.getKey() + "=" + values.get(0).toString());
        }

        String params = stringBuilder.toString();
        url += params;


        okhttp3.Request.Builder reqBuilder = new okhttp3.Request.Builder().url(url);

        okhttp3.Request req = reqBuilder.get().build();

        call(getClient(request), req, coreCallback);
    }


    public void post(Request request, HttpCoreCallback coreCallback)
    {
        RequestBody body;

        switch (request.getBodyContentType())
        {
            case APPLICATION_FORM_URLENCODED:
                body = createFormBody(request);
                break;
            case MULTIPART_FORM:
                body = createMultipartBody(request);
                break;
            default:
                body = createFormBody(request);
                break;
        }

        String url = request.getUrl().toString();

        okhttp3.Request.Builder reqBuilder = new okhttp3.Request.Builder();
        okhttp3.Request req = reqBuilder.url(url).post(body).build();

        call(getClient(request), req, coreCallback);
    }

    private OkHttpClient getClient(Request request)
    {
        long timeMillis = DEFAULT_TIMEOUT_MILLISECOND;
        Timeout timeout = request.getTimeout();

        if (timeout != null)
        {
            long time = timeout.getTimeout();
            timeMillis = timeout.getTimeUnit().toMillis(time);
        }

        OkHttpClient client = mClients.get(timeMillis);

        if (client == null)
        {
            OkHttpClient.Builder builder = mClients.get(DEFAULT_TIMEOUT_MILLISECOND).newBuilder();
            setBuilderTimeout(builder, timeMillis);
            return builder.build();
        } else
        {
            return client;
        }
    }


    private RequestBody createMultipartBody(Request request)
    {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        Set<Map.Entry<String, List<Object>>> entrySet = request.getFields().entrySet();

        for (Map.Entry<String, List<Object>> entry : entrySet)
        {
            List<Object> values = entry.getValue();

            if (values.size() > 1)
            {
                File file = null;
                MediaTypes mediaType = null;

                for (Object o : values)
                {
                    if (o instanceof File)
                    {
                        file = (File) o;
                    } else if (o instanceof MediaTypes)
                    {
                        mediaType = (MediaTypes) o;
                    }
                }

                if (file != null && mediaType != null)
                {
                    RequestBody fileBody = RequestBody.create(okhttp3.MediaType.parse(mediaType.toString()), file);
                    builder.addFormDataPart(entry.getKey(), file.getName(), fileBody);
                    Log.i("HttpRequest", "field: " + entry.getKey() + "=[ " + "File: " + file.toString() + " | MediaType: " + mediaType + " ]");
                }

            } else
            {
                builder.addFormDataPart(entry.getKey(), values.get(0).toString());

                Log.i("HttpRequest", "field: " + entry.getKey() + "=" + values.get(0).toString());
            }
        }

        return builder.build();
    }


    private RequestBody createFormBody(Request request)
    {
        FormBody.Builder builder = new FormBody.Builder();

        Set<Map.Entry<String, List<Object>>> entrySet = request.getFields().entrySet();

        for (Map.Entry<String, List<Object>> entry : entrySet)
        {
            List<Object> values = entry.getValue();
            builder.add(entry.getKey(), values.get(0).toString());

            Log.i("HttpRequest", "field: " + entry.getKey() + "=" + values.get(0).toString());
        }

        return builder.build();
    }


    private void call(OkHttpClient client, okhttp3.Request request, final HttpCoreCallback coreCallback)
    {
        Log.i("HttpRequest", request.toString());

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onResponse(Call call, final okhttp3.Response response) throws IOException
            {
                if (response.isSuccessful())
                    coreCallback.onSuccess(response.body().string());
                else
                    coreCallback.onFailure();
            }

            @Override
            public void onFailure(Call call, final IOException e)
            {
                coreCallback.onFailure();
            }
        });
    }
}