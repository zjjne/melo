package com.goteny.melo.utils.log;

import android.util.Log;


/**
 * Created by Jankey on 2017/9/8
 */

public class LogMelo
{
    private static boolean ENABLE_LOG;



    public static void enableLog(boolean enable)
    {
        ENABLE_LOG = enable;
    }



    public static void v()
    {
        if (ENABLE_LOG)
        {
            Log.v(getTag(), getTraceInfo());
        }
    }

    public static void v(Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.v(getTag(), getTraceInfo() + msg);
        }
    }

    public static void v(Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.v(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void v(String tag, Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.v(tag, getTraceInfo() + msg);
        }
    }

    public static void v(String tag, Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.v(tag, getTraceInfo() + msg, tr);
        }
    }





    public static void d()
    {
        if (ENABLE_LOG)
        {
            Log.d(getTag(), getTraceInfo());
        }
    }

    public static void d(Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.d(getTag(), getTraceInfo() + msg);
        }
    }

    public static void d(Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.d(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void d(String tag, Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.d(tag, getTraceInfo() + msg);
        }
    }

    public static void d(String tag, Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.d(tag, getTraceInfo() + msg, tr);
        }
    }






    public static void i()
    {
        if (ENABLE_LOG)
        {
            Log.i(getTag(), getTraceInfo());
        }
    }

    public static void i(Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.i(getTag(), getTraceInfo() + msg);
        }
    }

    public static void i(Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.i(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void i(String tag, Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.i(tag, getTraceInfo() + msg);
        }
    }

    public static void i(String tag, Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.i(tag, getTraceInfo() + msg, tr);
        }
    }







    public static void w()
    {
        if (ENABLE_LOG)
        {
            Log.w(getTag(), getTraceInfo());
        }
    }

    public static void w(Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.w(getTag(), getTraceInfo() + msg);
        }
    }

    public static void w(Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.w(getTag(), tr);
        }
    }

//    public static void w(Object msg, Throwable tr)
//    {
//        if (ENABLE_LOG)
//        {
//            Log.w(getTag(), getTraceInfo() + msg, tr);
//        }
//    }

    public static void w(String tag, Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.w(tag, getTraceInfo() + msg);
        }
    }

    public static void w(String tag, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.w(tag, tr);
        }
    }

    public static void w(String tag, Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.w(tag, getTraceInfo() + msg, tr);
        }
    }






    public static void e()
    {
        if (ENABLE_LOG)
        {
            Log.e(getTag(), getTraceInfo());
        }
    }

    public static void e(Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.e(getTag(), getTraceInfo() + msg);
        }
    }

    public static void e(Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.e(getTag(), getTraceInfo() + msg, tr);
        }
    }

    public static void e(String tag, Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.e(tag, getTraceInfo() + msg);
        }
    }

    public static void e(String tag, Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.e(tag, getTraceInfo() + msg, tr);
        }
    }






    public static void wtf()
    {
        if (ENABLE_LOG)
        {
            Log.wtf(getTag(), getTraceInfo());
        }
    }

    public static void wtf(Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.wtf(getTag(), getTraceInfo() + msg);
        }
    }

    public static void wtf(Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.wtf(getTag(), tr);
        }
    }

//    public static void wtf(Object msg, Throwable tr)
//    {
//        if (ENABLE_LOG)
//        {
//            Log.wtf(getTag(), getTraceInfo() + msg, tr);
//        }
//    }

    public static void wtf(String tag, Object msg)
    {
        if (ENABLE_LOG)
        {
            Log.wtf(tag, getTraceInfo() + msg);
        }
    }

    public static void wtf(String tag, Throwable tr)
    {
        if (ENABLE_LOG)
        {
            Log.wtf(tag, tr);
        }
    }

    public static void wtf(String tag, Object msg, Throwable tr)
    {
        if (ENABLE_LOG)
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
