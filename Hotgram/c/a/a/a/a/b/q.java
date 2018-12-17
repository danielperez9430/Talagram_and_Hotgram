package c.a.a.a.a.b;

import android.content.Context;
import c.a.a.a.a.a.b;
import c.a.a.a.a.a.d;
import c.a.a.a.c;

public class q {
    class c.a.a.a.a.b.q$1 implements d {
        c.a.a.a.a.b.q$1(q arg1) {
            this.a = arg1;
            super();
        }

        public String a(Context arg2) {
            String v2 = arg2.getPackageManager().getInstallerPackageName(arg2.getPackageName());
            if(v2 == null) {
                v2 = "";
            }

            return v2;
        }

        public Object b(Context arg1) {
            return this.a(arg1);
        }
    }

    private final d a;
    private final b b;

    public q() {
        super();
        this.a = new c.a.a.a.a.b.q$1(this);
        this.b = new b();
    }

    public String a(Context arg5) {
        String v5_2;
        String v0 = null;
        try {
            Object v5_1 = this.b.a(arg5, this.a);
            if(!"".equals(v5_1)) {
                return v5_2;
            }
        }
        catch(Exception v5) {
            c.h().e("Fabric", "Failed to determine installer package name", ((Throwable)v5));
            return v0;
        }

        v5_2 = v0;
        return v5_2;
    }
}

