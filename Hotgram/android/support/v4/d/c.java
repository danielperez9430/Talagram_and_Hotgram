package android.support.v4.d;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class c {
    class android.support.v4.d.c$1 implements Handler$Callback {
        android.support.v4.d.c$1(c arg1) {
            this.a = arg1;
            super();
        }

        public boolean handleMessage(Message arg3) {
            switch(arg3.what) {
                case 0: {
                    goto label_8;
                }
                case 1: {
                    goto label_4;
                }
            }

            return 1;
        label_4:
            this.a.a(arg3.obj);
            return 1;
        label_8:
            this.a.a();
            return 1;
        }
    }

    public interface a {
        void a(Object arg1);
    }

    private final Object a;
    private HandlerThread b;
    private Handler c;
    private int d;
    private Handler$Callback e;
    private final int f;
    private final int g;
    private final String h;

    public c(String arg2, int arg3, int arg4) {
        super();
        this.a = new Object();
        this.e = new android.support.v4.d.c$1(this);
        this.h = arg2;
        this.g = arg3;
        this.f = arg4;
        this.d = 0;
    }

    public Object a(Callable arg13, int arg14) {
        Object v13_1;
        ReentrantLock v7 = new ReentrantLock();
        Condition v8 = v7.newCondition();
        AtomicReference v9 = new AtomicReference();
        AtomicBoolean v10 = new AtomicBoolean(true);
        this.b(new Runnable(v9, arg13, v7, v10, v8) {
            public void run() {
                try {
                    this.a.set(this.b.call());
                    goto label_4;
                }
                catch(Exception ) {
                label_4:
                    this.c.lock();
                    try {
                        this.d.set(false);
                        this.e.signal();
                    }
                    catch(Throwable v0) {
                        this.c.unlock();
                        throw v0;
                    }

                    this.c.unlock();
                    return;
                }
            }
        });
        v7.lock();
        try {
            if(v10.get()) {
                goto label_25;
            }

            v13_1 = v9.get();
        }
        catch(Throwable v13) {
            goto label_43;
        }

        v7.unlock();
        return v13_1;
        try {
        label_25:
            long v13_2 = TimeUnit.MILLISECONDS.toNanos(((long)arg14));
            try {
                while(true) {
                label_28:
                    v13_2 = v8.awaitNanos(v13_2);
                    goto label_30;
                }
            }
            catch(InterruptedException ) {
            label_30:
                if(!v10.get()) {
                    v13_1 = v9.get();
                    v7.unlock();
                    return v13_1;
                }

                if(v13_2 > 0) {
                    goto label_28;
                }

                try {
                    throw new InterruptedException("timeout");
                }
                catch(Throwable v13) {
                label_43:
                    v7.unlock();
                    throw v13;
                }
            }
        }
        catch(Throwable v13) {
            goto label_43;
        }
    }

    public void a(Callable arg3, a arg4) {
        this.b(new Runnable(arg3, new Handler(), arg4) {
            public void run() {
                Object v0;
                try {
                    v0 = this.a.call();
                }
                catch(Exception ) {
                    v0 = null;
                }

                this.b.post(new Runnable(v0) {
                    public void run() {
                        this.b.c.a(this.a);
                    }
                });
            }
        });
    }

    void a() {
        Object v0 = this.a;
        __monitor_enter(v0);
        try {
            if(this.c.hasMessages(1)) {
                __monitor_exit(v0);
                return;
            }

            this.b.quit();
            this.b = null;
            this.c = null;
            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_16;
        }

        throw v1;
    }

    void a(Runnable arg5) {
        arg5.run();
        Object v5 = this.a;
        __monitor_enter(v5);
        try {
            this.c.removeMessages(0);
            this.c.sendMessageDelayed(this.c.obtainMessage(0), ((long)this.f));
            __monitor_exit(v5);
            return;
        label_15:
            __monitor_exit(v5);
        }
        catch(Throwable v0) {
            goto label_15;
        }

        throw v0;
    }

    private void b(Runnable arg6) {
        Object v0 = this.a;
        __monitor_enter(v0);
        try {
            if(this.b == null) {
                this.b = new HandlerThread(this.h, this.g);
                this.b.start();
                this.c = new Handler(this.b.getLooper(), this.e);
                ++this.d;
            }

            this.c.removeMessages(0);
            this.c.sendMessage(this.c.obtainMessage(1, arg6));
            __monitor_exit(v0);
            return;
        label_31:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_31;
        }

        throw v6;
    }
}

