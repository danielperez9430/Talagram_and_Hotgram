package c.b.e.a;

import c.b.c.e;
import c.b.f.a;
import java.util.concurrent.atomic.AtomicReference;

public enum b implements c.b.b.b {
    public static final enum b a;

    static {
        b.a = new b("DISPOSED", 0);
        b.b = new b[]{b.a};
    }

    private b(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static boolean a(AtomicReference arg2) {
        Object v0 = arg2.get();
        b v1 = b.a;
        if(v0 != v1) {
            Object v2 = arg2.getAndSet(v1);
            if((((b)v2)) != v1) {
                if(v2 != null) {
                    ((c.b.b.b)v2).a();
                }

                return 1;
            }
        }

        return 0;
    }

    public static boolean a(AtomicReference arg1, c.b.b.b arg2) {
        c.b.e.b.b.a(arg2, "d is null");
        if(!arg1.compareAndSet(null, arg2)) {
            arg2.a();
            if(arg1.get() != b.a) {
                b.b();
            }

            return 0;
        }

        return 1;
    }

    public void a() {
    }

    public static void b() {
        a.a(new e("Disposable already set!"));
    }

    public static b valueOf(String arg1) {
        return Enum.valueOf(b.class, arg1);
    }

    public static b[] values() {
        // Method was not decompiled
    }
}

