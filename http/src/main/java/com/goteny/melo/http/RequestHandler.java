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
import com.goteny.melo.http.enums.RequestTypes;
import com.goteny.melo.utils.log.LogMelo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        String host = "";
        String api = "";
        ContentTypes contentType;
        RequestTypes requestType;
        com.goteny.melo.http.Timeout timeout = null;
        boolean enableCookies = false;
        Map<String, String> headersMap = null;
        String[] headers = null;


        Host globalHostAnno = method.getDeclaringClass().getAnnotation(Host.class);     //全局host
        Host staticLocalHostAnno = method.getAnnotation(Host.class);                    //静态局部host
        Host dynamicLocalHostAnno = null;                                               //动态局部host
        Api apiAnno = method.getAnnotation(Api.class);
        RequestType requestTypeAnno = method.getAnnotation(RequestType.class);
        BodyContentType contentTypeAnno = method.getAnnotation(BodyContentType.class);
        Timeout globalTimeoutAnno = method.getDeclaringClass().getAnnotation(Timeout.class);
        Timeout timeoutAnno = method.getAnnotation(Timeout.class);
        Cookie globalCookieAnno = method.getDeclaringClass().getAnnotation(Cookie.class);
        Cookie cookieAnno = method.getAnnotation(Cookie.class);
        Headers globalHeadersAnno = method.getDeclaringClass().getAnnotation(Headers.class);     //全局headers
        Headers staticLocalHeadersAnno = method.getAnnotation(Headers.class);                    //静态局部headers
        Headers dynamicLocalHeadersAnno = null;                                                 //动态局部headers

        Annotation[][] parameterAnnos = method.getParameterAnnotations();

        HashMap<String, List<Object>> fieldsMap = new HashMap<>();

        for (int i = 0; i < parameterAnnos.length; i++)
        {
            List<Object> values = new ArrayList<>();
            String key = null;

            for (Annotation annotation : parameterAnnos[i])
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
                    dynamicLocalHostAnno = (Host) annotation;
                    host = (String) args[i];
                }else if (annotation.annotationType().equals(Headers.class))         //取得局部动态headers
                {
                    dynamicLocalHeadersAnno = (Headers) annotation;
                    headers = (String[]) args[i];
                }
            }

            if (key != null) fieldsMap.put(key, values);
        }


        //主机名host
        if (dynamicLocalHostAnno == null || dynamicLocalHostAnno.value() == null)
        {
            host = (globalHostAnno == null || globalHostAnno.value() == null)? host : globalHostAnno.value();
            host = (staticLocalHostAnno == null || staticLocalHostAnno.value() == null)? host : staticLocalHostAnno.value();
        }

        //api路径path
        api = (apiAnno == null || apiAnno.value() == null)? api : apiAnno.value();

        //请求类型
        requestType = (requestTypeAnno == null || requestTypeAnno.value() == null)?
                RequestTypes.GET : requestTypeAnno.value();

        //媒体类型
        contentType = (contentTypeAnno == null || contentTypeAnno.value() == null)?
                ContentTypes.APPLICATION_FORM_URLENCODED : contentTypeAnno.value();


        //超时时间
        if (timeoutAnno != null)
        {
            timeout = new com.goteny.melo.http.Timeout(timeoutAnno.timeout(), timeoutAnno.timeUnit());
        }else {
            if (globalTimeoutAnno != null)
                timeout = new com.goteny.melo.http.Timeout(globalTimeoutAnno.timeout(), globalTimeoutAnno.timeUnit());
        }


        //是否启用cookie
        if (cookieAnno != null)
        {
            enableCookies = true;
        }else {
            if (globalCookieAnno != null) enableCookies = true;
        }


        //请求头headers
        if (dynamicLocalHeadersAnno == null || dynamicLocalHeadersAnno.value() == null || dynamicLocalHeadersAnno.value().length <= 0)
        {
            headers = (globalHeadersAnno == null || globalHeadersAnno.value() == null || globalHeadersAnno.value().length <= 0)?
                    headers : globalHeadersAnno.value();

            headers = (staticLocalHeadersAnno == null || staticLocalHeadersAnno.value() == null || staticLocalHeadersAnno.value().length <= 0)?
                    headers : staticLocalHeadersAnno.value();
        }

        if (headers != null && headers.length > 0)
        {
            headersMap = new HashMap<>();

            for (String str: headers)
            {
                String[] keyValue = str.split("[:]", 2);     //拆分header的key和vaule
                headersMap.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }

        HttpUrl httpUrl = new HttpUrl();
        httpUrl.setHost(host);
        httpUrl.setPath(api);

        Request request = new Request();
        request.setUrl(httpUrl);
        request.setFields(fieldsMap);
        request.setHeaders(headersMap);
        request.setRequestType(requestType);
        request.setBodyContentType(contentType);
        request.setTimeout(timeout);
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

        LogMelo.i("globalHost: "        + globalHostAnno);
        LogMelo.i("staticLocalHost: "   + staticLocalHostAnno);
        LogMelo.i("dynamicLocalHost: "  + dynamicLocalHostAnno);
        LogMelo.i("host: "              + host);
        LogMelo.i("api: "               + api);
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
