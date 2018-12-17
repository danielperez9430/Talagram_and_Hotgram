package c.a.a.a.a.b;

import android.os.SystemClock;
import android.util.Log;

public class u {
    private final String a;
    private final String b;
    private final boolean c;
    private long d;
    private long e;

    public u(String arg1, String arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = Log.isLoggable(arg2, 2) ^ 1;
    }

    public void a() {
        __monitor_enter(this);
        try {
            if(!this.c) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_12;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            this.d = SystemClock.elapsedRealtime();
            this.e = 0;
        }
        catch(Throwable v0) {
        label_12:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public void b() {
        __monitor_enter(this);
        try {
            if(!this.c) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_19;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            if(this.e == 0) {
                goto label_10;
            }
        }
        catch(Throwable v0) {
            goto label_19;
        }

        __monitor_exit(this);
        return;
        try {
        label_10:
            this.e = SystemClock.elapsedRealtime() - this.d;
            this.c();
        }
        catch(Throwable v0) {
        label_19:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private void c() {
        String v0 = this.b;
        Log.v(v0, this.a + ": " + this.e + "ms");
    }
}

