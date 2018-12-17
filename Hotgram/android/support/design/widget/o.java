package android.support.design.widget;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class o {
    interface a {
        void a(int arg1);

        void a();
    }

    class b {
        final WeakReference a;
        int b;
        boolean c;

        boolean a(a arg2) {
            boolean v2 = arg2 == null || this.a.get() != arg2 ? false : true;
            return v2;
        }
    }

    private static o a;
    private final Object b;
    private final Handler c;
    private b d;
    private b e;

    private o() {
        super();
        this.b = new Object();
        this.c = new Handler(Looper.getMainLooper(), new Handler$Callback() {
            public boolean handleMessage(Message arg2) {
                if(arg2.what != 0) {
                    return 0;
                }

                this.a.a(arg2.obj);
                return 1;
            }
        });
    }

    public void a(a arg3, int arg4) {
        b v3_1;
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if(this.f(arg3)) {
                v3_1 = this.d;
                goto label_5;
            }
            else if(this.g(arg3)) {
                v3_1 = this.e;
            label_5:
                this.a(v3_1, arg4);
            }

            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    static o a() {
        if(o.a == null) {
            o.a = new o();
        }

        return o.a;
    }

    public void a(a arg2) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if(this.f(arg2)) {
                this.d = null;
                if(this.e != null) {
                    this.b();
                }
            }

            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }

    void a(b arg3) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if(this.d == arg3 || this.e == arg3) {
                this.a(arg3, 2);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    private boolean a(b arg3, int arg4) {
        Object v0 = arg3.a.get();
        if(v0 != null) {
            this.c.removeCallbacksAndMessages(arg3);
            ((a)v0).a(arg4);
            return 1;
        }

        return 0;
    }

    public void b(a arg2) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if(this.f(arg2)) {
                this.b(this.d);
            }

            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_9;
        }

        throw v2;
    }

    private void b() {
        if(this.e != null) {
            this.d = this.e;
            b v0 = null;
            this.e = v0;
            Object v1 = this.d.a.get();
            if(v1 != null) {
                ((a)v1).a();
            }
            else {
                this.d = v0;
            }
        }
    }

    private void b(b arg5) {
        if(arg5.b == -2) {
            return;
        }

        int v0 = 2750;
        if(arg5.b > 0) {
            v0 = arg5.b;
        }
        else if(arg5.b == -1) {
            v0 = 1500;
        }

        this.c.removeCallbacksAndMessages(arg5);
        this.c.sendMessageDelayed(Message.obtain(this.c, 0, arg5), ((long)v0));
    }

    public void c(a arg3) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if((this.f(arg3)) && !this.d.c) {
                this.d.c = true;
                this.c.removeCallbacksAndMessages(this.d);
            }

            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_16;
        }

        throw v3;
    }

    public void d(a arg3) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if((this.f(arg3)) && (this.d.c)) {
                this.d.c = false;
                this.b(this.d);
            }

            __monitor_exit(v0);
            return;
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_15;
        }

        throw v3;
    }

    public boolean e(a arg3) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            boolean v3_1 = (this.f(arg3)) || (this.g(arg3)) ? true : false;
            __monitor_exit(v0);
            return v3_1;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    private boolean f(a arg2) {
        boolean v2 = this.d == null || !this.d.a(arg2) ? false : true;
        return v2;
    }

    private boolean g(a arg2) {
        boolean v2 = this.e == null || !this.e.a(arg2) ? false : true;
        return v2;
    }
}

