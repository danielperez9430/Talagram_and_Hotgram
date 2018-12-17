package com.crashlytics.android.a;

import android.content.Context;
import c.a.a.a.c;
import c.a.a.a.l;

class r {
    private final Context a;
    private final t b;
    private q c;

    public r(Context arg2) {
        this(arg2, new t());
    }

    public r(Context arg1, t arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public q a() {
        if(this.c == null) {
            this.c = k.a(this.a);
        }

        return this.c;
    }

    public void a(ad arg5) {
        q v0 = this.a();
        if(v0 == null) {
            c.h().a("Answers", "Firebase analytics logging was enabled, but not available...");
            return;
        }

        s v1 = this.b.a(arg5);
        if(v1 == null) {
            l v0_1 = c.h();
            v0_1.a("Answers", "Fabric event was not mappable to Firebase event: " + arg5);
            return;
        }

        v0.a(v1.a(), v1.b());
        if("levelEnd".equals(arg5.g)) {
            v0.a("post_score", v1.b());
        }
    }
}

