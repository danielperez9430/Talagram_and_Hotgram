package c.a.a.a.a.g;

import android.content.res.Resources$NotFoundException;
import c.a.a.a.a.b.s;
import c.a.a.a.a.e.c;
import c.a.a.a.a.e.d;
import c.a.a.a.a.e.e;
import c.a.a.a.i;
import c.a.a.a.k;
import c.a.a.a.l;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;

abstract class a extends c.a.a.a.a.b.a {
    public a(i arg1, String arg2, String arg3, e arg4, c arg5) {
        super(arg1, arg2, arg3, arg4, arg5);
    }

    private d a(d arg2, c.a.a.a.a.g.d arg3) {
        return arg2.a("X-CRASHLYTICS-API-KEY", arg3.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a());
    }

    String a(k arg5) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", arg5.a());
    }

    public boolean a(c.a.a.a.a.g.d arg6) {
        d v0 = this.b(this.a(this.b(), arg6), arg6);
        l v1 = c.a.a.a.c.h();
        v1.a("Fabric", "Sending app info to " + this.a());
        if(arg6.j != null) {
            v1 = c.a.a.a.c.h();
            v1.a("Fabric", "App icon hash is " + arg6.j.a);
            v1 = c.a.a.a.c.h();
            v1.a("Fabric", "App icon size is " + arg6.j.c + "x" + arg6.j.d);
        }

        int v6 = v0.b();
        String v1_1 = "POST".equals(v0.p()) ? "Create" : "Update";
        l v2 = c.a.a.a.c.h();
        v2.a("Fabric", v1_1 + " app request ID: " + v0.b("X-REQUEST-ID"));
        l v0_1 = c.a.a.a.c.h();
        v0_1.a("Fabric", "Result was " + v6);
        boolean v6_1 = s.a(v6) == 0 ? true : false;
        return v6_1;
    }

    private d b(d arg8, c.a.a.a.a.g.d arg9) {
        Resources$NotFoundException v0_1;
        Closeable v1_2;
        InputStream v1_1;
        arg8 = arg8.e("app[identifier]", arg9.b).e("app[name]", arg9.f).e("app[display_version]", arg9.c).e("app[build_version]", arg9.d).a("app[source]", Integer.valueOf(arg9.g)).e("app[minimum_sdk_version]", arg9.h).e("app[built_sdk_version]", arg9.i);
        if(!c.a.a.a.a.b.i.d(arg9.e)) {
            arg8.e("app[instance_identifier]", arg9.e);
        }

        if(arg9.j == null) {
            goto label_83;
        }

        Closeable v0 = null;
        try {
            v1_1 = this.a.q().getResources().openRawResource(arg9.j.b);
        }
        catch(Throwable v8) {
            v1_2 = v0;
            goto label_80;
        }
        catch(Resources$NotFoundException v1) {
            Resources$NotFoundException v6 = v1;
            v1_2 = v0;
            v0_1 = v6;
            goto label_65;
        }

        try {
            arg8.e("app[icon][hash]", arg9.j.a).a("app[icon][data]", "icon.png", "application/octet-stream", v1_1).a("app[icon][width]", Integer.valueOf(arg9.j.c)).a("app[icon][height]", Integer.valueOf(arg9.j.d));
            goto label_76;
        }
        catch(Throwable v8) {
        }
        catch(Resources$NotFoundException v0_1) {
            try {
            label_65:
                l v2 = c.a.a.a.c.h();
                v2.e("Fabric", "Failed to find app icon with resource ID: " + arg9.j.b, ((Throwable)v0_1));
                goto label_76;
            }
            catch(Throwable v8) {
            }
        }

    label_80:
        c.a.a.a.a.b.i.a(((Closeable)v1_1), "Failed to close app icon InputStream.");
        throw v8;
    label_76:
        c.a.a.a.a.b.i.a(((Closeable)v1_1), "Failed to close app icon InputStream.");
    label_83:
        if(arg9.k != null) {
            Iterator v9 = arg9.k.iterator();
            while(v9.hasNext()) {
                Object v0_2 = v9.next();
                arg8.e(this.a(((k)v0_2)), ((k)v0_2).b());
                arg8.e(this.b(((k)v0_2)), ((k)v0_2).c());
            }
        }

        return arg8;
    }

    String b(k arg5) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", arg5.a());
    }
}

