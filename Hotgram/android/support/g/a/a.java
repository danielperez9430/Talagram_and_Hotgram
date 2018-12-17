package android.support.g.a;

import android.app.Fragment;
import android.os.Build$VERSION;

@Deprecated public class a {
    class android.support.g.a.a$a extends d {
        android.support.g.a.a$a() {
            super();
        }

        public void a(Fragment arg1, boolean arg2) {
            arg1.setUserVisibleHint(arg2);
        }
    }

    class b extends android.support.g.a.a$a {
        b() {
            super();
        }
    }

    class c extends b {
        c() {
            super();
        }

        public void a(Fragment arg1, boolean arg2) {
            arg1.setUserVisibleHint(arg2);
        }
    }

    class d implements e {
        d() {
            super();
        }

        public void a(Fragment arg1, boolean arg2) {
        }
    }

    interface e {
        void a(Fragment arg1, boolean arg2);
    }

    static final e a;

    static {
        d v0_3;
        if(Build$VERSION.SDK_INT >= 24) {
            c v0 = new c();
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            b v0_1 = new b();
        }
        else if(Build$VERSION.SDK_INT >= 15) {
            android.support.g.a.a$a v0_2 = new android.support.g.a.a$a();
        }
        else {
            v0_3 = new d();
        }

        a.a = ((e)v0_3);
    }

    @Deprecated public static void a(Fragment arg1, boolean arg2) {
        a.a.a(arg1, arg2);
    }
}

