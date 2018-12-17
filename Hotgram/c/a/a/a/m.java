package c.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import c.a.a.a.a.b.g;
import c.a.a.a.a.b.l;
import c.a.a.a.a.e.b;
import c.a.a.a.a.e.e;
import c.a.a.a.a.g.d;
import c.a.a.a.a.g.h;
import c.a.a.a.a.g.n;
import c.a.a.a.a.g.q;
import c.a.a.a.a.g.t;
import c.a.a.a.a.g.y;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;

class m extends i {
    private final e a;
    private PackageManager b;
    private String c;
    private PackageInfo d;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private final Future p;
    private final Collection q;

    public m(Future arg2, Collection arg3) {
        super();
        this.a = new b();
        this.p = arg2;
        this.q = arg3;
    }

    private d a(n arg16, Collection arg17) {
        Context v1 = this.q();
        return new d(new g().a(v1), this.p().c(), this.l, this.k, c.a.a.a.a.b.i.a(new String[]{c.a.a.a.a.b.i.m(v1)}), this.n, l.a(this.m).a(), this.o, "0", arg16, arg17);
    }

    private boolean a(c.a.a.a.a.g.e arg3, n arg4, Collection arg5) {
        return new y(((i)this), this.f(), arg3.c, this.a).a(this.a(arg4, arg5));
    }

    private boolean a(String arg4, c.a.a.a.a.g.e arg5, Collection arg6) {
        boolean v4;
        if("new".equals(arg5.b)) {
            if(!this.b(arg4, arg5, arg6)) {
                c.h().e("Fabric", "Failed to create app with Crashlytics service.", null);
                v4 = false;
            }
            else {
                goto label_6;
            }
        }
        else if("configured".equals(arg5.b)) {
        label_6:
            v4 = q.a().d();
        }
        else {
            if(arg5.f) {
                c.h().a("Fabric", "Server says an update is required - forcing a full App update.");
                this.c(arg4, arg5, arg6);
            }

            v4 = true;
        }

        return v4;
    }

    public String a() {
        return "1.4.1.19";
    }

    Map a(Map arg6, Collection arg7) {
        Iterator v7 = arg7.iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            if(arg6.containsKey(((i)v0).b())) {
                continue;
            }

            arg6.put(((i)v0).b(), new k(((i)v0).b(), ((i)v0).a(), "binary"));
        }

        return arg6;
    }

    private boolean b(String arg3, c.a.a.a.a.g.e arg4, Collection arg5) {
        return new h(((i)this), this.f(), arg4.c, this.a).a(this.a(n.a(this.q(), arg3), arg5));
    }

    public String b() {
        return "io.fabric.sdk.android:fabric";
    }

    protected boolean b_() {
        boolean v0 = false;
        try {
            this.m = this.p().i();
            this.b = this.q().getPackageManager();
            this.c = this.q().getPackageName();
            this.d = this.b.getPackageInfo(this.c, 0);
            this.k = Integer.toString(this.d.versionCode);
            String v1_1 = this.d.versionName == null ? "0.0" : this.d.versionName;
            this.l = v1_1;
            this.n = this.b.getApplicationLabel(this.q().getApplicationInfo()).toString();
            this.o = Integer.toString(this.q().getApplicationInfo().targetSdkVersion);
            v0 = true;
            return 1;
        }
        catch(PackageManager$NameNotFoundException v1) {
            c.h().e("Fabric", "Failed init", ((Throwable)v1));
            return v0;
        }
    }

    private boolean c(String arg2, c.a.a.a.a.g.e arg3, Collection arg4) {
        return this.a(arg3, n.a(this.q(), arg2), arg4);
    }

    protected Boolean c() {
        boolean v0_2;
        Object v2;
        String v0 = c.a.a.a.a.b.i.k(this.q());
        t v1 = this.g();
        if(v1 != null) {
            try {
                if(this.p != null) {
                    v2 = this.p.get();
                }
                else {
                    HashMap v2_1 = new HashMap();
                }

                v0_2 = this.a(v0, v1.a, this.a(((Map)v2), this.q).values());
                goto label_23;
            }
            catch(Exception v0_1) {
                c.h().e("Fabric", "Error performing auto configuration.", ((Throwable)v0_1));
            }
        }

        v0_2 = false;
    label_23:
        return Boolean.valueOf(v0_2);
    }

    protected Object e() {
        return this.c();
    }

    String f() {
        return c.a.a.a.a.b.i.b(this.q(), "com.crashlytics.ApiEndpoint");
    }

    private t g() {
        try {
            q.a().a(this, this.i, this.a, this.k, this.l, this.f()).c();
            return q.a().b();
        }
        catch(Exception v0) {
            c.h().e("Fabric", "Error dealing with settings", ((Throwable)v0));
            return null;
        }
    }
}

