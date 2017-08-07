package com.goteny.melo.http.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 请求超时时间长度
 * Created by Jankey on 2017/7/4.
 */

@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Timeout
{
    long timeout() default 10;
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
