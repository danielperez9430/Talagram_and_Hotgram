package com.google.firebase.a;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.firebase_abt.zzi;
import com.google.android.gms.internal.firebase_abt.zzj;
import com.google.android.gms.internal.firebase_abt.zzo;
import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@KeepForSdk public class a {
    private AppMeasurement a;
    private String b;
    private int c;
    private long d;
    private SharedPreferences e;
    private String f;
    private Integer g;

    @KeepForSdk public a(Context arg3, String arg4, int arg5) {
        super();
        this.a = AppMeasurement.getInstance(arg3);
        this.b = arg4;
        this.c = arg5;
        this.g = null;
        this.e = arg3.getSharedPreferences("com.google.firebase.abt", 0);
        this.f = String.format("%s_lastKnownExperimentStartTime", arg4);
        this.d = this.e.getLong(this.f, 0);
    }

    public void a(List arg14) {
        // Method was not decompiled
    }

    private static zzo a(byte[] arg3) {
        try {
            return zzj.zza(new zzo(), arg3, 0, arg3.length);
        }
        catch(zzi v3) {
            Log.e("FirebaseABTesting", "Payload was not defined or could not be deserialized.", ((Throwable)v3));
            return null;
        }
    }

    private final void a(String arg3) {
        this.a.clearConditionalUserProperty(arg3, null, null);
    }

    private final void a(Collection arg2) {
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            this.a(v2.next().mName);
        }
    }

    private final boolean a(zzo arg7) {
        int v0 = arg7.zzc;
        int v1 = this.c;
        if(v0 != 0) {
        }
        else if(v1 != 0) {
            v0 = v1;
        }
        else {
            v0 = 1;
        }

        if(v0 != 1) {
            if(Log.isLoggable("FirebaseABTesting", 3)) {
                Log.d("FirebaseABTesting", String.format("Experiment won\'t be set due to the overflow policy: [%s, %s]", arg7.zzaq, arg7.zzar));
            }

            return 0;
        }

        return 1;
    }

    @KeepForSdk public void a() {
        this.a(this.c());
    }

    private final ConditionalUserProperty b(zzo arg4) {
        ConditionalUserProperty v0 = new ConditionalUserProperty();
        v0.mOrigin = this.b;
        v0.mCreationTimestamp = arg4.zzas;
        v0.mName = arg4.zzaq;
        v0.mValue = arg4.zzar;
        String v1 = TextUtils.isEmpty(arg4.zzat) ? null : arg4.zzat;
        v0.mTriggerEventName = v1;
        v0.mTriggerTimeout = arg4.zzau;
        v0.mTimeToLive = arg4.zzav;
        return v0;
    }

    private final void b() {
        if(this.e.getLong(this.f, 0) == this.d) {
            return;
        }

        SharedPreferences$Editor v0 = this.e.edit();
        v0.putLong(this.f, this.d);
        v0.apply();
    }

    private final List c() {
        return this.a.getConditionalUserProperties(this.b, "");
    }

    private final int d() {
        if(this.g == null) {
            this.g = Integer.valueOf(this.a.getMaxUserProperties(this.b));
        }

        return this.g.intValue();
    }
}

