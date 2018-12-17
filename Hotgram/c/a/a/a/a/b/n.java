package c.a.a.a.a.b;

import c.a.a.a.c;
import c.a.a.a.l;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class n {
    public static ExecutorService a(String arg1) {
        ExecutorService v0 = Executors.newSingleThreadExecutor(n.c(arg1));
        n.a(arg1, v0);
        return v0;
    }

    private static final void a(String arg3, ExecutorService arg4) {
        n.a(arg3, arg4, 2, TimeUnit.SECONDS);
    }

    public static final void a(String arg9, ExecutorService arg10, long arg11, TimeUnit arg13) {
        Runtime v0 = Runtime.getRuntime();
        c.a.a.a.a.b.n$2 v8 = new h(arg9, arg10, arg11, arg13) {
            public void a() {
                try {
                    l v0 = c.h();
                    v0.a("Fabric", "Executing shutdown hook for " + this.a);
                    this.b.shutdown();
                    if(this.b.awaitTermination(this.c, this.d)) {
                        return;
                    }

                    v0 = c.h();
                    v0.a("Fabric", this.a + " did not shut down in the allocated time. Requesting immediate shutdown.");
                    this.b.shutdownNow();
                }
                catch(InterruptedException ) {
                    c.h().a("Fabric", String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", this.a));
                    this.b.shutdownNow();
                }
            }
        };
        StringBuilder v10 = new StringBuilder();
        v10.append("Crashlytics Shutdown Hook for ");
        v10.append(arg9);
        v0.addShutdownHook(new Thread(((Runnable)v8), v10.toString()));
    }

    public static ScheduledExecutorService b(String arg1) {
        ScheduledExecutorService v0 = Executors.newSingleThreadScheduledExecutor(n.c(arg1));
        n.a(arg1, ((ExecutorService)v0));
        return v0;
    }

    public static final ThreadFactory c(String arg3) {
        return new ThreadFactory(arg3, new AtomicLong(1)) {
            public Thread newThread(Runnable arg4) {
                Thread v4 = Executors.defaultThreadFactory().newThread(new h(arg4) {
                    public void a() {
                        this.a.run();
                    }
                });
                v4.setName(this.a + this.b.getAndIncrement());
                return v4;
            }
        };
    }
}

