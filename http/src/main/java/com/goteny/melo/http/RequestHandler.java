package com.goteny.melo.http;


import com.goteny.melo.http.annotations.Api;
import com.goteny.melo.http.annotations.BodyContentType;
import com.goteny.melo.http.annotations.Cookie;
import com.goteny.melo.http.annotations.Field;
import com.goteny.melo.http.annotations.Headers;
import com.goteny.melo.http.annotations.Host;
import com.goteny.melo.http.annotations.MediaType;
import com.goteny.melo.http.annotations.RequestType;
import com.goteny.melo.http.annotations.Timeout;
import com.goteny.melo.http.enums.ContentTypes;
import com.goteny.melo.http.enums.MediaTypes;
import com.goteny.melo.utils.log.LogMelo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.google.gson.reflect.TypeToken;

/**
 * 请求BaseRequest对象创建助手类
 *
 * Created by Jankey on 2017/6/22.
 */

@SuppressWarnings("unchecked")
public class RequestHandler
{

    /**
     * 创建HttpRequest请求对象
     * @param method 方法
     * @param args 参数
     * @return BaseHttp对象
     */
    public static BaseHttp createRequest(Method method, Object[] args)
    {
        String hostStr = "";
        String apiStr = "";
        ContentTypes contentType;
        com.goteny.melo.http.Timeout to = null;
        boolean enableCookies = false;
        Map<String, String> headers = null;
        String[] headerStrings = null;


        Host globalHost = method.getDeclaringClass().getAnnotation(Host.class);     //全局host
        Host staticLocalHost = method.getAnnotation(Host.class);                    //静态局部host
        Host dynamicLocalHost = null;                                               //动态局部host
        Api api = method.getAnnotation(Api.class);
        RequestType requestType = method.getAnnotation(RequestType.class);
        BodyContentType bodyContentType = method.getAnnotation(BodyContentType.class);
        Timeout globalTimeout = method.getDeclaringClass().getAnnotation(Timeout.class);
        Timeout timeout = method.getAnnotation(Timeout.class);
        Cookie globalCookie = method.getDeclaringClass().getAnnotation(Cookie.class);
        Cookie cookie = method.getAnnotation(Cookie.class);
        Headers globalHeaders = method.getDeclaringClass().getAnnotation(Headers.class);     //全局headers
        Headers staticLocalHeaders = method.getAnnotation(Headers.class);                    //静态局部headers
        Headers dynamicLocalHeaders = null;                                                 //动态局部headers

        Annotation[][] parameters = method.getParameterAnnotations();

        HashMap<String, List<Object>> map = new HashMap<>();

        for (int i = 0; i < parameters.length; i++)
        {
            List<Object> values = new ArrayList<>();
            String key = null;

            for (Annotation annotation : parameters[i])
            {
                if (annotation.annotationType().equals(Field.class))                //取得参数field的key value
                {
                    key = ((Field) annotation).value();     //取得注解值key
                    values.add(args[i]);                    //取得被注解的参数对象，放入List
                }else if (annotation.annotationType().equals(MediaType.class))      //取得媒体格式类型
                {
                    MediaTypes mediaType = ((MediaType) annotation).value();
                    values.add(mediaType);                //取得文件类型描述，放入List
                }else if (annotation.annotationType().equals(Host.class))           //取得局部动态host值
                {
                    dynamicLocalHost = (Host) annotation;
                    hostStr = (String) args[i];
                }else if (annotation.annotationType().equals(Headers.class))         //取得局部动态headers
                {
                    dynamicLocalHeaders = (Headers) annotation;
                    headerStrings = (String[]) args[i];
                }
            }

            if (key != null) map.put(key, values);
        }


        //主机名host
        if (dynamicLocalHost == null || dynamicLocalHost.value() == null)
        {
            hostStr = (globalHost == null || globalHost.value() == null)? hostStr : globalHost.value();
            hostStr = (staticLocalHost == null || staticLocalHost.value() == null)? hostStr : staticLocalHost.value();
        }

        //api路径path
        apiStr = (api == null || api.value() == null)? apiStr : api.value();

        //媒体类型
        contentType = (bodyContentType == null)? null : bodyContentType.value();


        //超时时间
        if (timeout != null)
        {
            to = new com.goteny.melo.http.Timeout(timeout.timeout(), timeout.timeUnit());
        }else {
            if (globalTimeout != null)
                to = new com.goteny.melo.http.Timeout(globalTimeout.timeout(), globalTimeout.timeUnit());
        }


        //是否启用cookie
        if (cookie != null)
        {
            enableCookies = true;
        }else {
            if (globalCookie != null) enableCookies = true;
        }


        //请求头headers
        if (dynamicLocalHeaders == null || dynamicLocalHeaders.value() == null || dynamicLocalHeaders.value().length <= 0)
        {
            headerStrings = (globalHeaders == null || globalHeaders.value() == null || globalHeaders.value().length <= 0)? headerStrings : globalHeaders.value();
            headerStrings = (staticLocalHeaders == null || staticLocalHeaders.value() == null || staticLocalHeaders.value().length <= 0)? headerStrings : staticLocalHeaders.value();
        }

        if (headerStrings != null && headerStrings.length > 0)
        {
            headers = new HashMap<>();

            for (String str: headerStrings)
            {
                String[] keyValue = str.split("[:]", 2);     //拆分header的key和vaule
                headers.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }

        HttpUrl httpUrl = new HttpUrl();
        httpUrl.setHost(hostStr);
        httpUrl.setPath(apiStr);

        Request request = new Request();
        request.setUrl(httpUrl);
        request.setFields(map);
        request.setHeaders(headers);
        request.setRequestType(requestType.value());
        request.setBodyContentType(contentType);
        request.setTimeout(to);
        request.setEnableCookies(enableCookies);

        Type returnType = method.getGenericReturnType();    //获取函数返回值类型type

        Class cls = getRawClass(returnType);
        Type type = getGenricType(returnType, 0);         //取返回值类里的泛型的type

        BaseHttp http;

        try
        {
            http = (BaseHttp) cls.newInstance();
        } catch (Throwable e)
        {
            LogMelo.i("BaseHttp convert failed " + e );
            http = new BaseHttp<>();
        }

        http.setType(type);
        http.setRequest(request);

        LogMelo.i("globalHost: "        + globalHost);
        LogMelo.i("staticLocalHost: "   + staticLocalHost);
        LogMelo.i("dynamicLocalHost: "  + dynamicLocalHost);
        LogMelo.i("hostStr: "           + hostStr);
        LogMelo.i("apiStr: "            + apiStr);
        LogMelo.i("enableCookies: "     + enableCookies);
        LogMelo.i("contentType: "       + contentType);

        return http;
    }


    /**
     * 取type对象的源class对象(即类Abcde<T>的Abcde类)
     * @param type type对象须实现了接口ParameterizedType才能正确返回数据(即type为Abcde<T>类)
     * @return
     */
    private static Class getRawClass(Type type)
    {
        if (!(type instanceof ParameterizedType))
            return (Class) type;

        Type rawType = ((ParameterizedType) type).getRawType();
        if (rawType != null)
            return (Class) rawType;

        return (Class) type;
    }


//    /**
//     * 取type对象内第index个泛型的真实类的class对象(即类Abcde<T>的T类)
//     * @param type type对象须实现了接口ParameterizedType才能正确返回真实类的class对象(即type为Abcde<T>类)
//     * @param index
//     * @return
//     */
//    private static Class getGenricClass(Type type, int index)
//    {
//        if (!(type instanceof ParameterizedType))
//            return Object.class;
//
//        //getActualTypeArguments为取ParameterizedType实现类里的args数组，此数组为type对象所携带的所有泛型的真实类数组
//        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
//
//        if (index >= params.length || index < 0)
//            return Object.class;
//
//        if ( !(params[index] instanceof Class))
//            return Object.class;
//
//        return (Class) params[index];
//    }


    /**
     * 取type对象内第index个泛型的真实类的Type对象(即类Abcde<T>的T类)
     * @param type type对象须实现了接口ParameterizedType才能正确返回真实类的type对象(即type为Abcde<T>类)
     * @param index
     * @return
     */
    private static Type getGenricType(Type type, int index)
    {
        if (!(type instanceof ParameterizedType))
            return type;

        //getActualTypeArguments为取ParameterizedType实现类里的args数组，此数组为type对象所携带的所有泛型的真实类数组
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();

        if (index >= params.length || index < 0)
            return type;

        return params[index];
    }
}
