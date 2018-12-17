package c.b.e.b;

import c.b.d.c;

public final class b {
    final class a implements c {
        a() {
            super();
        }
    }

    static final c a;

    static {
        b.a = new a();
    }

    public static Object a(Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }
}

