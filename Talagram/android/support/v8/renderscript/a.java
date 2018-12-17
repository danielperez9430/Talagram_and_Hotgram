package android.support.v8.renderscript;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.Canvas;
import android.util.Log;
import java.nio.ByteBuffer;

public class a extends b {
    class android.support.v8.renderscript.a$1 {
        static {
            android.support.v8.renderscript.a$1.a = new int[Bitmap$Config.values().length];
            try {
                android.support.v8.renderscript.a$1.a[Bitmap$Config.ALPHA_8.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    android.support.v8.renderscript.a$1.a[Bitmap$Config.ARGB_8888.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        android.support.v8.renderscript.a$1.a[Bitmap$Config.RGB_565.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            android.support.v8.renderscript.a$1.a[Bitmap$Config.ARGB_4444.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public enum android.support.v8.renderscript.a$a {
        public static final enum android.support.v8.renderscript.a$a a;
        public static final enum android.support.v8.renderscript.a$a b;
        public static final enum android.support.v8.renderscript.a$a c;
        int d;

        static {
            android.support.v8.renderscript.a$a.a = new android.support.v8.renderscript.a$a("MIPMAP_NONE", 0, 0);
            android.support.v8.renderscript.a$a.b = new android.support.v8.renderscript.a$a("MIPMAP_FULL", 1, 1);
            android.support.v8.renderscript.a$a.c = new android.support.v8.renderscript.a$a("MIPMAP_ON_SYNC_TO_TEXTURE", 2, 2);
            android.support.v8.renderscript.a$a.e = new android.support.v8.renderscript.a$a[]{android.support.v8.renderscript.a$a.a, android.support.v8.renderscript.a$a.b, android.support.v8.renderscript.a$a.c};
        }

        private android.support.v8.renderscript.a$a(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.d = arg3;
        }

        public static android.support.v8.renderscript.a$a valueOf(String arg1) {
            return Enum.valueOf(android.support.v8.renderscript.a$a.class, arg1);
        }

        public static android.support.v8.renderscript.a$a[] values() {
            // Method was not decompiled
        }
    }

    l a;
    Bitmap b;
    int c;
    int d;
    ByteBuffer e;
    long f;
    boolean g;
    boolean h;
    boolean i;
    android.support.v8.renderscript.l$b j;
    int k;
    int l;
    int m;
    int n;
    long o;
    boolean p;
    static BitmapFactory$Options q;

    static {
        a.q = new BitmapFactory$Options();
        a.q.inScaled = false;
    }

    a(long arg3, RenderScript arg5, l arg6, int arg7) {
        super(arg3, arg5);
        this.e = null;
        arg3 = 0;
        this.f = arg3;
        this.g = true;
        this.h = true;
        this.i = false;
        this.j = android.support.v8.renderscript.l$b.a;
        if((arg7 & -228) == 0) {
            if((arg7 & 32) != 0) {
                this.h = false;
                if((arg7 & -36) == 0) {
                }
                else {
                    throw new f("Invalid usage combination.");
                }
            }

            this.a = arg6;
            this.c = arg7;
            this.o = arg3;
            this.p = false;
            if(arg6 != null) {
                this.d = this.a.i() * this.a.a().a();
                this.a(arg6);
            }

            if(RenderScript.b) {
                try {
                    RenderScript.d.invoke(RenderScript.c, Integer.valueOf(this.d));
                }
                catch(Exception v3) {
                    Log.e("RenderScript_jni", "Couldn\'t invoke registerNativeAllocation:" + v3);
                    StringBuilder v5 = new StringBuilder();
                    v5.append("Couldn\'t invoke registerNativeAllocation:");
                    v5.append(v3);
                    throw new h(v5.toString());
                }
            }

            return;
        }

        throw new f("Unknown usage specified.");
    }

    public c a() {
        return this.a.a();
    }

    public void a(Bitmap arg4) {
        this.r.f();
        this.c(arg4);
        this.d(arg4);
        this.r.a(this.a(this.r), arg4);
    }

    private void a(l arg3) {
        this.k = arg3.b();
        this.l = arg3.e();
        this.m = arg3.f();
        this.n = this.k;
        if(this.l > 1) {
            this.n *= this.l;
        }

        if(this.m > 1) {
            this.n *= this.m;
        }
    }

    public static a a(RenderScript arg11, Bitmap arg12, android.support.v8.renderscript.a$a arg13, int arg14) {
        arg11.f();
        if(arg12.getConfig() == null) {
            if((arg14 & 128) == 0) {
                Bitmap v0 = Bitmap.createBitmap(arg12.getWidth(), arg12.getHeight(), Bitmap$Config.ARGB_8888);
                new Canvas(v0).drawBitmap(arg12, 0f, 0f, null);
                return a.a(arg11, v0, arg13, arg14);
            }

            throw new f("USAGE_SHARED cannot be used with a Bitmap that has a null config.");
        }

        l v4 = a.a(arg11, arg12, arg13);
        long v1 = 0;
        if(arg13 == android.support.v8.renderscript.a$a.a && (v4.a().a(c.f(arg11))) && arg14 == 131) {
            long v5 = arg11.b(v4.a(arg11), arg13.d, arg12, arg14);
            if(v5 != v1) {
                a v13 = new a(v5, arg11, v4, arg14);
                v13.b(arg12);
                return v13;
            }
            else {
                throw new h("Load failed.");
            }
        }

        long v12 = arg11.a(v4.a(arg11), arg13.d, arg12, arg14);
        if(v12 != v1) {
            return new a(v12, arg11, v4, arg14);
        }

        throw new h("Load failed.");
    }

    static l a(RenderScript arg2, Bitmap arg3, android.support.v8.renderscript.a$a arg4) {
        android.support.v8.renderscript.l$a v1 = new android.support.v8.renderscript.l$a(arg2, a.a(arg2, arg3));
        v1.a(arg3.getWidth());
        v1.b(arg3.getHeight());
        boolean v2 = arg4 == android.support.v8.renderscript.a$a.b ? true : false;
        v1.a(v2);
        return v1.a();
    }

    static c a(RenderScript arg2, Bitmap arg3) {
        Bitmap$Config v3 = arg3.getConfig();
        if(v3 == Bitmap$Config.ALPHA_8) {
            return c.c(arg2);
        }

        if(v3 == Bitmap$Config.ARGB_4444) {
            return c.e(arg2);
        }

        if(v3 == Bitmap$Config.ARGB_8888) {
            return c.f(arg2);
        }

        if(v3 == Bitmap$Config.RGB_565) {
            return c.d(arg2);
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("Bad bitmap type: ");
        v0.append(v3);
        throw new g(v0.toString());
    }

    public void a(long arg1) {
        this.o = arg1;
    }

    public static a b(RenderScript arg2, Bitmap arg3) {
        return a.a(arg2, arg3, android.support.v8.renderscript.a$a.a, 131);
    }

    private void b(Bitmap arg1) {
        this.b = arg1;
    }

    public l b() {
        return this.a;
    }

    private void c(Bitmap arg4) {
        StringBuilder v1_1;
        Bitmap$Config v4 = arg4.getConfig();
        if(v4 == null) {
            goto label_154;
        }

        int v1 = 2;
        switch(android.support.v8.renderscript.a$1.a[v4.ordinal()]) {
            case 1: {
                if(this.a.a().c == android.support.v8.renderscript.c$a.c) {
                    return;
                }

                v1_1 = new StringBuilder();
                v1_1.append("Allocation kind is ");
                v1_1.append(this.a.a().c);
                v1_1.append(", type ");
                v1_1.append(this.a.a().b);
                v1_1.append(" of ");
                v1_1.append(this.a.a().a());
                v1_1.append(" bytes, passed bitmap was ");
                v1_1.append(v4);
                throw new f(v1_1.toString());
            }
            case 2: {
                if(this.a.a().c == android.support.v8.renderscript.c$a.f && this.a.a().a() == 4) {
                    return;
                }

                v1_1 = new StringBuilder();
                v1_1.append("Allocation kind is ");
                v1_1.append(this.a.a().c);
                v1_1.append(", type ");
                v1_1.append(this.a.a().b);
                v1_1.append(" of ");
                v1_1.append(this.a.a().a());
                v1_1.append(" bytes, passed bitmap was ");
                v1_1.append(v4);
                throw new f(v1_1.toString());
            }
            case 3: {
                if(this.a.a().c == android.support.v8.renderscript.c$a.e && this.a.a().a() == v1) {
                    return;
                }

                goto label_55;
            }
            case 4: {
                if(this.a.a().c == android.support.v8.renderscript.c$a.f && this.a.a().a() == v1) {
                    return;
                }

                v1_1 = new StringBuilder();
                v1_1.append("Allocation kind is ");
                v1_1.append(this.a.a().c);
                v1_1.append(", type ");
                v1_1.append(this.a.a().b);
                v1_1.append(" of ");
                v1_1.append(this.a.a().a());
                v1_1.append(" bytes, passed bitmap was ");
                v1_1.append(v4);
                throw new f(v1_1.toString());
            }
            default: {
                break;
            }
        }

        return;
    label_55:
        v1_1 = new StringBuilder();
        v1_1.append("Allocation kind is ");
        v1_1.append(this.a.a().c);
        v1_1.append(", type ");
        v1_1.append(this.a.a().b);
        v1_1.append(" of ");
        v1_1.append(this.a.a().a());
        v1_1.append(" bytes, passed bitmap was ");
        v1_1.append(v4);
        throw new f(v1_1.toString());
    label_154:
        throw new f("Bitmap has an unsupported format for this operation");
    }

    private void d(Bitmap arg3) {
        if(this.k == arg3.getWidth() && this.l == arg3.getHeight()) {
            return;
        }

        throw new f("Cannot update allocation from bitmap, sizes mismatch");
    }

    protected void finalize() {
        if(RenderScript.b) {
            RenderScript.e.invoke(RenderScript.c, Integer.valueOf(this.d));
        }

        super.finalize();
    }
}

