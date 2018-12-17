package com.crashlytics.android.c;

import c.a.a.a.a.b.h;
import c.a.a.a.l;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class af {
    final class a implements d {
        a() {
            super();
        }

        public boolean a() {
            return 1;
        }
    }

    interface b {
        boolean a();
    }

    interface c {
        File[] a();

        File[] b();
    }

    interface d {
        boolean a();
    }

    class e extends h {
        private final float b;
        private final d c;

        e(af arg1, float arg2, d arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
        }

        public void a() {
            try {
                this.b();
            }
            catch(Exception v0) {
                c.a.a.a.c.h().e("CrashlyticsCore", "An unexpected error occurred while attempting to upload crash reports.", ((Throwable)v0));
            }

            af.a(this.a, null);
        }

        private void b() {
            Iterator v0_2;
            l v0 = c.a.a.a.c.h();
            v0.a("CrashlyticsCore", "Starting report processing in " + this.b + " second(s)...");
            if(this.b > 0f) {
                try {
                    Thread.sleep(((long)(this.b * 1000f)));
                }
                catch(InterruptedException ) {
                    goto label_21;
                }
            }

            List v0_1 = this.a.a();
            if(af.a(this.a).a()) {
                return;
            }

            if(!v0_1.isEmpty() && !this.c.a()) {
                l v1 = c.a.a.a.c.h();
                v1.a("CrashlyticsCore", "User declined to send. Removing " + v0_1.size() + " Report(s).");
                v0_2 = v0_1.iterator();
                while(v0_2.hasNext()) {
                    v0_2.next().f();
                }

                return;
            }

            int v1_1 = 0;
            while(!v0_1.isEmpty()) {
                if(af.a(this.a).a()) {
                    return;
                }

                l v2_1 = c.a.a.a.c.h();
                v2_1.a("CrashlyticsCore", "Attempting to send " + v0_1.size() + " report(s)");
                v0_2 = v0_1.iterator();
                while(v0_2.hasNext()) {
                    this.a.a(v0_2.next());
                }

                v0_1 = this.a.a();
                if(v0_1.isEmpty()) {
                    continue;
                }

                int v3_1 = v1_1 + 1;
                long v1_2 = ((long)af.b()[Math.min(v1_1, af.b().length - 1)]);
                l v4_1 = c.a.a.a.c.h();
                v4_1.a("CrashlyticsCore", "Report submisson: scheduling delayed retry in " + v1_2 + " seconds");
                v1_2 *= 1000;
                try {
                    Thread.sleep(v1_2);
                    v1_1 = v3_1;
                    continue;
                }
                catch(InterruptedException ) {
                    goto label_21;
                }
            }

            return;
        label_21:
            Thread.currentThread().interrupt();
        }
    }

    static final Map a;
    private static final short[] b;
    private final Object c;
    private final o d;
    private final String e;
    private final c f;
    private final b g;
    private Thread h;

    static {
        af.a = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
        af.b = new short[]{10, 20, 30, 60, 120, 300};
    }

    public af(String arg2, o arg3, c arg4, b arg5) {
        super();
        this.c = new Object();
        if(arg3 != null) {
            this.d = arg3;
            this.e = arg2;
            this.f = arg4;
            this.g = arg5;
            return;
        }

        throw new IllegalArgumentException("createReportCall must not be null.");
    }

    static b a(af arg0) {
        return arg0.g;
    }

    static Thread a(af arg0, Thread arg1) {
        arg0.h = arg1;
        return arg1;
    }

    List a() {
        int v4;
        File[] v2;
        File[] v1_1;
        c.a.a.a.c.h().a("CrashlyticsCore", "Checking for crash reports...");
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            v1_1 = this.f.a();
            v2 = this.f.b();
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_80:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_80;
            }

            throw v1;
        }

        LinkedList v0_1 = new LinkedList();
        int v3 = 0;
        if(v1_1 != null) {
            v4 = v1_1.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                File v6 = v1_1[v5];
                l v7 = c.a.a.a.c.h();
                v7.a("CrashlyticsCore", "Found crash report " + v6.getPath());
                ((List)v0_1).add(new ah(v6));
            }
        }

        HashMap v1_2 = new HashMap();
        if(v2 != null) {
            v4 = v2.length;
            while(v3 < v4) {
                File v5_1 = v2[v3];
                String v6_1 = com.crashlytics.android.c.h.a(v5_1);
                if(!((Map)v1_2).containsKey(v6_1)) {
                    ((Map)v1_2).put(v6_1, new LinkedList());
                }

                ((Map)v1_2).get(v6_1).add(v5_1);
                ++v3;
            }
        }

        Iterator v2_1 = ((Map)v1_2).keySet().iterator();
        while(v2_1.hasNext()) {
            Object v3_1 = v2_1.next();
            l v4_1 = c.a.a.a.c.h();
            v4_1.a("CrashlyticsCore", "Found invalid session: " + (((String)v3_1)));
            Object v4_2 = ((Map)v1_2).get(v3_1);
            ((List)v0_1).add(new t(((String)v3_1), ((List)v4_2).toArray(new File[((List)v4_2).size()])));
        }

        if(((List)v0_1).isEmpty()) {
            c.a.a.a.c.h().a("CrashlyticsCore", "No reports found.");
        }

        return ((List)v0_1);
    }

    public void a(float arg2, d arg3) {
        __monitor_enter(this);
        try {
            if(this.h == null) {
                goto label_9;
            }

            c.a.a.a.c.h().a("CrashlyticsCore", "Report upload has already been started.");
        }
        catch(Throwable v2) {
            goto label_20;
        }

        __monitor_exit(this);
        return;
        try {
        label_9:
            this.h = new Thread(new e(this, arg2, arg3), "Crashlytics Report Uploader");
            this.h.start();
        }
        catch(Throwable v2) {
        label_20:
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    boolean a(ae arg8) {
        Object v8_1;
        l v3;
        Object v0 = this.c;
        __monitor_enter(v0);
        boolean v1 = false;
        try {
            boolean v2_1 = this.d.a(new n(this.e, arg8));
            v3 = c.a.a.a.c.h();
            String v4 = "CrashlyticsCore";
            StringBuilder v5 = new StringBuilder();
            v5.append("Crashlytics report upload ");
            String v6 = v2_1 ? "complete: " : "FAILED: ";
            v5.append(v6);
            v5.append(arg8.b());
            v3.c(v4, v5.toString());
            if(!v2_1) {
                goto label_40;
            }

            arg8.f();
            v8_1 = null;
            v1 = true;
            goto label_40;
        }
        catch(Throwable v8) {
        }
        catch(Exception v2) {
            try {
                v3 = c.a.a.a.c.h();
                v3.e("CrashlyticsCore", "Error occurred sending report " + v8_1, ((Throwable)v2));
            label_40:
                __monitor_exit(v0);
                return v1;
            }
            catch(Throwable v8) {
            label_29:
                try {
                    __monitor_exit(v0);
                }
                catch(Throwable v8) {
                    goto label_29;
                }

                throw v8;
            }
        }
    }

    static short[] b() {
        return af.b;
    }
}

