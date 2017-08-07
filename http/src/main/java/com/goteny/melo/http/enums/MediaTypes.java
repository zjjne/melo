package com.goteny.melo.http.enums;

/**
 * Created by Jankey on 2017/7/2.
 */

public enum MediaTypes
{
    IMAGE_JPEG("image/jpeg"),
    IMAGE_JPG("image/jpg"),
    IMAGE_GIF("image/gif"),
    IMAGE_PNG("image/png");


    final String type;

    MediaTypes(String type)
    {
        this.type = type;
    }


    @Override
    public String toString()
    {
        return type;
    }
}
