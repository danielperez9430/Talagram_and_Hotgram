package okhttp3.internal.e;

import e.c;
import e.d;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

final class j implements Closeable {
    final b a;
    private static final Logger b;
    private final d c;
    private final boolean d;
    private final c e;
    private int f;
    private boolean g;

    static {
        j.b = Logger.getLogger(e.class.getName());
    }

    j(d arg1, boolean arg2) {
        super();
        this.c = arg1;
        this.d = arg2;
        this.e = new c();
        this.a = new b(this.e);
        this.f = 16384;
    }

    public void a(boolean arg1, int arg2, int arg3, List arg4) {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_6;
            }

            this.a(arg1, arg2, arg4);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        __monitor_exit(this);
        return;
        try {
        label_6:
            throw new IOException("closed");
        }
        catch(Throwable v1) {
        label_11:
            __monitor_exit(this);
            throw v1;
        }
    }

    public void a(int arg8, int arg9, List arg10) {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_33;
            }

            this.a.a(arg10);
            long v0 = this.e.b();
            int v2 = 4;
            int v10 = ((int)Math.min(((long)(this.f - v2)), v0));
            byte v3 = 5;
            long v4 = ((long)v10);
            byte v6 = v0 == v4 ? 4 : 0;
            this.a(arg8, v10 + v2, v3, v6);
            this.c.g(arg9 & 2147483647);
            this.c.a_(this.e, v4);
            if(v0 > v4) {
                this.b(arg8, v0 - v4);
            }
        }
        catch(Throwable v8) {
            goto label_38;
        }

        __monitor_exit(this);
        return;
        try {
        label_33:
            throw new IOException("closed");
        }
        catch(Throwable v8) {
        label_38:
            __monitor_exit(this);
            throw v8;
        }
    }

    public void a(int arg5, long arg6) {
        // Method was not decompiled
    }

    public void a(int arg4, okhttp3.internal.e.b arg5) {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_20;
            }

            if(arg5.l == -1) {
                goto label_17;
            }

            this.a(arg4, 4, 3, 0);
            this.c.g(arg5.l);
            this.c.flush();
        }
        catch(Throwable v4) {
            goto label_25;
        }

        __monitor_exit(this);
        return;
        try {
        label_17:
            throw new IllegalArgumentException();
        label_20:
            throw new IOException("closed");
        }
        catch(Throwable v4) {
        label_25:
            __monitor_exit(this);
            throw v4;
        }
    }

    public void a(boolean arg2, int arg3, c arg4, int arg5) {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_10;
            }

            byte v0 = 0;
            if(arg2) {
                v0 = ((byte)1);
            }

            this.a(arg3, v0, arg4, arg5);
        }
        catch(Throwable v2) {
            goto label_15;
        }

        __monitor_exit(this);
        return;
        try {
        label_10:
            throw new IOException("closed");
        }
        catch(Throwable v2) {
        label_15:
            __monitor_exit(this);
            throw v2;
        }
    }

    public void a(int arg4, okhttp3.internal.e.b arg5, byte[] arg6) {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_28;
            }

            if(arg5.l == -1) {
                goto label_24;
            }

            this.a(0, arg6.length + 8, 7, 0);
            this.c.g(arg4);
            this.c.g(arg5.l);
            if(arg6.length > 0) {
                this.c.c(arg6);
            }

            this.c.flush();
        }
        catch(Throwable v4) {
            goto label_33;
        }

        __monitor_exit(this);
        return;
        try {
        label_24:
            throw e.a("errorCode.httpCode == -1", new Object[0]);
        label_28:
            throw new IOException("closed");
        }
        catch(Throwable v4) {
        label_33:
            __monitor_exit(this);
            throw v4;
        }
    }

    public void a() {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_29;
            }

            if(this.d) {
                goto label_7;
            }
        }
        catch(Throwable v0) {
            goto label_34;
        }

        __monitor_exit(this);
        return;
        try {
        label_7:
            if(j.b.isLoggable(Level.FINE)) {
                j.b.fine(okhttp3.internal.c.a(">> CONNECTION %s", new Object[]{e.a.e()}));
            }

            this.c.c(e.a.h());
            this.c.flush();
        }
        catch(Throwable v0) {
            goto label_34;
        }

        __monitor_exit(this);
        return;
        try {
        label_29:
            throw new IOException("closed");
        }
        catch(Throwable v0) {
        label_34:
            __monitor_exit(this);
            throw v0;
        }
    }

    public void a(boolean arg4, int arg5, int arg6) {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_15;
            }

            this.a(0, 8, 6, ((byte)arg4));
            this.c.g(arg5);
            this.c.g(arg6);
            this.c.flush();
        }
        catch(Throwable v4) {
            goto label_20;
        }

        __monitor_exit(this);
        return;
        try {
        label_15:
            throw new IOException("closed");
        }
        catch(Throwable v4) {
        label_20:
            __monitor_exit(this);
            throw v4;
        }
    }

    public void a(m arg3) {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_20;
            }

            this.f = arg3.d(this.f);
            if(arg3.c() != -1) {
                this.a.a(arg3.c());
            }

            this.a(0, 0, 4, 1);
            this.c.flush();
        }
        catch(Throwable v3) {
            goto label_25;
        }

        __monitor_exit(this);
        return;
        try {
        label_20:
            throw new IOException("closed");
        }
        catch(Throwable v3) {
        label_25:
            __monitor_exit(this);
            throw v3;
        }
    }

    private static void a(d arg1, int arg2) {
        arg1.i(arg2 >>> 16 & 255);
        arg1.i(arg2 >>> 8 & 255);
        arg1.i(arg2 & 255);
    }

    public void a(int arg4, int arg5, byte arg6, byte arg7) {
        if(j.b.isLoggable(Level.FINE)) {
            j.b.fine(e.a(false, arg4, arg5, arg6, arg7));
        }

        if(arg5 <= this.f) {
            if((-2147483648 & arg4) == 0) {
                j.a(this.c, arg5);
                this.c.i(arg6 & 255);
                this.c.i(arg7 & 255);
                this.c.g(arg4 & 2147483647);
                return;
            }

            throw e.a("reserved bit set: %s", new Object[]{Integer.valueOf(arg4)});
        }

        throw e.a("FRAME_SIZE_ERROR length > %d: %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(arg5)});
    }

    void a(int arg3, byte arg4, c arg5, int arg6) {
        this.a(arg3, arg6, 0, arg4);
        if(arg6 > 0) {
            this.c.a_(arg5, ((long)arg6));
        }
    }

    void a(boolean arg6, int arg7, List arg8) {
        if(!this.g) {
            this.a.a(arg8);
            long v0 = this.e.b();
            int v8 = ((int)Math.min(((long)this.f), v0));
            long v2 = ((long)v8);
            byte v4 = v0 == v2 ? 4 : 0;
            if(arg6) {
                v4 = ((byte)(v4 | 1));
            }

            this.a(arg7, v8, 1, v4);
            this.c.a_(this.e, v2);
            if(v0 > v2) {
                this.b(arg7, v0 - v2);
            }

            return;
        }

        throw new IOException("closed");
    }

    public void b() {
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_7;
            }

            this.c.flush();
        }
        catch(Throwable v0) {
            goto label_12;
        }

        __monitor_exit(this);
        return;
        try {
        label_7:
            throw new IOException("closed");
        }
        catch(Throwable v0) {
        label_12:
            __monitor_exit(this);
            throw v0;
        }
    }

    public void b(m arg5) {
        int v0;
        __monitor_enter(this);
        try {
            if(this.g) {
                goto label_32;
            }

            int v1 = 0;
            byte v2 = 4;
            this.a(0, arg5.b() * 6, v2, 0);
            while(v1 < 10) {
                if(!arg5.a(v1)) {
                }
                else {
                    if(v1 == v2) {
                        v0 = 3;
                    }
                    else if(v1 == 7) {
                        v0 = 4;
                    }
                    else {
                        v0 = v1;
                    }

                    this.c.h(v0);
                    this.c.g(arg5.b(v1));
                }

                ++v1;
            }

            this.c.flush();
        }
        catch(Throwable v5) {
            goto label_37;
        }

        __monitor_exit(this);
        return;
        try {
        label_32:
            throw new IOException("closed");
        }
        catch(Throwable v5) {
        label_37:
            __monitor_exit(this);
            throw v5;
        }
    }

    private void b(int arg8, long arg9) {
        while(true) {
            long v0 = 0;
            if(arg9 <= v0) {
                return;
            }

            int v2 = ((int)Math.min(((long)this.f), arg9));
            long v3 = ((long)v2);
            arg9 -= v3;
            byte v5 = 9;
            byte v0_1 = arg9 == v0 ? 4 : 0;
            this.a(arg8, v2, v5, v0_1);
            this.c.a_(this.e, v3);
        }
    }

    public int c() {
        return this.f;
    }

    public void close() {
        __monitor_enter(this);
        try {
            this.g = true;
            this.c.close();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

