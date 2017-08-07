package com.goteny.melo.http;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jankey on 2017/8/1.
 */

public class Timeout
{
    private long timeout;
    private TimeUnit timeUnit;

    public Timeout(long timeout, TimeUnit timeUnit)
    {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public long getTimeout()
    {
        return timeout;
    }

    public void setTimeout(long timeout)
    {
        this.timeout = timeout;
    }

    public TimeUnit getTimeUnit()
    {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit)
    {
        this.timeUnit = timeUnit;
    }
}
