package com.goteny.melo.http.annotations;


import com.goteny.melo.http.enums.ContentTypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * HTTP请求的body的ContentType类型
 * Created by Jankey on 2017/7/4.
 */

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BodyContentType
{
    ContentTypes value() default ContentTypes.APPLICATION_FORM_URLENCODED;
}
