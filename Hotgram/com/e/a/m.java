package com.e.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Map;

public class m {
    final class com.e.a.m$1 extends Handler {
        com.e.a.m$1(Looper arg1) {
            super(arg1);
        }

        public void handleMessage(Message arg5) {
            Object v1;
            Object v5;
            int v0 = arg5.what;
            if(v0 != 3) {
                int v2 = 0;
                if(v0 == 8) {
                    v5 = arg5.obj;
                    v0 = ((List)v5).size();
                    while(v2 < v0) {
                        v1 = ((List)v5).get(v2);
                        ((b)v1).a.a(((b)v1));
                        ++v2;
                    }
                }
                else if(v0 == 13) {
                    v5 = arg5.obj;
                    v0 = ((List)v5).size();
                    while(v2 < v0) {
                        v1 = ((List)v5).get(v2);
                        ((a)v1).a.c(((a)v1));
                        ++v2;
                    }
                }
                else {
                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Unknown handler message received: ");
                    v1_1.append(arg5.what);
                    throw new AssertionError(v1_1.toString());
                }
            }
            else {
                v5 = arg5.obj;
                if(((a)v5).g().k) {
                    v.a("Main", "canceled", ((a)v5).b.a(), "target got garbage collected");
                }

                m.a(((a)v5).a, ((a)v5).c());
            }
        }
    }

    public interface com.e.a.m$a {
        void a(m arg1, Uri arg2, Exception arg3);
    }

    public enum com.e.a.m$b {
        public static final enum com.e.a.m$b a;
        public static final enum com.e.a.m$b b;
        public static final enum com.e.a.m$b c;
        final int d;

        static {
            com.e.a.m$b.a = new com.e.a.m$b("MEMORY", 0, -16711936);
            com.e.a.m$b.b = new com.e.a.m$b("DISK", 1, -16776961);
            com.e.a.m$b.c = new com.e.a.m$b("NETWORK", 2, -65536);
            com.e.a.m$b.e = new com.e.a.m$b[]{com.e.a.m$b.a, com.e.a.m$b.b, com.e.a.m$b.c};
        }

        private com.e.a.m$b(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.d = arg3;
        }

        public static com.e.a.m$b valueOf(String arg1) {
            return Enum.valueOf(com.e.a.m$b.class, arg1);
        }

        public static com.e.a.m$b[] values() {
            // Method was not decompiled
        }
    }

    public enum c {
        public static final enum c a;
        public static final enum c b;
        public static final enum c c;

        static {
            c.a = new c("LOW", 0);
            c.b = new c("NORMAL", 1);
            c.c = new c("HIGH", 2);
            c.d = new c[]{c.a, c.b, c.c};
        }

        private c(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static c valueOf(String arg1) {
            return Enum.valueOf(c.class, arg1);
        }

        public static c[] values() {
            // Method was not decompiled
        }
    }

    public interface d {
        final class com.e.a.m$d$1 implements d {
            com.e.a.m$d$1() {
                super();
            }

            public o a(o arg1) {
                return arg1;
            }
        }

        public static final d a;

        static {
            d.a = new com.e.a.m$d$1();
        }

        o a(o arg1);
    }

    static final Handler a;
    static volatile m b;
    final Context c;
    final f d;
    final com.e.a.c e;
    final r f;
    final Map g;
    final Map h;
    final ReferenceQueue i;
    boolean j;
    volatile boolean k;
    private final com.e.a.m$a l;
    private final d m;

    static {
        m.a = new com.e.a.m$1(Looper.getMainLooper());
        m.b = null;
    }

    private void a(Bitmap arg4, com.e.a.m$b arg5, a arg6) {
        if(arg6.e()) {
            return;
        }

        if(!arg6.f()) {
            this.g.remove(arg6.c());
        }

        if(arg4 == null) {
            arg6.a();
            if(this.k) {
                v.a("Main", "errored", arg6.b.a());
            }
        }
        else if(arg5 != null) {
            arg6.a(arg4, arg5);
            if(this.k) {
                String v6 = arg6.b.a();
                v.a("Main", "completed", v6, "from " + arg5);
            }
        }
        else {
            throw new AssertionError("LoadedFrom cannot be null.");
        }
    }

    static void a(m arg0, Object arg1) {
        arg0.a(arg1);
    }

    private void a(Object arg3) {
        v.a();
        Object v0 = this.g.remove(arg3);
        if(v0 != null) {
            ((a)v0).b();
            this.d.b(((a)v0));
        }

        if((arg3 instanceof ImageView)) {
            arg3 = this.h.remove(arg3);
            if(arg3 != null) {
                ((e)arg3).a();
            }
        }
    }

    Bitmap a(String arg2) {
        Bitmap v2 = this.e.a(arg2);
        if(v2 != null) {
            this.f.a();
        }
        else {
            this.f.b();
        }

        return v2;
    }

    o a(o arg4) {
        o v0 = this.m.a(arg4);
        if(v0 != null) {
            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Request transformer ");
        v1.append(this.m.getClass().getCanonicalName());
        v1.append(" returned null for ");
        v1.append(arg4);
        throw new IllegalStateException(v1.toString());
    }

    public void a(ImageView arg1) {
        this.a(arg1);
    }

    void a(ImageView arg2, e arg3) {
        this.h.put(arg2, arg3);
    }

    void a(a arg3) {
        Object v0 = arg3.c();
        if(v0 != null && this.g.get(v0) != arg3) {
            this.a(v0);
            this.g.put(v0, arg3);
        }

        this.b(arg3);
    }

    void a(b arg8) {
        a v0 = arg8.d();
        List v1 = arg8.e();
        int v2 = 1;
        int v3 = 0;
        int v4 = v1 == null || (v1.isEmpty()) ? 0 : 1;
        if(v0 == null) {
            if(v4 != 0) {
            }
            else {
                v2 = 0;
            }
        }

        if(v2 == 0) {
            return;
        }

        Uri v2_1 = arg8.c().d;
        Exception v5 = arg8.f();
        Bitmap v6 = arg8.b();
        com.e.a.m$b v8 = arg8.g();
        if(v0 != null) {
            this.a(v6, v8, v0);
        }

        if(v4 != 0) {
            int v0_1 = v1.size();
            while(v3 < v0_1) {
                this.a(v6, v8, v1.get(v3));
                ++v3;
            }
        }

        if(this.l != null && v5 != null) {
            this.l.a(this, v2_1, v5);
        }
    }

    void b(a arg2) {
        this.d.a(arg2);
    }

    void c(a arg5) {
        Bitmap v0 = j.a(arg5.e) ? this.a(arg5.d()) : null;
        if(v0 != null) {
            this.a(v0, com.e.a.m$b.a, arg5);
            if(this.k) {
                String v5 = arg5.b.a();
                v.a("Main", "completed", v5, "from " + com.e.a.m$b.a);
            }
        }
        else {
            this.a(arg5);
            if(this.k) {
                v.a("Main", "resumed", arg5.b.a());
            }
        }
    }
}

