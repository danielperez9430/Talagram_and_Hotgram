package com.d.a.b;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.d.a.b.a.h;
import com.d.a.b.e.b;
import com.d.a.b.f.a;
import com.d.a.b.f.c;

public class d {
    public static final String a = "d";
    private e b;
    private f c;
    private a d;
    private static volatile d e;

    static {
    }

    protected d() {
        super();
        this.d = new c();
    }

    private static Handler a(com.d.a.b.c arg2) {
        Handler v0 = arg2.r();
        if(arg2.s()) {
            v0 = null;
        }
        else if(v0 == null && Looper.myLooper() == Looper.getMainLooper()) {
            v0 = new Handler();
        }

        return v0;
    }

    public static d a() {
        if(d.e == null) {
            Class v0 = d.class;
            __monitor_enter(v0);
            try {
                if(d.e == null) {
                    d.e = new d();
                }

                __monitor_exit(v0);
                goto label_14;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_12;
            }

            throw v1;
        }

    label_14:
        return d.e;
    }

    public void a(e arg3) {
        __monitor_enter(this);
        if(arg3 != null) {
            try {
                if(this.b == null) {
                    com.d.a.c.c.a("Initialize ImageLoader with configuration", new Object[0]);
                    this.c = new f(arg3);
                    this.b = arg3;
                }
                else {
                    com.d.a.c.c.c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
                }
            }
            catch(Throwable v3) {
                goto label_23;
            }

            __monitor_exit(this);
            return;
        }

        try {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        }
        catch(Throwable v3) {
        label_23:
            __monitor_exit(this);
            throw v3;
        }
    }

    public void a(String arg7, ImageView arg8) {
        this.a(arg7, new b(arg8), null, null, null);
    }

    public void a(String arg8, com.d.a.b.e.a arg9, com.d.a.b.c arg10, a arg11, com.d.a.b.f.b arg12) {
        this.a(arg8, arg9, arg10, null, arg11, arg12);
    }

    public void a(String arg7, ImageView arg8, com.d.a.b.c arg9) {
        this.a(arg7, new b(arg8), arg9, null, null);
    }

    public void a(String arg7, ImageView arg8, com.d.a.b.c arg9, a arg10) {
        this.a(arg7, arg8, arg9, arg10, null);
    }

    public void a(String arg7, ImageView arg8, com.d.a.b.c arg9, a arg10, com.d.a.b.f.b arg11) {
        this.a(arg7, new b(arg8), arg9, arg10, arg11);
    }

    public void a(String arg7, com.d.a.b.a.e arg8, com.d.a.b.c arg9, a arg10, com.d.a.b.f.b arg11) {
        this.b();
        if(arg8 == null) {
            arg8 = this.b.a();
        }

        if(arg9 == null) {
            arg9 = this.b.r;
        }

        this.a(arg7, new com.d.a.b.e.c(arg7, arg8, h.b), arg9, arg10, arg11);
    }

    public void a(String arg7, com.d.a.b.c arg8, a arg9) {
        this.a(arg7, null, arg8, arg9, null);
    }

    public void a(String arg10, com.d.a.b.e.a arg11, com.d.a.b.c arg12, com.d.a.b.a.e arg13, a arg14, com.d.a.b.f.b arg15) {
        this.b();
        if(arg11 != null) {
            if(arg14 == null) {
                arg14 = this.d;
            }

            a v6 = arg14;
            if(arg12 == null) {
                arg12 = this.b.r;
            }

            Drawable v0 = null;
            if(TextUtils.isEmpty(((CharSequence)arg10))) {
                this.c.b(arg11);
                v6.onLoadingStarted(arg10, arg11.d());
                if(arg12.b()) {
                    arg11.a(arg12.b(this.b.a));
                }
                else {
                    arg11.a(v0);
                }

                v6.onLoadingComplete(arg10, arg11.d(), ((Bitmap)v0));
                return;
            }

            if(arg13 == null) {
                arg13 = com.d.a.c.a.a(arg11, this.b.a());
            }

            com.d.a.b.a.e v3 = arg13;
            String v4 = com.d.a.c.d.a(arg10, v3);
            this.c.a(arg11, v4);
            v6.onLoadingStarted(arg10, arg11.d());
            Bitmap v13 = this.b.n.a(v4);
            if(v13 == null || (v13.isRecycled())) {
                if(arg12.a()) {
                    arg11.a(arg12.a(this.b.a));
                }
                else if(arg12.g()) {
                    arg11.a(v0);
                }

                com.d.a.b.h v10_1 = new com.d.a.b.h(this.c, new g(arg10, arg11, v3, v4, arg12, v6, arg15, this.c.a(arg10)), d.a(arg12));
                if(arg12.s()) {
                    v10_1.run();
                    return;
                }

                this.c.a(v10_1);
            }
            else {
                com.d.a.c.c.a("Load image from memory cache [%s]", new Object[]{v4});
                if(arg12.e()) {
                    i v10 = new i(this.c, v13, new g(arg10, arg11, v3, v4, arg12, v6, arg15, this.c.a(arg10)), d.a(arg12));
                    if(arg12.s()) {
                        v10.run();
                    }
                    else {
                        this.c.a(v10);
                    }
                }
                else {
                    arg12.q().a(v13, arg11, com.d.a.b.a.f.c);
                    v6.onLoadingComplete(arg10, arg11.d(), v13);
                }
            }

            return;
        }

        throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
    }

    public void a(String arg7, a arg8) {
        this.a(arg7, null, null, arg8, null);
    }

    private void b() {
        if(this.b != null) {
            return;
        }

        throw new IllegalStateException("ImageLoader must be init with configuration before using");
    }
}

