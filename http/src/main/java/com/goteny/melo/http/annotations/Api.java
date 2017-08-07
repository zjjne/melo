package com.goteny.melo.http.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * API接口名(即URL中的API路径)
 *
 * Created by Jankey on 2017/6/19.
 */

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Api
{
    String value() default "";
}
