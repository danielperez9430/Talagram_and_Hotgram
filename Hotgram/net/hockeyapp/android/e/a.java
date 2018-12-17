package net.hockeyapp.android.e;

import android.os.AsyncTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class a {
    private static Executor a;

    public static void a(AsyncTask arg2) {
        Executor v0 = a.a != null ? a.a : AsyncTask.THREAD_POOL_EXECUTOR;
        arg2.executeOnExecutor(v0, new Void[0]);
    }

    public static FutureTask a(Callable arg2) {
        Executor v0 = a.a != null ? a.a : AsyncTask.THREAD_POOL_EXECUTOR;
        FutureTask v1 = new FutureTask(arg2);
        v0.execute(((Runnable)v1));
        return v1;
    }
}

