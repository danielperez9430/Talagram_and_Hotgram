package com.d.a.b;

import android.graphics.Bitmap;
import android.os.Handler;
import com.d.a.c.c;

final class i implements Runnable {
    private final f a;
    private final Bitmap b;
    private final g c;
    private final Handler d;

    public i(f arg1, Bitmap arg2, g arg3, Handler arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public void run() {
        c.a("PostProcess image before displaying [%s]", new Object[]{this.c.b});
        h.a(new b(this.c.e.p().a(this.b), this.c, this.a, com.d.a.b.a.f.c), this.c.e.s(), this.d, this.a);
    }
}

