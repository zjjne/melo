package com.goteny.melo.http;


import android.text.TextUtils;
import android.util.Log;

import com.goteny.melo.http.annotations.Api;
import com.goteny.melo.http.annotations.BodyContentType;
import com.goteny.melo.http.annotations.Cookie;
import com.goteny.melo.http.annotations.Field;
import com.goteny.melo.http.annotations.Host;
import com.goteny.melo.http.annotations.MediaType;
import com.goteny.melo.http.annotations.RequestType;
import com.goteny.melo.http.annotations.Timeout;
import com.goteny.melo.http.enums.ContentTypes;
import com.goteny.melo.http.enums.MediaTypes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * @param method
     * @param args
     * @return
     */
    public static BaseHttp createRequest(Method method, Object[] args)
    {
        Host globalHost = method.getDeclaringClass().getAnnotation(Host.class);
        Host host = method.getAnnotation(Host.class);
        Api api = method.getAnnotation(Api.class);
        RequestType requestType = method.getAnnotation(RequestType.class);
        BodyContentType bodyContentType = method.getAnnotation(BodyContentType.class);
        Timeout globalTimeout = method.getDeclaringClass().getAnnotation(Timeout.class);
        Timeout timeout = method.getAnnotation(Timeout.class);
        Cookie globalCookie = method.getDeclaringClass().getAnnotation(Cookie.class);
        Cookie cookie = method.getAnnotation(Cookie.class);

        Annotation[][] parameters = method.getParameterAnnotations();

        HashMap<String, List<Object>> map = new HashMap<>();

        for (int i = 0; i < parameters.length; i++)
        {
            List<Object> values = new ArrayList<>();
            String key = null;

            for (Annotation annotation : parameters[i])
            {
                if (annotation.annotationType().equals(Field.class))
                {
                    key = ((Field) annotation).value();     //取得注解值key
                    values.add(args[i]);                    //取得被注解的参数对象，放入List
                }else if (annotation.annotationType().equals(MediaType.class))
                {
                    MediaTypes contentType = ((MediaType) annotation).value();
                    values.add(contentType);                //取得文件类型描述，放入List
                }
            }

            if (key != null) map.put(key, values);
        }

//        HashMap<String, Object> map = new HashMap<>();
//
//        for (int i = 0; i < parameters.length; i++)
//        {
//            for (Annotation annotation : parameters[i])
//            {
//                if (annotation.annotationType().equals(Field.class))
//                {
//                    String key = ((Field) annotation).value();
//                    map.put(key, args[i]);
//                }
//            }
//        }

//        Annotation[][] pa = method.getParameterAnnotations();
//
//        //pa.length为方法的参数个数
//        Log.i("HttpProxy", "pa.length:" + pa.length);
//
//        for (Annotation[] aa: pa)
//        {
//            //aa.length为此参数的注解个数
//            Log.i("HttpProxy", "aa.length:" + aa.length);
//            for (Annotation a: aa)
//            {
//                Log.i("HttpProxy", "ParameterAnnotation:" + a);
//            }
//        }


        Log.i("HttpProxy", "globalHost:" + globalHost);

        String hostStr = (globalHost == null || globalHost.value() == null)? "" : globalHost.value();

        Log.i("HttpProxy", "globalHost.value: " + hostStr);

        hostStr = (host == null || host.value() == null)? hostStr : host.value();

        Log.i("HttpProxy", "host: " + host);
        Log.i("HttpProxy", "hostStr: " + hostStr);

        String apiStr = (api == null || api.value() == null)? "" : api.value();

        if (!TextUtils.isEmpty(apiStr))
        {
            hostStr = (hostStr.trim().equals(""))? "" : (hostStr + "/");

            //正则表达式，替换字符串末尾的所有"/"符号为单个"/"   如"////"会替换为"/"
            hostStr = (!hostStr.trim().equals(""))? hostStr.replaceAll("/+$", "/") : "";
        }

        //正则表达式，判断字符串开头是否含有"http://" "https://" "HTTP://" "HTTPS://"，不含则添加"http://"到字符串前面
        hostStr = (!hostStr.trim().equals("") && !hostStr.matches("^(?:http|https|HTTP|HTTPS)://\\S+$"))? ("http://" + hostStr) : "";

        String url = hostStr + apiStr;

        ContentTypes contentType = (bodyContentType == null)? null : bodyContentType.value();

        com.goteny.melo.http.Timeout to = null;

        if (timeout != null)
        {
            to = new com.goteny.melo.http.Timeout(timeout.timeout(), timeout.timeUnit());
        }else {
            if (globalTimeout != null)
                to = new com.goteny.melo.http.Timeout(globalTimeout.timeout(), globalTimeout.timeUnit());
        }


        boolean enableCookies = false;

        if (cookie != null)
        {
            enableCookies = true;
        }else {
            if (globalCookie != null) enableCookies = true;
        }

        Request request = new Request();
        request.setUrl(new HttpUrl(url));
        request.setFields(map);
        request.setRequestType(requestType.value());
        request.setBodyContentType(contentType);
        request.setTimeout(to);
        request.setEnableCookies(enableCookies);

        Type returnType = method.getGenericReturnType();    //获取函数返回值类型type

        Class cls = getRawClass(returnType);
        Class clss = getGenricClass(returnType, 0);         //取返回值类里的泛型的class

        BaseHttp http;

        try
        {
            http = (BaseHttp) cls.newInstance();
        } catch (Throwable e)
        {
            e.printStackTrace();
            http = new BaseHttp<>();
        }

        http.setClss(clss);
        http.setRequest(request);

        Log.i("HttpProxy", "Annotation api:" + api);
        Log.i("HttpProxy", "Annotation api.value():" + apiStr);

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
            return Object.class;

        Type rawType = ((ParameterizedType) type).getRawType();
        if (rawType != null)
            return (Class) rawType;

        return Object.class;
    }


    /**
     * 取type对象内第index个泛型的真实类的class对象(即类Abcde<T>的T类)
     * @param type type对象须实现了接口ParameterizedType才能正确返回真实类的class对象(即type为Abcde<T>类)
     * @param index
     * @return
     */
    private static Class getGenricClass(Type type, int index)
    {
        if (!(type instanceof ParameterizedType))
            return Object.class;

        //getActualTypeArguments为取ParameterizedType实现类里的args数组，此数组为type对象所携带的所有泛型的真实类数组
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();

        if (index >= params.length || index < 0)
            return Object.class;

        if (!(params[index] instanceof Class))
            return Object.class;

        return (Class) params[index];
    }
}
