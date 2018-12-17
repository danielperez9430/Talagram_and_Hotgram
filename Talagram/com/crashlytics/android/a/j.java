package com.crashlytics.android.a;

import c.a.a.a.a.c.a.b;
import c.a.a.a.a.c.a.c;
import c.a.a.a.a.c.a.e;
import c.a.a.a.a.d.f;
import java.util.List;

class j implements f {
    private final aa a;
    private final x b;

    j(aa arg1, x arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public static j a(aa arg5) {
        return new j(arg5, new x(new e(new w(new c(1000, 8), 0.1), new b(5))));
    }

    public boolean a(List arg5) {
        long v0 = System.nanoTime();
        if(this.b.a(v0)) {
            if(this.a.a(arg5)) {
                this.b.a();
                return 1;
            }
            else {
                this.b.b(v0);
            }
        }

        return 0;
    }
}

