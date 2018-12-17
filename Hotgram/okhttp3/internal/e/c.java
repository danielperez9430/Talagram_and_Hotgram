package okhttp3.internal.e;

import e.f;

public final class c {
    public static final f a;
    public static final f b;
    public static final f c;
    public static final f d;
    public static final f e;
    public static final f f;
    public final f g;
    public final f h;
    final int i;

    static {
        c.a = f.a(":");
        c.b = f.a(":status");
        c.c = f.a(":method");
        c.d = f.a(":path");
        c.e = f.a(":scheme");
        c.f = f.a(":authority");
    }

    public c(f arg1, f arg2) {
        super();
        this.g = arg1;
        this.h = arg2;
        this.i = arg1.g() + 32 + arg2.g();
    }

    public c(f arg1, String arg2) {
        this(arg1, f.a(arg2));
    }

    public c(String arg1, String arg2) {
        this(f.a(arg1), f.a(arg2));
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(((arg4 instanceof c)) && (this.g.equals(((c)arg4).g)) && (this.h.equals(((c)arg4).h))) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        return (527 + this.g.hashCode()) * 31 + this.h.hashCode();
    }

    public String toString() {
        return okhttp3.internal.c.a("%s: %s", new Object[]{this.g.a(), this.h.a()});
    }
}

