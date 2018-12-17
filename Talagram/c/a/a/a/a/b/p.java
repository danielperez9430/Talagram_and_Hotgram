package c.a.a.a.a.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Build$VERSION;
import android.os.Build;
import android.provider.Settings$Secure;
import android.text.TextUtils;
import c.a.a.a.l;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class p {
    public enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        public static final enum a d;
        public static final enum a e;
        public static final enum a f;
        public static final enum a g;
        public final int h;

        static {
            a.a = new a("WIFI_MAC_ADDRESS", 0, 1);
            a.b = new a("BLUETOOTH_MAC_ADDRESS", 1, 2);
            a.c = new a("FONT_TOKEN", 2, 53);
            a.d = new a("ANDROID_ID", 3, 100);
            a.e = new a("ANDROID_DEVICE_ID", 4, 101);
            a.f = new a("ANDROID_SERIAL", 5, 102);
            a.g = new a("ANDROID_ADVERTISING_ID", 6, 103);
            a.i = new a[]{a.a, a.b, a.c, a.d, a.e, a.f, a.g};
        }

        private a(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.h = arg3;
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    c a;
    b b;
    boolean c;
    o d;
    private static final Pattern e;
    private static final String f;
    private final ReentrantLock g;
    private final q h;
    private final boolean i;
    private final boolean j;
    private final Context k;
    private final String l;
    private final String m;
    private final Collection n;

    static {
        p.e = Pattern.compile("[^\\p{Alnum}]");
        p.f = Pattern.quote("/");
    }

    public p(Context arg3, String arg4, String arg5, Collection arg6) {
        l v4;
        super();
        this.g = new ReentrantLock();
        if(arg3 != null) {
            if(arg4 != null) {
                if(arg6 != null) {
                    this.k = arg3;
                    this.l = arg4;
                    this.m = arg5;
                    this.n = arg6;
                    this.h = new q();
                    this.a = new c(arg3);
                    this.d = new o();
                    this.i = i.a(arg3, "com.crashlytics.CollectDeviceIdentifiers", true);
                    if(!this.i) {
                        v4 = c.a.a.a.c.h();
                        v4.a("Fabric", "Device ID collection disabled for " + arg3.getPackageName());
                    }

                    this.j = i.a(arg3, "com.crashlytics.CollectUserIdentifiers", true);
                    if(!this.j) {
                        v4 = c.a.a.a.c.h();
                        v4.a("Fabric", "User information collection disabled for " + arg3.getPackageName());
                    }

                    return;
                }

                throw new IllegalArgumentException("kits must not be null");
            }

            throw new IllegalArgumentException("appIdentifier must not be null");
        }

        throw new IllegalArgumentException("appContext must not be null");
    }

    @SuppressLint(value={"CommitPrefEdits"}) private String a(SharedPreferences arg3) {
        String v0;
        this.g.lock();
        try {
            v0 = arg3.getString("crashlytics.installation.id", null);
            if(v0 == null) {
                v0 = this.a(UUID.randomUUID().toString());
                arg3.edit().putString("crashlytics.installation.id", v0).commit();
            }
        }
        catch(Throwable v3) {
            this.g.unlock();
            throw v3;
        }

        this.g.unlock();
        return v0;
    }

    private String a(String arg2) {
        return arg2 == null ? null : p.e.matcher(((CharSequence)arg2)).replaceAll("").toLowerCase(Locale.US);
    }

    @SuppressLint(value={"CommitPrefEdits"}) private void a(SharedPreferences arg3, String arg4) {
        SharedPreferences$Editor v3_1;
        this.g.lock();
        try {
            if(!TextUtils.isEmpty(((CharSequence)arg4))) {
                goto label_7;
            }
        }
        catch(Throwable v3) {
            goto label_30;
        }

        this.g.unlock();
        return;
        try {
        label_7:
            String v0 = arg3.getString("crashlytics.advertising.id", null);
            if(TextUtils.isEmpty(((CharSequence)v0))) {
                v3_1 = arg3.edit().putString("crashlytics.advertising.id", arg4);
                goto label_15;
            }
            else if(!v0.equals(arg4)) {
                v3_1 = arg3.edit().remove("crashlytics.installation.id").putString("crashlytics.advertising.id", arg4);
            label_15:
                v3_1.commit();
            }
        }
        catch(Throwable v3) {
        label_30:
            this.g.unlock();
            throw v3;
        }

        this.g.unlock();
    }

    private void a(Map arg1, a arg2, String arg3) {
        if(arg3 != null) {
            arg1.put(arg2, arg3);
        }
    }

    public boolean a() {
        return this.j;
    }

    private String b(String arg3) {
        return arg3.replaceAll(p.f, "");
    }

    private void b(SharedPreferences arg2) {
        b v0 = this.n();
        if(v0 != null) {
            this.a(arg2, v0.a);
        }
    }

    public String b() {
        String v0 = this.m;
        if(v0 == null) {
            SharedPreferences v0_1 = i.a(this.k);
            this.b(v0_1);
            String v1 = v0_1.getString("crashlytics.installation.id", null);
            v0 = v1 == null ? this.a(v0_1) : v1;
        }

        return v0;
    }

    public String c() {
        return this.l;
    }

    public String d() {
        return this.e() + "/" + this.f();
    }

    public String e() {
        return this.b(Build$VERSION.RELEASE);
    }

    public String f() {
        return this.b(Build$VERSION.INCREMENTAL);
    }

    public String g() {
        return String.format(Locale.US, "%s/%s", this.b(Build.MANUFACTURER), this.b(Build.MODEL));
    }

    public Map h() {
        Object v2;
        HashMap v0 = new HashMap();
        Iterator v1 = this.n.iterator();
        do {
        label_4:
            if(!v1.hasNext()) {
                goto label_19;
            }

            v2 = v1.next();
        }
        while(!(v2 instanceof m));

        Iterator v2_1 = ((m)v2).f().entrySet().iterator();
        goto label_12;
    label_19:
        String v1_1 = this.k();
        if(TextUtils.isEmpty(((CharSequence)v1_1))) {
            this.a(((Map)v0), a.d, this.l());
        }
        else {
            this.a(((Map)v0), a.g, v1_1);
        }

        return Collections.unmodifiableMap(((Map)v0));
        while(true) {
        label_12:
            if(!v2_1.hasNext()) {
                goto label_4;
            }

            Object v3 = v2_1.next();
            this.a(((Map)v0), ((Map$Entry)v3).getKey(), ((Map$Entry)v3).getValue());
        }
    }

    public String i() {
        return this.h.a(this.k);
    }

    public Boolean j() {
        Boolean v0 = this.m() ? this.o() : null;
        return v0;
    }

    public String k() {
        String v0_1;
        if(this.m()) {
            b v0 = this.n();
            if(v0 == null) {
                goto label_8;
            }
            else if(!v0.b) {
                v0_1 = v0.a;
            }
            else {
                goto label_8;
            }
        }
        else {
        label_8:
            v0_1 = null;
        }

        return v0_1;
    }

    public String l() {
        String v0_1;
        boolean v0 = Boolean.TRUE.equals(this.o());
        if(!this.m() || (v0)) {
        label_15:
            v0_1 = null;
        }
        else {
            v0_1 = Settings$Secure.getString(this.k.getContentResolver(), "android_id");
            if(!"9774d56d682e549c".equals(v0_1)) {
                v0_1 = this.a(v0_1);
            }
            else {
                goto label_15;
            }
        }

        return v0_1;
    }

    protected boolean m() {
        boolean v0 = !this.i || (this.d.b(this.k)) ? false : true;
        return v0;
    }

    b n() {
        b v0_1;
        __monitor_enter(this);
        try {
            if(!this.c) {
                this.b = this.a.a();
                this.c = true;
            }

            v0_1 = this.b;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    private Boolean o() {
        b v0 = this.n();
        if(v0 != null) {
            return Boolean.valueOf(v0.b);
        }

        return null;
    }
}

