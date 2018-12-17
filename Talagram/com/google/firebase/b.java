package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector$BackgroundStateChangeListener;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.firebase.components.k;
import com.google.firebase.components.l;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

public class b {
    final class com.google.firebase.b$1 implements BackgroundStateChangeListener {
        com.google.firebase.b$1() {
            super();
        }

        public final void onBackgroundStateChanged(boolean arg1) {
            b.a(arg1);
        }
    }

    @KeepForSdk public interface a {
        @KeepForSdk void a(boolean arg1);
    }

    @KeepForSdk public interface com.google.firebase.b$b {
    }

    final class c implements Executor {
        private static final Handler a;

        static {
            c.a = new Handler(Looper.getMainLooper());
        }

        c(byte arg1) {
            this();
        }

        private c() {
            super();
        }

        public final void execute(Runnable arg2) {
            c.a.post(arg2);
        }
    }

    @TargetApi(value=24) final class d extends BroadcastReceiver {
        private static AtomicReference a;
        private final Context b;

        static {
            d.a = new AtomicReference();
        }

        private d(Context arg1) {
            super();
            this.b = arg1;
        }

        static void a(Context arg3) {
            if(d.a.get() == null) {
                d v0 = new d(arg3);
                if(d.a.compareAndSet(null, v0)) {
                    arg3.registerReceiver(((BroadcastReceiver)v0), new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public final void onReceive(Context arg2, Intent arg3) {
            Object v2 = b.g();
            __monitor_enter(v2);
            try {
                Iterator v3_1 = b.a.values().iterator();
                while(v3_1.hasNext()) {
                    b.a(v3_1.next());
                }

                __monitor_exit(v2);
            }
            catch(Throwable v3) {
                goto label_15;
            }

            this.b.unregisterReceiver(((BroadcastReceiver)this));
            return;
            try {
            label_15:
                __monitor_exit(v2);
            }
            catch(Throwable v3) {
                goto label_15;
            }

            throw v3;
        }
    }

    @GuardedBy(value="LOCK") static final Map a;
    private static final List b;
    private static final List c;
    private static final List d;
    private static final List e;
    private static final Set f;
    private static final Object g;
    private static final Executor h;
    private final Context i;
    private final String j;
    private final com.google.firebase.d k;
    private final l l;
    private final SharedPreferences m;
    private final com.google.firebase.b.c n;
    private final AtomicBoolean o;
    private final AtomicBoolean p;
    private final AtomicBoolean q;
    private final List r;
    private final List s;
    private final List t;
    private com.google.firebase.b$b u;

    static {
        b.b = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
        b.c = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
        b.d = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
        b.e = Arrays.asList(new String[0]);
        b.f = Collections.emptySet();
        b.g = new Object();
        b.h = new c(0);
        b.a = new android.support.v4.f.a();
    }

    protected b(Context arg7, String arg8, com.google.firebase.d arg9) {
        super();
        this.o = new AtomicBoolean(false);
        this.p = new AtomicBoolean();
        this.r = new CopyOnWriteArrayList();
        this.s = new CopyOnWriteArrayList();
        this.t = new CopyOnWriteArrayList();
        this.i = Preconditions.checkNotNull(arg7);
        this.j = Preconditions.checkNotEmpty(arg8);
        this.k = Preconditions.checkNotNull(arg9);
        this.u = new com.google.firebase.d.a();
        this.m = arg7.getSharedPreferences("com.google.firebase.common.prefs", 0);
        this.q = new AtomicBoolean(this.h());
        this.l = new l(b.h, k.a(arg7).a(), new com.google.firebase.components.a[]{com.google.firebase.components.a.a(arg7, Context.class, new Class[0]), com.google.firebase.components.a.a(this, b.class, new Class[0]), com.google.firebase.components.a.a(arg9, com.google.firebase.d.class, new Class[0])});
        this.n = this.l.a(com.google.firebase.b.c.class);
    }

    public static b a(Context arg3) {
        Object v0 = b.g;
        __monitor_enter(v0);
        try {
            if(b.a.containsKey("[DEFAULT]")) {
                __monitor_exit(v0);
                return b.d();
            }

            com.google.firebase.d v1 = com.google.firebase.d.a(arg3);
            if(v1 == null) {
                __monitor_exit(v0);
                return null;
            }

            __monitor_exit(v0);
            return b.a(arg3, v1);
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_18;
        }

        throw v3;
    }

    public static b a(Context arg1, com.google.firebase.d arg2) {
        return b.a(arg1, arg2, "[DEFAULT]");
    }

    public static b a(Context arg4, com.google.firebase.d arg5, String arg6) {
        b v1_1;
        if((PlatformVersion.isAtLeastIceCreamSandwich()) && ((arg4.getApplicationContext() instanceof Application))) {
            BackgroundDetector.initialize(arg4.getApplicationContext());
            BackgroundDetector.getInstance().addListener(new com.google.firebase.b$1());
        }

        arg6 = arg6.trim();
        if(arg4.getApplicationContext() == null) {
        }
        else {
            arg4 = arg4.getApplicationContext();
        }

        Object v0 = b.g;
        __monitor_enter(v0);
        try {
            int v1 = b.a.containsKey(arg6) ^ 1;
            StringBuilder v2 = new StringBuilder("FirebaseApp name ");
            v2.append(arg6);
            v2.append(" already exists!");
            Preconditions.checkState(((boolean)v1), v2.toString());
            Preconditions.checkNotNull(arg4, "Application context cannot be null.");
            v1_1 = new b(arg4, arg6, arg5);
            b.a.put(arg6, v1_1);
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            try {
            label_39:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_39;
            }

            throw v4;
        }

        v1_1.j();
        return v1_1;
    }

    static void a(b arg0) {
        arg0.j();
    }

    private static void a(Class arg6, Object arg7, Iterable arg8, boolean arg9) {
        Object v0;
        Iterator v8 = arg8.iterator();
    label_1:
        while(v8.hasNext()) {
            v0 = v8.next();
            if(arg9) {
                try {
                    if(!b.e.contains(v0)) {
                        continue;
                    }

                label_13:
                    Method v1_1 = Class.forName(((String)v0)).getMethod("getInstance", arg6);
                    int v2 = v1_1.getModifiers();
                    if(!Modifier.isPublic(v2)) {
                        continue;
                    }

                    if(!Modifier.isStatic(v2)) {
                        continue;
                    }

                    v1_1.invoke(null, arg7);
                    continue;
                }
                catch(ClassNotFoundException ) {
                    goto label_51;
                }
                catch(IllegalAccessException v1) {
                    goto label_30;
                }
                catch(NoSuchMethodException ) {
                    goto label_42;
                }
                catch(InvocationTargetException v0_1) {
                    goto label_38;
                }
            }

            goto label_13;
        }

        return;
    label_30:
        StringBuilder v3 = new StringBuilder("Failed to initialize ");
        v3.append(((String)v0));
        Log.wtf("FirebaseApp", v3.toString(), ((Throwable)v1));
        goto label_1;
    label_38:
        Log.wtf("FirebaseApp", "Firebase API initialization failure.", ((Throwable)v0_1));
        goto label_1;
    label_42:
        StringBuilder v7 = new StringBuilder();
        v7.append(((String)v0));
        v7.append("#getInstance has been removed by Proguard. Add keep rule to prevent it.");
        throw new IllegalStateException(v7.toString());
    label_51:
        if(!b.f.contains(v0)) {
            Log.d("FirebaseApp", (((String)v0)) + " is not linked. Skipping initialization.");
            goto label_1;
        }

        v7 = new StringBuilder();
        v7.append(((String)v0));
        v7.append(" is missing, but is required. Check if it has been removed by Proguard.");
        throw new IllegalStateException(v7.toString());
    }

    @KeepForSdk public static void a(boolean arg4) {
        Object v0 = b.g;
        __monitor_enter(v0);
        try {
            Iterator v1 = new ArrayList(b.a.values()).iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                if(!((b)v2).o.get()) {
                    continue;
                }

                ((b)v2).b(arg4);
            }

            __monitor_exit(v0);
            return;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_18;
        }

        throw v4;
    }

    public Context a() {
        this.i();
        return this.i;
    }

    @KeepForSdk public Object a(Class arg2) {
        this.i();
        return this.l.a(arg2);
    }

    private void b(boolean arg3) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        Iterator v0 = this.s.iterator();
        while(v0.hasNext()) {
            v0.next().a(arg3);
        }
    }

    public String b() {
        this.i();
        return this.j;
    }

    public com.google.firebase.d c() {
        this.i();
        return this.k;
    }

    public static b d() {
        Object v0 = b.g;
        __monitor_enter(v0);
        try {
            Object v1_1 = b.a.get("[DEFAULT]");
            if(v1_1 != null) {
                __monitor_exit(v0);
                return ((b)v1_1);
            }

            StringBuilder v2 = new StringBuilder("Default FirebaseApp is not initialized in this process ");
            v2.append(ProcessUtils.getMyProcessName());
            v2.append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
            throw new IllegalStateException(v2.toString());
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_20;
        }

        throw v1;
    }

    @KeepForSdk public boolean e() {
        this.i();
        return this.q.get();
    }

    public boolean equals(Object arg2) {
        if(!(arg2 instanceof b)) {
            return 0;
        }

        return this.j.equals(((b)arg2).b());
    }

    @KeepForSdk public boolean f() {
        return "[DEFAULT]".equals(this.b());
    }

    static Object g() {
        return b.g;
    }

    private boolean h() {
        if(this.m.contains("firebase_data_collection_default_enabled")) {
            return this.m.getBoolean("firebase_data_collection_default_enabled", true);
        }

        try {
            PackageManager v0 = this.i.getPackageManager();
            if(v0 != null) {
                ApplicationInfo v0_1 = v0.getApplicationInfo(this.i.getPackageName(), 128);
                if(v0_1 != null && v0_1.metaData != null && (v0_1.metaData.containsKey("firebase_data_collection_default_enabled"))) {
                    return v0_1.metaData.getBoolean("firebase_data_collection_default_enabled");
                }
            }

            return 1;
        }
        catch(PackageManager$NameNotFoundException ) {
            return 1;
        }
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    private void i() {
        Preconditions.checkState(this.p.get() ^ 1, "FirebaseApp was deleted");
    }

    private void j() {
        boolean v0 = android.support.v4.content.a.c(this.i);
        if(v0) {
            d.a(this.i);
        }
        else {
            this.l.a(this.f());
        }

        b.a(b.class, this, b.b, v0);
        if(this.f()) {
            b.a(b.class, this, b.c, v0);
            b.a(Context.class, this.i, b.d, v0);
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.j).add("options", this.k).toString();
    }
}

