package com.crashlytics.android.c;

import android.content.Context;
import c.a.a.a.a.b.i;
import c.a.a.a.c;
import java.io.File;
import java.util.Set;

class u {
    class com.crashlytics.android.c.u$1 {
    }

    public interface a {
        File a();
    }

    final class b implements s {
        b(com.crashlytics.android.c.u$1 arg1) {
            this();
        }

        private b() {
            super();
        }

        public com.crashlytics.android.c.b a() {
            return null;
        }

        public void b() {
        }

        public void c() {
        }
    }

    private static final b a;
    private final Context b;
    private final a c;
    private s d;

    static {
        u.a = new b(null);
    }

    u(Context arg2, a arg3) {
        this(arg2, arg3, null);
    }

    u(Context arg1, a arg2, String arg3) {
        super();
        this.b = arg1;
        this.c = arg2;
        this.d = u.a;
        this.a(arg3);
    }

    com.crashlytics.android.c.b a() {
        return this.d.a();
    }

    void a(Set arg6) {
        File[] v0 = this.c.a().listFiles();
        if(v0 != null) {
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                File v3 = v0[v2];
                if(!arg6.contains(this.a(v3))) {
                    v3.delete();
                }
            }
        }
    }

    final void a(String arg4) {
        this.d.b();
        this.d = u.a;
        if(arg4 == null) {
            return;
        }

        if(!i.a(this.b, "com.crashlytics.CollectCustomLogs", true)) {
            c.h().a("CrashlyticsCore", "Preferences requested no custom logs. Aborting log file creation.");
            return;
        }

        this.a(this.b(arg4), 65536);
    }

    private String a(File arg3) {
        String v3 = arg3.getName();
        int v0 = v3.lastIndexOf(".temp");
        if(v0 == -1) {
            return v3;
        }

        return v3.substring("crashlytics-userlog-".length(), v0);
    }

    void a(File arg2, int arg3) {
        this.d = new ac(arg2, arg3);
    }

    void b() {
        this.d.c();
    }

    private File b(String arg3) {
        StringBuilder v0 = new StringBuilder();
        v0.append("crashlytics-userlog-");
        v0.append(arg3);
        v0.append(".temp");
        return new File(this.c.a(), v0.toString());
    }
}

