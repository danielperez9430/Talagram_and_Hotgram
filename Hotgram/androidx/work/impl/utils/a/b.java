package androidx.work.impl.utils.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class b implements a {
    class androidx.work.impl.utils.a.b$1 implements Executor {
        androidx.work.impl.utils.a.b$1(b arg1) {
            this.a = arg1;
            super();
        }

        public void execute(Runnable arg2) {
            this.a.b(arg2);
        }
    }

    class androidx.work.impl.utils.a.b$2 implements ThreadFactory {
        androidx.work.impl.utils.a.b$2(b arg1) {
            this.a = arg1;
            super();
        }

        public Thread newThread(Runnable arg2) {
            Thread v2 = Executors.defaultThreadFactory().newThread(arg2);
            this.a.a = v2;
            return v2;
        }
    }

    volatile Thread a;
    private final Handler b;
    private final Executor c;
    private final ThreadFactory d;
    private final ExecutorService e;

    public b() {
        super();
        this.b = new Handler(Looper.getMainLooper());
        this.c = new androidx.work.impl.utils.a.b$1(this);
        this.d = new androidx.work.impl.utils.a.b$2(this);
        this.e = Executors.newSingleThreadExecutor(this.d);
    }

    public Executor a() {
        return this.c;
    }

    public void a(Runnable arg2) {
        this.e.execute(arg2);
    }

    public Thread b() {
        return this.a;
    }

    public void b(Runnable arg2) {
        this.b.post(arg2);
    }

    public Executor c() {
        return this.e;
    }
}

