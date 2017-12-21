package com.goteny.melo.utils.log;

import android.util.Log;

import com.goteny.melo.utils.BuildConfig;


/**
 * Created by Jankey on 2017/9/8
 */

public class LogMelo
{
    public static void v(String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.v(getTag(), getTraceInfo() + msg);
        }
    }

    public static void v(String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.v(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void v(String tag, String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.v(tag, getTraceInfo() + msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.v(tag, getTraceInfo() + msg, tr);
        }
    }






    public static void d(String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.d(getTag(), getTraceInfo() + msg);
        }
    }

    public static void d(String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.d(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void d(String tag, String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.d(tag, getTraceInfo() + msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.d(tag, getTraceInfo() + msg, tr);
        }
    }







    public static void i(String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.i(getTag(), getTraceInfo() + msg);
        }
    }

    public static void i(String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.i(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void i(String tag, String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.i(tag, getTraceInfo() + msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.i(tag, getTraceInfo() + msg, tr);
        }
    }








    public static void w(String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.w(getTag(), getTraceInfo() + msg);
        }
    }

    public static void w(Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.w(getTag(), tr);
        }
    }

//    public static void w(String msg, Throwable tr)
//    {
//        if (BuildConfig.DEBUG)
//        {
//            Log.w(getTag(), getTraceInfo() + msg, tr);
//        }
//    }

    public static void w(String tag, String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.w(tag, getTraceInfo() + msg);
        }
    }

    public static void w(String tag, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.w(tag, tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.w(tag, getTraceInfo() + msg, tr);
        }
    }







    public static void e(String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.e(getTag(), getTraceInfo() + msg);
        }
    }

    public static void e(String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.e(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void e(String tag, String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.e(tag, getTraceInfo() + msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.e(tag, getTraceInfo() + msg, tr);
        }
    }







    public static void wtf(String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.wtf(getTag(), getTraceInfo() + msg);
        }
    }

    public static void wtf(Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.wtf(getTag(), tr);
        }
    }

//    public static void wtf(String msg, Throwable tr)
//    {
//        if (BuildConfig.DEBUG)
//        {
//            Log.wtf(getTag(), getTraceInfo() + msg, tr);
//        }
//    }

    public static void wtf(String tag, String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.wtf(tag, getTraceInfo() + msg);
        }
    }

    public static void wtf(String tag, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.wtf(tag, tr);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr)
    {
        if (BuildConfig.DEBUG)
        {
            Log.wtf(tag, getTraceInfo() + msg, tr);
        }
    }







    private static String getTraceInfo()
    {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
        String fileInfo = "[(" + stackTrace.getFileName() + ":" + stackTrace.getLineNumber() + ") #" + stackTrace.getMethodName() + "]-->  ";
        return fileInfo;
    }

    private static String getTag()
    {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
        return stackTrace.getFileName().replace(".java", "");
    }
}
