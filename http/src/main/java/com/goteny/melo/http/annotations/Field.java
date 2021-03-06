package com.goteny.melo.http.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Created by Jankey on 2017/6/23.
 */

@Target(PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field
{
    String value() default "";
}
