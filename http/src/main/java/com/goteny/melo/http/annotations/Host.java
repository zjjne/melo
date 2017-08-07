package com.goteny.melo.http.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 请求服务器地址(域名或IP)
 * Created by Jankey on 2017/7/4.
 */

@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Host
{
    String value() default "";
}
