package org.telegram.customization.util;

import android.annotation.TargetApi;
import android.os.AsyncTask;

@TargetApi(value=11) class e {
    static void a(AsyncTask arg1, Object[] arg2) {
        arg1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, arg2);
    }
}

