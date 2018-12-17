package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.support.v4.f.a;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class y {
    private final SharedPreferences a;
    private final Context b;
    private final ay c;
    private final Map d;

    public y(Context arg2) {
        this(arg2, new ay());
    }

    private y(Context arg3, ay arg4) {
        super();
        this.d = new a();
        this.b = arg3;
        this.a = arg3.getSharedPreferences("com.google.android.gms.appid", 0);
        this.c = arg4;
        File v0 = new File(android.support.v4.content.a.b(this.b), "com.google.android.gms.appid-no-backup");
        if(!v0.exists()) {
            try {
                if((v0.createNewFile()) && !this.c()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    this.b();
                    FirebaseInstanceId.a().h();
                }
            }
            catch(IOException v3) {
                if(Log.isLoggable("FirebaseInstanceId", 3)) {
                    String v4 = "FirebaseInstanceId";
                    String v0_1 = "Error creating file in no backup dir: ";
                    String v3_1 = String.valueOf(v3.getMessage());
                    v3_1 = v3_1.length() != 0 ? v0_1.concat(v3_1) : new String(v0_1);
                    Log.d(v4, v3_1);
                }
                else {
                }

                return;
            }

            return;
        }
    }

    public final z a(String arg2, String arg3, String arg4) {
        z v2_1;
        __monitor_enter(this);
        try {
            v2_1 = z.a(this.a.getString(y.b(arg2, arg3, arg4), null));
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }

    public final void a(String arg3, String arg4, String arg5, String arg6, String arg7) {
        __monitor_enter(this);
        try {
            arg6 = z.a(arg6, arg7, System.currentTimeMillis());
            if(arg6 != null) {
                goto label_6;
            }
        }
        catch(Throwable v3) {
            goto label_14;
        }

        __monitor_exit(this);
        return;
        try {
        label_6:
            SharedPreferences$Editor v7 = this.a.edit();
            v7.putString(y.b(arg3, arg4, arg5), arg6);
            v7.commit();
        }
        catch(Throwable v3) {
        label_14:
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public final String a() {
        String v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.a.getString("topic_operaion_queue", "");
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final void a(String arg3) {
        __monitor_enter(this);
        try {
            this.a.edit().putString("topic_operaion_queue", arg3).apply();
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    static String a(String arg2, String arg3) {
        StringBuilder v1 = new StringBuilder(String.valueOf(arg2).length() + 3 + String.valueOf(arg3).length());
        v1.append(arg2);
        v1.append("|S|");
        v1.append(arg3);
        return v1.toString();
    }

    public final az b(String arg3) {
        az v0_1;
        Object v0;
        __monitor_enter(this);
        try {
            v0 = this.d.get(arg3);
            if(v0 == null) {
                goto label_6;
            }
        }
        catch(Throwable v3) {
            goto label_23;
        }

        __monitor_exit(this);
        return ((az)v0);
        try {
        label_6:
            v0_1 = this.c.a(this.b, arg3);
        }
        catch(ba ) {
            Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
            FirebaseInstanceId.a().h();
            v0_1 = this.c.b(this.b, arg3);
        }

        this.d.put(arg3, v0_1);
        goto label_20;
    label_23:
        __monitor_exit(this);
        throw v3;
    label_20:
        __monitor_exit(this);
        return v0_1;
    }

    public final void b() {
        __monitor_enter(this);
        try {
            this.d.clear();
            ay.a(this.b);
            this.a.edit().clear().commit();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private static String b(String arg2, String arg3, String arg4) {
        StringBuilder v1 = new StringBuilder(String.valueOf(arg2).length() + 4 + String.valueOf(arg3).length() + String.valueOf(arg4).length());
        v1.append(arg2);
        v1.append("|T|");
        v1.append(arg3);
        v1.append("|");
        v1.append(arg4);
        return v1.toString();
    }

    public final void c(String arg5) {
        __monitor_enter(this);
        try {
            arg5 = String.valueOf(arg5).concat("|T|");
            SharedPreferences$Editor v0 = this.a.edit();
            Iterator v1 = this.a.getAll().keySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                if(!((String)v2).startsWith(arg5)) {
                    continue;
                }

                v0.remove(((String)v2));
            }

            v0.commit();
        }
        catch(Throwable v5) {
            goto label_21;
        }

        __monitor_exit(this);
        return;
    label_21:
        __monitor_exit(this);
        throw v5;
    }

    private final boolean c() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.a.getAll().isEmpty();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }
}

