package com.goteny.melo.http.enums;

/**
 * Created by Jankey on 2017/7/4.
 */

public enum ContentTypes
{
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    MULTIPART_FORM("multipart/form-data");


    final String type;

    ContentTypes(String type)
    {
        this.type = type;
    }


    @Override
    public String toString()
    {
        return type;
    }
}
