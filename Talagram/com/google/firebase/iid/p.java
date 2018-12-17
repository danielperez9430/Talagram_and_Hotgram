package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.b;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public final class p {
    private final Context a;
    @GuardedBy(value="this") private String b;
    @GuardedBy(value="this") private String c;
    @GuardedBy(value="this") private int d;
    @GuardedBy(value="this") private int e;

    public p(Context arg2) {
        super();
        this.e = 0;
        this.a = arg2;
    }

    public static String a(b arg3) {
        String v0 = arg3.c().b();
        if(v0 != null) {
            return v0;
        }

        String v3 = arg3.c().a();
        if(!v3.startsWith("1:")) {
            return v3;
        }

        String[] v3_1 = v3.split(":");
        String v2 = null;
        if(v3_1.length < 2) {
            return v2;
        }

        v3 = v3_1[1];
        if(v3.isEmpty()) {
            return v2;
        }

        return v3;
    }

    public static String a(KeyPair arg3) {
        byte[] v3 = arg3.getPublic().getEncoded();
        try {
            v3 = MessageDigest.getInstance("SHA1").digest(v3);
            v3[0] = ((byte)((v3[0] & 15) + 112));
            return Base64.encodeToString(v3, 0, 8, 11);
        }
        catch(NoSuchAlgorithmException ) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private final PackageInfo a(String arg4) {
        try {
            return this.a.getPackageManager().getPackageInfo(arg4, 0);
        }
        catch(PackageManager$NameNotFoundException v4) {
            arg4 = String.valueOf(v4);
            StringBuilder v2 = new StringBuilder(String.valueOf(arg4).length() + 23);
            v2.append("Failed to find package ");
            v2.append(arg4);
            Log.w("FirebaseInstanceId", v2.toString());
            return null;
        }
    }

    public final int a() {
        int v1_2;
        Intent v1;
        PackageManager v0_2;
        int v0_1;
        __monitor_enter(this);
        try {
            if(this.e == 0) {
                goto label_6;
            }

            v0_1 = this.e;
        }
        catch(Throwable v0) {
            goto label_61;
        }

        __monitor_exit(this);
        return v0_1;
        try {
        label_6:
            v0_2 = this.a.getPackageManager();
            if(v0_2.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") != -1) {
                goto label_19;
            }

            Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
        }
        catch(Throwable v0) {
            goto label_61;
        }

        __monitor_exit(this);
        return 0;
        try {
        label_19:
            if(!PlatformVersion.isAtLeastO()) {
                v1 = new Intent("com.google.android.c2dm.intent.REGISTER");
                v1.setPackage("com.google.android.gms");
                List v1_1 = v0_2.queryIntentServices(v1, 0);
                if(v1_1 != null && v1_1.size() > 0) {
                    this.e = 1;
                    v0_1 = this.e;
                    goto label_33;
                }
            }

            goto label_35;
        }
        catch(Throwable v0) {
            goto label_61;
        }

    label_33:
        __monitor_exit(this);
        return v0_1;
        try {
        label_35:
            v1 = new Intent("com.google.iid.TOKEN_REQUEST");
            v1.setPackage("com.google.android.gms");
            List v0_3 = v0_2.queryBroadcastReceivers(v1, 0);
            v1_2 = 2;
            if(v0_3 != null && v0_3.size() > 0) {
                this.e = v1_2;
                v0_1 = this.e;
                goto label_47;
            }

            goto label_49;
        }
        catch(Throwable v0) {
            goto label_61;
        }

    label_47:
        __monitor_exit(this);
        return v0_1;
        try {
        label_49:
            Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
            this.e = PlatformVersion.isAtLeastO() ? v1_2 : 1;
            v0_1 = this.e;
        }
        catch(Throwable v0) {
        label_61:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final String b() {
        String v0_1;
        __monitor_enter(this);
        try {
            if(this.b == null) {
                this.e();
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

    public final String c() {
        String v0_1;
        __monitor_enter(this);
        try {
            if(this.c == null) {
                this.e();
            }

            v0_1 = this.c;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final int d() {
        int v0_2;
        __monitor_enter(this);
        try {
            if(this.d == 0) {
                PackageInfo v0_1 = this.a("com.google.android.gms");
                if(v0_1 != null) {
                    this.d = v0_1.versionCode;
                }
            }

            v0_2 = this.d;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_2;
    }

    private final void e() {
        __monitor_enter(this);
        try {
            PackageInfo v0_1 = this.a(this.a.getPackageName());
            if(v0_1 != null) {
                this.b = Integer.toString(v0_1.versionCode);
                this.c = v0_1.versionName;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

