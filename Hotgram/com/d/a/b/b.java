package com.d.a.b;

import android.graphics.Bitmap;
import com.d.a.b.e.a;
import com.d.a.c.c;

final class b implements Runnable {
    private final Bitmap a;
    private final String b;
    private final a c;
    private final String d;
    private final com.d.a.b.c.a e;
    private final com.d.a.b.f.a f;
    private final f g;
    private final com.d.a.b.a.f h;

    public b(Bitmap arg1, g arg2, f arg3, com.d.a.b.a.f arg4) {
        super();
        this.a = arg1;
        this.b = arg2.a;
        this.c = arg2.c;
        this.d = arg2.b;
        this.e = arg2.e.q();
        this.f = arg2.f;
        this.g = arg3;
        this.h = arg4;
    }

    private boolean a() {
        return this.d.equals(this.g.a(this.c)) ^ 1;
    }

    public void run() {
        Object[] v2;
        String v0;
        if(this.c.e()) {
            v0 = "ImageAware was collected by GC. Task is cancelled. [%s]";
            v2 = new Object[]{this.d};
            goto label_9;
        }
        else if(this.a()) {
            v0 = "ImageAware is reused for another image. Task is cancelled. [%s]";
            v2 = new Object[]{this.d};
        label_9:
            c.a(v0, v2);
            this.f.onLoadingCancelled(this.b, this.c.d());
        }
        else {
            c.a("Display image in ImageAware (loaded from %1$s) [%2$s]", new Object[]{this.h, this.d});
            this.e.a(this.a, this.c, this.h);
            this.g.b(this.c);
            this.f.onLoadingComplete(this.b, this.c.d(), this.a);
        }
    }
}

