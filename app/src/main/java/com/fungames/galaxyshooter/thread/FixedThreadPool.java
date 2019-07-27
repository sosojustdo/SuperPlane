package com.fungames.galaxyshooter.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: daipeng1
 * @date: 2019-07-27 18:00
 */
public class FixedThreadPool {

    private static int coreSize = Runtime.getRuntime().availableProcessors();

    private static ExecutorService getExecutor(String threadPrefix, boolean daemon){
        return new ThreadPoolExecutor(coreSize, coreSize, 5000L, TimeUnit.MINUTES,
                new LinkedBlockingDeque<Runnable>(1024), new NamedThreadFactory(threadPrefix, daemon));
    }

    public static void execute(String threadPrefix, boolean daemon, Runnable r){
        ExecutorService executor = getExecutor(threadPrefix, daemon);
        executor.execute(r);
    }

    public static Future<?> submit(String threadPrefix, boolean daemon, Callable<?> c){
        ExecutorService executor = getExecutor(threadPrefix, daemon);
        return executor.submit(c);
    }

}
