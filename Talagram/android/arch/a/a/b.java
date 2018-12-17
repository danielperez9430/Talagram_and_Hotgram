package android.arch.a.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class b extends c {
    private final Object a;
    private ExecutorService b;
    private volatile Handler c;

    public b() {
        super();
        this.a = new Object();
        this.b = Executors.newFixedThreadPool(2);
    }

    public void a(Runnable arg2) {
        this.b.execute(arg2);
    }

    public void b(Runnable arg4) {
        if(this.c == null) {
            Object v0 = this.a;
            __monitor_enter(v0);
            try {
                if(this.c == null) {
                    this.c = new Handler(Looper.getMainLooper());
                }

                __monitor_exit(v0);
                goto label_15;
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_13;
            }

            throw v4;
        }

    label_15:
        this.c.post(arg4);
    }

    public boolean b() {
        boolean v0 = Looper.getMainLooper().getThread() == Thread.currentThread() ? true : false;
        return v0;
    }
}

