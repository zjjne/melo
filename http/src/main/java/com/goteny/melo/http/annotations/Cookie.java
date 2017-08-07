package com.goteny.melo.http.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 是否开启并存储cookie
 *
 * Created by Jankey on 2017/7/15.
 */

@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cookie
{
    boolean value() default true;
}
