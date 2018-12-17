package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build$VERSION;
import android.os.Looper;
import android.support.annotation.Keep;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.b.b;
import com.google.firebase.b.d;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseInstanceId {
    final class a {
        private final boolean b;
        private final d c;
        @GuardedBy(value="this") private b d;
        @GuardedBy(value="this") private Boolean e;

        a(FirebaseInstanceId arg2, d arg3) {
            this.a = arg2;
            super();
            this.c = arg3;
            this.b = this.c();
            this.e = this.b();
            if(this.e == null && (this.b)) {
                this.d = new aq(this);
                arg3.a(com.google.firebase.a.class, this.d);
            }
        }

        final boolean a() {
            boolean v0_1;
            __monitor_enter(this);
            try {
                if(this.e == null) {
                    goto label_7;
                }

                v0_1 = this.e.booleanValue();
            }
            catch(Throwable v0) {
                goto label_19;
            }

            __monitor_exit(this);
            return v0_1;
            try {
            label_7:
                if(!this.b) {
                    goto label_16;
                }
                else if(!FirebaseInstanceId.a(this.a).e()) {
                    goto label_16;
                }
            }
            catch(Throwable v0) {
            label_19:
                __monitor_exit(this);
                throw v0;
            }

            v0_1 = true;
            goto label_14;
        label_16:
            v0_1 = false;
        label_14:
            __monitor_exit(this);
            return v0_1;
        }

        private final Boolean b() {
            Context v0 = FirebaseInstanceId.a(this.a).a();
            SharedPreferences v1 = v0.getSharedPreferences("com.google.firebase.messaging", 0);
            if(v1.contains("auto_init")) {
                return Boolean.valueOf(v1.getBoolean("auto_init", false));
            }

            try {
                PackageManager v1_1 = v0.getPackageManager();
                if(v1_1 != null) {
                    ApplicationInfo v0_1 = v1_1.getApplicationInfo(v0.getPackageName(), 128);
                    if(v0_1 != null && v0_1.metaData != null && (v0_1.metaData.containsKey("firebase_messaging_auto_init_enabled"))) {
                        return Boolean.valueOf(v0_1.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
                    }
                }

                return null;
            }
            catch(PackageManager$NameNotFoundException ) {
                return null;
            }
        }

        private final boolean c() {
            try {
                Class.forName("com.google.firebase.messaging.a");
                return 1;
            }
            catch(ClassNotFoundException ) {
                Context v1 = FirebaseInstanceId.a(this.a).a();
                Intent v2 = new Intent("com.google.firebase.MESSAGING_EVENT");
                v2.setPackage(v1.getPackageName());
                ResolveInfo v1_1 = v1.getPackageManager().resolveService(v2, 0);
                if(v1_1 != null && v1_1.serviceInfo != null) {
                    return 1;
                }

                return 0;
            }
        }
    }

    private static final long a;
    private static y b;
    @VisibleForTesting @GuardedBy(value="FirebaseInstanceId.class") private static ScheduledThreadPoolExecutor c;
    private final Executor d;
    private final com.google.firebase.b e;
    private final p f;
    private com.google.firebase.iid.b g;
    private final s h;
    private final ac i;
    @GuardedBy(value="this") private boolean j;
    private final a k;

    static {
        FirebaseInstanceId.a = TimeUnit.HOURS.toSeconds(8);
    }

    FirebaseInstanceId(com.google.firebase.b arg7, d arg8) {
        this(arg7, new p(arg7.a()), ak.b(), ak.b(), arg8);
    }

    private FirebaseInstanceId(com.google.firebase.b arg4, p arg5, Executor arg6, Executor arg7, d arg8) {
        super();
        this.h = new s();
        this.j = false;
        if(p.a(arg4) != null) {
            Class v0 = FirebaseInstanceId.class;
            __monitor_enter(v0);
            try {
                if(FirebaseInstanceId.b == null) {
                    FirebaseInstanceId.b = new y(arg4.a());
                }

                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                try {
                label_46:
                    __monitor_exit(v0);
                }
                catch(Throwable v4) {
                    goto label_46;
                }

                throw v4;
            }

            this.e = arg4;
            this.f = arg5;
            if(this.g == null) {
                Object v0_1 = arg4.a(com.google.firebase.iid.b.class);
                if(v0_1 == null || !((com.google.firebase.iid.b)v0_1).b()) {
                    ar v0_2 = new ar(arg4, arg5, arg6);
                }
                else {
                }

                this.g = ((com.google.firebase.iid.b)v0_1);
            }

            this.g = this.g;
            this.d = arg7;
            this.i = new ac(FirebaseInstanceId.b);
            this.k = new a(this, arg8);
            if(this.k.a()) {
                this.m();
            }

            return;
        }

        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    public static FirebaseInstanceId a() {
        return FirebaseInstanceId.getInstance(com.google.firebase.b.d());
    }

    static com.google.firebase.b a(FirebaseInstanceId arg0) {
        return arg0.e;
    }

    private final Object a(Task arg4) {
        long v0 = 30000;
        try {
            return Tasks.await(arg4, v0, TimeUnit.MILLISECONDS);
        }
        catch(TimeoutException ) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        catch(ExecutionException v4) {
            Throwable v0_1 = v4.getCause();
            if((v0_1 instanceof IOException)) {
                if("INSTANCE_ID_RESET".equals(v0_1.getMessage())) {
                    this.h();
                }

                throw v0_1;
            }

            if((v0_1 instanceof RuntimeException)) {
                throw v0_1;
            }

            throw new IOException(((Throwable)v4));
        }
    }

    static void a(Runnable arg3, long arg4) {
        Class v0 = FirebaseInstanceId.class;
        __monitor_enter(v0);
        try {
            if(FirebaseInstanceId.c == null) {
                FirebaseInstanceId.c = new ScheduledThreadPoolExecutor(1);
            }

            FirebaseInstanceId.c.schedule(arg3, arg4, TimeUnit.SECONDS);
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    final void a(long arg12) {
        __monitor_enter(this);
        long v0 = 30;
        long v3 = arg12 << 1;
        try {
            FirebaseInstanceId.a(new aa(this, this.f, this.i, Math.min(Math.max(v0, v3), FirebaseInstanceId.a)), arg12);
            this.j = true;
        }
        catch(Throwable v12) {
            __monitor_exit(this);
            throw v12;
        }

        __monitor_exit(this);
    }

    public final Task a(String arg2) {
        Task v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.i.a(arg2);
            this.n();
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }

    final Task a(String arg2, String arg3, String arg4, String arg5) {
        return this.g.a(arg2, arg3, arg4, arg5);
    }

    public String a(String arg3, String arg4) {
        if(Looper.getMainLooper() != Looper.myLooper()) {
            return this.a(this.b(arg3, arg4)).a();
        }

        throw new IOException("MAIN_THREAD");
    }

    final void a(String arg10, String arg11, TaskCompletionSource arg12, String arg13) {
        String v6 = FirebaseInstanceId.o();
        z v11 = FirebaseInstanceId.c(arg10, arg11);
        if(v11 != null && !v11.b(this.f.b())) {
            arg12.setResult(new ax(v6, v11.a));
            return;
        }

        this.h.a(arg10, arg13, new ao(this, v6, z.a(v11), arg10, arg13)).addOnCompleteListener(this.d, new ap(this, arg10, arg13, arg12, v6));
    }

    final void a(String arg7, String arg8, TaskCompletionSource arg9, String arg10, Task arg11) {
        if(arg11.isSuccessful()) {
            Object v11 = arg11.getResult();
            FirebaseInstanceId.b.a("", arg7, arg8, v11, this.f.b());
            arg9.setResult(new ax(arg10, ((String)v11)));
            return;
        }

        arg9.setException(arg11.getException());
    }

    final void a(boolean arg1) {
        __monitor_enter(this);
        try {
            this.j = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    private final Task b(String arg10, String arg11) {
        String v5 = FirebaseInstanceId.d(arg11);
        TaskCompletionSource v6 = new TaskCompletionSource();
        this.d.execute(new an(this, arg10, arg11, v6, v5));
        return v6.getTask();
    }

    static void b(FirebaseInstanceId arg0) {
        arg0.m();
    }

    final com.google.firebase.b b() {
        return this.e;
    }

    final void b(String arg4) {
        z v0 = this.e();
        if(v0 != null && !v0.b(this.f.b())) {
            this.a(this.g.a(FirebaseInstanceId.o(), v0.a, arg4));
            return;
        }

        throw new IOException("token not available");
    }

    public String c() {
        this.m();
        return FirebaseInstanceId.o();
    }

    @VisibleForTesting private static z c(String arg2, String arg3) {
        return FirebaseInstanceId.b.a("", arg2, arg3);
    }

    final void c(String arg4) {
        z v0 = this.e();
        if(v0 != null && !v0.b(this.f.b())) {
            this.a(this.g.b(FirebaseInstanceId.o(), v0.a, arg4));
            return;
        }

        throw new IOException("token not available");
    }

    private static String d(String arg1) {
        if(!arg1.isEmpty() && !arg1.equalsIgnoreCase("fcm")) {
            if(arg1.equalsIgnoreCase("gcm")) {
            }
            else {
                return arg1;
            }
        }

        return "*";
    }

    @Deprecated public String d() {
        z v0 = this.e();
        if(v0 == null || (v0.b(this.f.b()))) {
            this.n();
        }

        if(v0 != null) {
            return v0.a;
        }

        return null;
    }

    final z e() {
        return FirebaseInstanceId.c(p.a(this.e), "*");
    }

    final String f() {
        return this.a(p.a(this.e), "*");
    }

    static boolean g() {
        int v1 = 3;
        if(!Log.isLoggable("FirebaseInstanceId", v1) && (Build$VERSION.SDK_INT != 23 || !Log.isLoggable("FirebaseInstanceId", v1))) {
            return 0;
        }

        return 1;
    }

    @Keep public static FirebaseInstanceId getInstance(com.google.firebase.b arg2) {
        Class v0 = FirebaseInstanceId.class;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return arg2.a(FirebaseInstanceId.class);
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }
    }

    final void h() {
        __monitor_enter(this);
        try {
            FirebaseInstanceId.b.b();
            if(this.k.a()) {
                this.n();
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    final boolean i() {
        return this.g.b();
    }

    final boolean j() {
        return this.g.a();
    }

    final void k() {
        this.a(this.g.a(FirebaseInstanceId.o(), z.a(this.e())));
    }

    final void l() {
        FirebaseInstanceId.b.c("");
        this.n();
    }

    private final void m() {
        z v0 = this.e();
        if(!this.j() || v0 == null || (v0.b(this.f.b())) || (this.i.a())) {
            this.n();
        }
    }

    private final void n() {
        __monitor_enter(this);
        try {
            if(!this.j) {
                this.a(0);
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private static String o() {
        return p.a(FirebaseInstanceId.b.b("").a());
    }
}

