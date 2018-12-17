package com.persianswitch.sdk.base.webservice;

import android.os.AsyncTask;

public class AsyncTaskUtil {
    public AsyncTaskUtil() {
        super();
    }

    public static AsyncTask a(AsyncTask arg1, Object[] arg2) {
        if(arg1 != null) {
            arg1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, arg2);
            return arg1;
        }

        throw new IllegalArgumentException("task can not be null");
    }
}

