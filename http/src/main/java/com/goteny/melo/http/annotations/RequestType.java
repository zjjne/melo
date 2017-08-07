package com.goteny.melo.http.annotations;



import com.goteny.melo.http.enums.RequestTypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 *
 * Created by Jankey on 2017/6/19.
 */

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestType
{
    RequestTypes value() default RequestTypes.GET;
}
