package com.goteny.melo.http.annotations;


import com.goteny.melo.http.enums.MediaTypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

/**
 * HTTP请求的媒体格式类型(如form表单上传文件时File的ContentType类型)
 * Created by Jankey on 2017/7/2.
 */

@Target(PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MediaType
{
    MediaTypes value() default MediaTypes.IMAGE_JPG;
}
