package com.google.a.b.a;

import com.google.a.d.c;
import com.google.a.f;
import com.google.a.j;
import com.google.a.k;
import com.google.a.r;
import com.google.a.s;
import com.google.a.v;
import com.google.a.w;

public final class l extends v {
    class com.google.a.b.a.l$1 {
    }

    final class a implements j, r {
        a(l arg1, com.google.a.b.a.l$1 arg2) {
            this(arg1);
        }

        private a(l arg1) {
            this.a = arg1;
            super();
        }
    }

    final f a;
    private final s b;
    private final k c;
    private final com.google.a.c.a d;
    private final w e;
    private final a f;
    private v g;

    public l(s arg3, k arg4, f arg5, com.google.a.c.a arg6, w arg7) {
        super();
        this.f = new a(this, null);
        this.b = arg3;
        this.c = arg4;
        this.a = arg5;
        this.d = arg6;
        this.e = arg7;
    }

    private v a() {
        v v0 = this.g;
        if(v0 != null) {
        }
        else {
            v0 = this.a.a(this.e, this.d);
            this.g = v0;
        }

        return v0;
    }

    public Object read(com.google.a.d.a arg4) {
        if(this.c == null) {
            return this.a().read(arg4);
        }

        com.google.a.l v4 = com.google.a.b.j.a(arg4);
        if(v4.j()) {
            return null;
        }

        return this.c.a(v4, this.d.b(), this.f);
    }

    public void write(c arg4, Object arg5) {
        if(this.b == null) {
            this.a().write(arg4, arg5);
            return;
        }

        if(arg5 == null) {
            arg4.f();
            return;
        }

        com.google.a.b.j.a(this.b.a(arg5, this.d.b(), this.f), arg4);
    }
}

