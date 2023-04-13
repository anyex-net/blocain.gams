package com.fintech.gams.utils;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class GuavaExecutors
{
    public static final int                 DEFAULT_MAX_THREAD              = 1000;
    
    private static ListeningExecutorService defaultCompletedExecutorService = null;
    
    private static final Object             lock                            = new Object();
    
    public static ListeningExecutorService newCachedExecutorService(int maxThreadNum, final String namePrefix)
    {
        return MoreExecutors.listeningDecorator(new ThreadPoolExecutor(0, maxThreadNum, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory()
        {
            private final AtomicInteger poolNum = new AtomicInteger(1);
            
            @Override
            public Thread newThread(Runnable r)
            {
                Thread thread = new Thread(r, namePrefix + poolNum.getAndIncrement());
                return thread;
            }
        }));
    }
    
    public static ListeningExecutorService newCachedExecutorService(String namePrefix)
    {
        return newCachedExecutorService(DEFAULT_MAX_THREAD, namePrefix);
    }
    
    public static ListeningExecutorService getDefaultCompletedExecutorService()
    {
        if (defaultCompletedExecutorService == null)
        {
            synchronized (lock)
            {
                if (defaultCompletedExecutorService == null)
                {
                    defaultCompletedExecutorService = newCachedExecutorService("Completed-Callback-");
                }
            }
        }
        return defaultCompletedExecutorService;
    }
}
