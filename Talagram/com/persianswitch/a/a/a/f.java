package com.persianswitch.a.a.a;

import com.persianswitch.a.a.l;

public final class f {
    public static final com.persianswitch.b.f a;
    public static final com.persianswitch.b.f b;
    public static final com.persianswitch.b.f c;
    public static final com.persianswitch.b.f d;
    public static final com.persianswitch.b.f e;
    public static final com.persianswitch.b.f f;
    public static final com.persianswitch.b.f g;
    public final com.persianswitch.b.f h;
    public final com.persianswitch.b.f i;
    final int j;

    static {
        f.a = com.persianswitch.b.f.a(":status");
        f.b = com.persianswitch.b.f.a(":method");
        f.c = com.persianswitch.b.f.a(":path");
        f.d = com.persianswitch.b.f.a(":scheme");
        f.e = com.persianswitch.b.f.a(":authority");
        f.f = com.persianswitch.b.f.a(":host");
        f.g = com.persianswitch.b.f.a(":version");
    }

    public f(com.persianswitch.b.f arg1, com.persianswitch.b.f arg2) {
        super();
        this.h = arg1;
        this.i = arg2;
        this.j = arg1.e() + 32 + arg2.e();
    }

    public f(com.persianswitch.b.f arg1, String arg2) {
        this(arg1, com.persianswitch.b.f.a(arg2));
    }

    public f(String arg1, String arg2) {
        this(com.persianswitch.b.f.a(arg1), com.persianswitch.b.f.a(arg2));
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(((arg4 instanceof f)) && (this.h.equals(((f)arg4).h)) && (this.i.equals(((f)arg4).i))) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        return (527 + this.h.hashCode()) * 31 + this.i.hashCode();
    }

    public String toString() {
        return l.a("%s: %s", new Object[]{this.h.a(), this.i.a()});
    }
}

