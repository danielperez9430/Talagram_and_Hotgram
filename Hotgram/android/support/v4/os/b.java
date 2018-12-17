package android.support.v4.os;

import android.os.Build$VERSION;
import android.os.CancellationSignal;

public final class b {
    public interface a {
        void a();
    }

    private boolean a;
    private a b;
    private Object c;
    private boolean d;

    public b() {
        super();
    }

    public void a() {
        Object v1;
        a v0_1;
        __monitor_enter(this);
        try {
            if(this.a) {
                __monitor_exit(this);
                return;
            }

            this.a = true;
            this.d = true;
            v0_1 = this.b;
            v1 = this.c;
            __monitor_exit(this);
            if(v0_1 == null) {
                goto label_17;
            }
        }
        catch(Throwable v0) {
            try {
            label_40:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_40;
            }

            throw v0;
        }

        try {
            v0_1.a();
        label_17:
            if(v1 != null && Build$VERSION.SDK_INT >= 16) {
                ((CancellationSignal)v1).cancel();
                goto label_31;
            label_16:
                goto label_23;
            }

            goto label_31;
        }
        catch(Throwable v0) {
            goto label_16;
        }

    label_23:
        __monitor_enter(this);
        try {
            this.d = false;
            this.notifyAll();
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_29;
        }

        throw v0;
        try {
        label_29:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_29;
        }

        throw v0;
    label_31:
        __monitor_enter(this);
        try {
            this.d = false;
            this.notifyAll();
            __monitor_exit(this);
            return;
        label_37:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_37;
        }

        throw v0;
    }

    public Object b() {
        if(Build$VERSION.SDK_INT < 16) {
            return null;
        }

        __monitor_enter(this);
        try {
            if(this.c == null) {
                this.c = new CancellationSignal();
                if(this.a) {
                    this.c.cancel();
                }
            }

            __monitor_exit(this);
            return this.c;
        label_19:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_19;
        }

        throw v0;
    }
}

