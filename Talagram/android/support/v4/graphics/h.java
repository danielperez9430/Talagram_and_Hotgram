package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.v4.content.a.c$c;
import android.support.v4.d.b$b;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

class h {
    interface a {
        boolean a(Object arg1);

        int b(Object arg1);
    }

    h() {
        super();
    }

    public Typeface a(Context arg1, Resources arg2, int arg3, String arg4, int arg5) {
        Typeface v2_1;
        File v1 = i.a(arg1);
        Typeface v4 = null;
        if(v1 == null) {
            return v4;
        }

        try {
            if(i.a(v1, arg2, arg3)) {
                goto label_8;
            }
        }
        catch(Throwable v2) {
            goto label_13;
        }
        catch(RuntimeException ) {
            goto label_15;
        }

        v1.delete();
        return v4;
        try {
        label_8:
            v2_1 = Typeface.createFromFile(v1.getPath());
        }
        catch(Throwable v2) {
        label_13:
            v1.delete();
            throw v2;
        }
        catch(RuntimeException ) {
        label_15:
            v1.delete();
            return v4;
        }

        v1.delete();
        return v2_1;
    }

    public Typeface a(Context arg3, CancellationSignal arg4, b[] arg5, int arg6) {
        InputStream v0_1;
        Typeface v3_1;
        Closeable v4_2;
        InputStream v4_1;
        Typeface v0 = null;
        if(arg5.length < 1) {
            return v0;
        }

        b v4 = this.a(arg5, arg6);
        try {
            v4_1 = arg3.getContentResolver().openInputStream(v4.a());
        }
        catch(Throwable v3) {
            goto label_16;
        }
        catch(IOException ) {
            v4_2 = ((Closeable)v0);
            goto label_19;
        }

        try {
            v3_1 = this.a(arg3, v4_1);
            goto label_10;
        }
        catch(Throwable v3) {
            v0_1 = v4_1;
        }
        catch(IOException ) {
        label_19:
            i.a(v4_2);
            return v0;
        }

    label_16:
        i.a(((Closeable)v0_1));
        throw v3;
    label_10:
        i.a(((Closeable)v4_1));
        return v3_1;
    }

    public Typeface a(Context arg2, android.support.v4.content.a.c$b arg3, Resources arg4, int arg5) {
        c v3 = this.a(arg3, arg5);
        if(v3 == null) {
            return null;
        }

        return android.support.v4.graphics.c.a(arg2, arg4, v3.f(), v3.a(), arg5);
    }

    private c a(android.support.v4.content.a.c$b arg2, int arg3) {
        return h.a(arg2.a(), arg3, new a() {
            public int a(c arg1) {
                return arg1.b();
            }

            public boolean a(Object arg1) {
                return this.b(((c)arg1));
            }

            public boolean b(c arg1) {
                return arg1.c();
            }

            public int b(Object arg1) {
                return this.a(((c)arg1));
            }
        });
    }

    private static Object a(Object[] arg10, int arg11, a arg12) {
        int v0 = (arg11 & 1) == 0 ? 400 : 700;
        boolean v11 = (arg11 & 2) != 0 ? true : false;
        int v5 = arg10.length;
        Object v4 = null;
        int v3 = 0;
        int v6 = 2147483647;
        while(v3 < v5) {
            Object v7 = arg10[v3];
            int v8 = Math.abs(arg12.b(v7) - v0) * 2;
            int v9 = arg12.a(v7) == v11 ? 0 : 1;
            v8 += v9;
            if(v4 == null || v6 > v8) {
                v4 = v7;
                v6 = v8;
            }

            ++v3;
        }

        return v4;
    }

    protected b a(b[] arg2, int arg3) {
        return h.a(((Object[])arg2), arg3, new a() {
            public int a(b arg1) {
                return arg1.c();
            }

            public boolean a(Object arg1) {
                return this.b(((b)arg1));
            }

            public boolean b(b arg1) {
                return arg1.d();
            }

            public int b(Object arg1) {
                return this.a(((b)arg1));
            }
        });
    }

    protected Typeface a(Context arg2, InputStream arg3) {
        Typeface v3_1;
        File v2 = i.a(arg2);
        Typeface v0 = null;
        if(v2 == null) {
            return v0;
        }

        try {
            if(i.a(v2, arg3)) {
                goto label_8;
            }
        }
        catch(Throwable v3) {
            goto label_13;
        }
        catch(RuntimeException ) {
            goto label_15;
        }

        v2.delete();
        return v0;
        try {
        label_8:
            v3_1 = Typeface.createFromFile(v2.getPath());
        }
        catch(Throwable v3) {
        label_13:
            v2.delete();
            throw v3;
        }
        catch(RuntimeException ) {
        label_15:
            v2.delete();
            return v0;
        }

        v2.delete();
        return v3_1;
    }
}

