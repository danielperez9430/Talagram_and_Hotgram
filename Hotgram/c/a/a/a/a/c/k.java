package c.a.a.a.a.c;

import android.annotation.TargetApi;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class k extends ThreadPoolExecutor {
    public final class a implements ThreadFactory {
        private final int a;

        public a(int arg1) {
            super();
            this.a = arg1;
        }

        public Thread newThread(Runnable arg2) {
            Thread v0 = new Thread(arg2);
            v0.setPriority(this.a);
            v0.setName("Queue");
            return v0;
        }
    }

    private static final int a;
    private static final int b;
    private static final int c;

    static {
        k.a = Runtime.getRuntime().availableProcessors();
        k.b = k.a + 1;
        k.c = k.a * 2 + 1;
    }

    k(int arg1, int arg2, long arg3, TimeUnit arg5, c arg6, ThreadFactory arg7) {
        super(arg1, arg2, arg3, arg5, ((BlockingQueue)arg6), arg7);
        this.prestartAllCoreThreads();
    }

    public static k a() {
        return k.a(k.b, k.c);
    }

    public static k a(int arg9, int arg10) {
        return new k(arg9, arg10, 1, TimeUnit.SECONDS, new c(), new a(10));
    }

    protected void afterExecute(Runnable arg3, Throwable arg4) {
        arg3.b(true);
        arg3.a(arg4);
        this.b().d();
        super.afterExecute(arg3, arg4);
    }

    public c b() {
        return super.getQueue();
    }

    @TargetApi(value=9) public void execute(Runnable arg2) {
        RunnableFuture v2;
        if(!j.a(arg2)) {
            v2 = this.newTaskFor(arg2, null);
        }

        super.execute(((Runnable)v2));
    }

    public BlockingQueue getQueue() {
        return this.b();
    }

    protected RunnableFuture newTaskFor(Runnable arg2, Object arg3) {
        return new h(arg2, arg3);
    }

    protected RunnableFuture newTaskFor(Callable arg2) {
        return new h(arg2);
    }
}

