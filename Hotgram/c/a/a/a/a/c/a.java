package c.a.a.a.a.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class a {
    final class c.a.a.a.a.c.a$1 implements ThreadFactory {
        private final AtomicInteger a;

        c.a.a.a.a.c.a$1() {
            super();
            this.a = new AtomicInteger(1);
        }

        public Thread newThread(Runnable arg4) {
            StringBuilder v1 = new StringBuilder();
            v1.append("AsyncTask #");
            v1.append(this.a.getAndIncrement());
            return new Thread(arg4, v1.toString());
        }
    }

    class c.a.a.a.a.c.a$4 {
        static {
            c.a.a.a.a.c.a$4.a = new int[d.values().length];
            try {
                c.a.a.a.a.c.a$4.a[d.b.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    c.a.a.a.a.c.a$4.a[d.c.ordinal()] = 2;
                    return;
                }
                catch(NoSuchFieldError ) {
                    return;
                }
            }
        }
    }

    class c.a.a.a.a.c.a$a {
        final a a;
        final Object[] b;

        c.a.a.a.a.c.a$a(a arg1, Object[] arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    class b extends Handler {
        public b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message arg3) {
            Object v0 = arg3.obj;
            switch(arg3.what) {
                case 1: {
                    a.c(((c.a.a.a.a.c.a$a)v0).a, ((c.a.a.a.a.c.a$a)v0).b[0]);
                    break;
                }
                case 2: {
                    ((c.a.a.a.a.c.a$a)v0).a.b(((c.a.a.a.a.c.a$a)v0).b);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    class c implements Executor {
        final LinkedList a;
        Runnable b;

        c(c.a.a.a.a.c.a$1 arg1) {
            this();
        }

        private c() {
            super();
            this.a = new LinkedList();
        }

        protected void a() {
            __monitor_enter(this);
            try {
                Object v0_1 = this.a.poll();
                this.b = ((Runnable)v0_1);
                if(v0_1 != null) {
                    a.b.execute(this.b);
                }
            }
            catch(Throwable v0) {
                __monitor_exit(this);
                throw v0;
            }

            __monitor_exit(this);
        }

        public void execute(Runnable arg3) {
            __monitor_enter(this);
            try {
                this.a.offer(new Runnable(arg3) {
                    public void run() {
                        try {
                            this.a.run();
                        }
                        catch(Throwable v0) {
                            this.b.a();
                            throw v0;
                        }

                        this.b.a();
                    }
                });
                if(this.b == null) {
                    this.a();
                }
            }
            catch(Throwable v3) {
                __monitor_exit(this);
                throw v3;
            }

            __monitor_exit(this);
        }
    }

    public enum d {
        public static final enum d a;
        public static final enum d b;
        public static final enum d c;

        static {
            d.a = new d("PENDING", 0);
            d.b = new d("RUNNING", 1);
            d.c = new d("FINISHED", 2);
            d.d = new d[]{d.a, d.b, d.c};
        }

        private d(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static d valueOf(String arg1) {
            return Enum.valueOf(d.class, arg1);
        }

        public static d[] values() {
            // Method was not decompiled
        }
    }

    abstract class e implements Callable {
        Object[] b;

        e(c.a.a.a.a.c.a$1 arg1) {
            this();
        }

        private e() {
            super();
        }
    }

    private static final int a;
    public static final Executor b;
    public static final Executor c;
    private static final int d;
    private static final int e;
    private static final ThreadFactory f;
    private static final BlockingQueue g;
    private static final b h;
    private static volatile Executor i;
    private final e j;
    private final FutureTask k;
    private volatile d l;
    private final AtomicBoolean m;
    private final AtomicBoolean n;

    static {
        a.a = Runtime.getRuntime().availableProcessors();
        a.d = a.a + 1;
        a.e = a.a * 2 + 1;
        a.f = new c.a.a.a.a.c.a$1();
        a.g = new LinkedBlockingQueue(128);
        a.b = new ThreadPoolExecutor(a.d, a.e, 1, TimeUnit.SECONDS, a.g, a.f);
        a.c = new c(null);
        a.h = new b();
        a.i = a.c;
    }

    public a() {
        super();
        this.l = d.a;
        this.m = new AtomicBoolean();
        this.n = new AtomicBoolean();
        this.j = new e() {
            public Object call() {
                a.a(this.a).set(true);
                Process.setThreadPriority(10);
                return a.a(this.a, this.a.a(this.b));
            }
        };
        this.k = new FutureTask(this.j) {
            protected void done() {
                try {
                    a.b(this.a, this.get());
                }
                catch(CancellationException ) {
                    a.b(this.a, null);
                }
                catch(ExecutionException v0) {
                    throw new RuntimeException("An error occured while executing doInBackground()", v0.getCause());
                }
                catch(InterruptedException v0_1) {
                    Log.w("AsyncTask", ((Throwable)v0_1));
                }
            }
        };
    }

    static AtomicBoolean a(a arg0) {
        return arg0.n;
    }

    static Object a(a arg0, Object arg1) {
        return arg0.e(arg1);
    }

    protected abstract Object a(Object[] arg1);

    protected void a(Object arg1) {
    }

    public final a a(Executor arg3, Object[] arg4) {
        if(this.l != d.a) {
            switch(c.a.a.a.a.c.a$4.a[this.l.ordinal()]) {
                case 1: {
                    goto label_13;
                }
                case 2: {
                    goto label_9;
                }
            }

            goto label_17;
        label_9:
            throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
        label_13:
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }

    label_17:
        this.l = d.b;
        this.a();
        this.j.b = arg4;
        arg3.execute(this.k);
        return this;
    }

    protected void a() {
    }

    public final boolean a(boolean arg3) {
        this.m.set(true);
        return this.k.cancel(arg3);
    }

    static void b(a arg0, Object arg1) {
        arg0.d(arg1);
    }

    protected void b(Object arg1) {
        this.h_();
    }

    protected void b(Object[] arg1) {
    }

    static void c(a arg0, Object arg1) {
        arg0.f(arg1);
    }

    private void d(Object arg2) {
        if(!this.n.get()) {
            this.e(arg2);
        }
    }

    private Object e(Object arg6) {
        a.h.obtainMessage(1, new c.a.a.a.a.c.a$a(this, new Object[]{arg6})).sendToTarget();
        return arg6;
    }

    public final boolean e() {
        return this.m.get();
    }

    private void f(Object arg2) {
        if(this.e()) {
            this.b(arg2);
        }
        else {
            this.a(arg2);
        }

        this.l = d.c;
    }

    public final d g_() {
        return this.l;
    }

    protected void h_() {
    }
}

