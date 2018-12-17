package com.d.a.b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build$VERSION;
import com.d.a.a.a.a.a.b;
import com.d.a.b.a.g;
import com.d.a.c.c;
import com.d.a.c.e;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class a {
    class com.d.a.b.a$a implements ThreadFactory {
        private static final AtomicInteger a;
        private final ThreadGroup b;
        private final AtomicInteger c;
        private final String d;
        private final int e;

        static {
            com.d.a.b.a$a.a = new AtomicInteger(1);
        }

        com.d.a.b.a$a(int arg3, String arg4) {
            super();
            this.c = new AtomicInteger(1);
            this.e = arg3;
            this.b = Thread.currentThread().getThreadGroup();
            this.d = arg4 + com.d.a.b.a$a.a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable arg8) {
            ThreadGroup v1 = this.b;
            StringBuilder v0 = new StringBuilder();
            v0.append(this.d);
            v0.append(this.c.getAndIncrement());
            Thread v6 = new Thread(v1, arg8, v0.toString(), 0);
            if(v6.isDaemon()) {
                v6.setDaemon(false);
            }

            v6.setPriority(this.e);
            return v6;
        }
    }

    @TargetApi(value=11) private static int a(ActivityManager arg0) {
        return arg0.getLargeMemoryClass();
    }

    public static com.d.a.a.a.a a(Context arg9, com.d.a.a.a.b.a arg10, long arg11, int arg13) {
        File v7 = a.b(arg9);
        if(arg11 > 0 || arg13 > 0) {
            File v1 = e.b(arg9);
            try {
                b v0 = new b(v1, v7, arg10, arg11, arg13);
                return v0;
            }
            catch(IOException v11) {
                c.a(((Throwable)v11));
            }
        }

        return new com.d.a.a.a.a.b(e.a(arg9), v7, arg10);
    }

    public static com.d.a.a.b.a a(Context arg2, int arg3) {
        if(arg3 == 0) {
            Object v3 = arg2.getSystemService("activity");
            int v0 = ((ActivityManager)v3).getMemoryClass();
            if((a.d()) && (a.c(arg2))) {
                v0 = a.a(((ActivityManager)v3));
            }

            arg3 = v0 * 1048576 / 8;
        }

        return new com.d.a.a.b.a.b(arg3);
    }

    public static com.d.a.b.b.b a(boolean arg1) {
        return new com.d.a.b.b.a(arg1);
    }

    public static com.d.a.b.d.b a(Context arg1) {
        return new com.d.a.b.d.a(arg1);
    }

    public static Executor a() {
        return Executors.newCachedThreadPool(a.a(5, "uil-pool-d-"));
    }

    private static ThreadFactory a(int arg1, String arg2) {
        return new com.d.a.b.a$a(arg1, arg2);
    }

    public static Executor a(int arg8, int arg9, g arg10) {
        LinkedBlockingQueue v10_2;
        int v10 = arg10 == g.b ? 1 : 0;
        if(v10 != 0) {
            com.d.a.b.a.a.c v10_1 = new com.d.a.b.a.a.c();
        }
        else {
            v10_2 = new LinkedBlockingQueue();
        }

        return new ThreadPoolExecutor(arg8, arg8, 0, TimeUnit.MILLISECONDS, ((com.d.a.b.a.a.c)v10_2), a.a(arg9, "uil-pool-"));
    }

    private static File b(Context arg2) {
        File v2 = e.a(arg2, false);
        File v0 = new File(v2, "uil-images");
        if((v0.exists()) || (v0.mkdir())) {
            v2 = v0;
        }

        return v2;
    }

    public static com.d.a.a.a.b.a b() {
        return new com.d.a.a.a.b.b();
    }

    @TargetApi(value=11) private static boolean c(Context arg1) {
        boolean v1 = (arg1.getApplicationInfo().flags & 1048576) != 0 ? true : false;
        return v1;
    }

    public static com.d.a.b.c.a c() {
        return new com.d.a.b.c.c();
    }

    private static boolean d() {
        boolean v0 = Build$VERSION.SDK_INT >= 11 ? true : false;
        return v0;
    }
}

