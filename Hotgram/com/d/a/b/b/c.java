package com.d.a.b.b;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory$Options;
import android.os.Build$VERSION;
import com.d.a.b.a.d;
import com.d.a.b.a.e;
import com.d.a.b.a.h;
import com.d.a.b.d.b;

public class c {
    private final String a;
    private final String b;
    private final String c;
    private final e d;
    private final d e;
    private final h f;
    private final b g;
    private final Object h;
    private final boolean i;
    private final BitmapFactory$Options j;

    public c(String arg1, String arg2, String arg3, e arg4, h arg5, b arg6, com.d.a.b.c arg7) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg7.j();
        this.f = arg5;
        this.g = arg6;
        this.h = arg7.n();
        this.i = arg7.m();
        this.j = new BitmapFactory$Options();
        this.a(arg7.k(), this.j);
    }

    private void a(BitmapFactory$Options arg3, BitmapFactory$Options arg4) {
        arg4.inDensity = arg3.inDensity;
        arg4.inDither = arg3.inDither;
        arg4.inInputShareable = arg3.inInputShareable;
        arg4.inJustDecodeBounds = arg3.inJustDecodeBounds;
        arg4.inPreferredConfig = arg3.inPreferredConfig;
        arg4.inPurgeable = arg3.inPurgeable;
        arg4.inSampleSize = arg3.inSampleSize;
        arg4.inScaled = arg3.inScaled;
        arg4.inScreenDensity = arg3.inScreenDensity;
        arg4.inTargetDensity = arg3.inTargetDensity;
        arg4.inTempStorage = arg3.inTempStorage;
        if(Build$VERSION.SDK_INT >= 10) {
            this.b(arg3, arg4);
        }

        if(Build$VERSION.SDK_INT >= 11) {
            this.c(arg3, arg4);
        }
    }

    public String a() {
        return this.a;
    }

    @TargetApi(value=10) private void b(BitmapFactory$Options arg1, BitmapFactory$Options arg2) {
        arg2.inPreferQualityOverSpeed = arg1.inPreferQualityOverSpeed;
    }

    public String b() {
        return this.b;
    }

    @TargetApi(value=11) private void c(BitmapFactory$Options arg2, BitmapFactory$Options arg3) {
        arg3.inBitmap = arg2.inBitmap;
        arg3.inMutable = arg2.inMutable;
    }

    public e c() {
        return this.d;
    }

    public d d() {
        return this.e;
    }

    public h e() {
        return this.f;
    }

    public b f() {
        return this.g;
    }

    public Object g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public BitmapFactory$Options i() {
        return this.j;
    }
}

