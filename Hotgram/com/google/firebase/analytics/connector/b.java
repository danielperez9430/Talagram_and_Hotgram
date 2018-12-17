package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzak;
import com.google.android.gms.measurement.internal.zzbt;
import com.google.firebase.b.d;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class b implements a {
    @VisibleForTesting final Map a;
    private static volatile a b;
    @VisibleForTesting private final AppMeasurement c;

    private b(AppMeasurement arg1) {
        super();
        Preconditions.checkNotNull(arg1);
        this.c = arg1;
        this.a = new ConcurrentHashMap();
    }

    @KeepForSdk public static a a(com.google.firebase.b arg5, Context arg6, d arg7) {
        Preconditions.checkNotNull(arg5);
        Preconditions.checkNotNull(arg6);
        Preconditions.checkNotNull(arg7);
        Preconditions.checkNotNull(arg6.getApplicationContext());
        if(b.b == null) {
            Class v0 = b.class;
            __monitor_enter(v0);
            try {
                if(b.b == null) {
                    Bundle v1 = new Bundle(1);
                    if(arg5.f()) {
                        arg7.a(com.google.firebase.a.class, c.a, com.google.firebase.analytics.connector.d.a);
                        v1.putBoolean("dataCollectionDefaultEnabled", arg5.e());
                    }

                    b.b = new b(zzbt.zza(arg6, zzak.zzc(v1)).zzki());
                }

                __monitor_exit(v0);
                goto label_34;
            label_32:
                __monitor_exit(v0);
            }
            catch(Throwable v5) {
                goto label_32;
            }

            throw v5;
        }

    label_34:
        return b.b;
    }

    static final void a(com.google.firebase.b.a arg2) {
        boolean v2 = arg2.b().a;
        Class v0 = b.class;
        __monitor_enter(v0);
        try {
            b.b.c.zzd(v2);
            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v2_1) {
            goto label_10;
        }

        throw v2_1;
    }

    @KeepForSdk public void a(String arg2, String arg3, Bundle arg4) {
        if(arg4 == null) {
            arg4 = new Bundle();
        }

        if(!com.google.firebase.analytics.connector.internal.b.a(arg2)) {
            return;
        }

        if(!com.google.firebase.analytics.connector.internal.b.a(arg3, arg4)) {
            return;
        }

        if(!com.google.firebase.analytics.connector.internal.b.a(arg2, arg3, arg4)) {
            return;
        }

        this.c.logEventInternal(arg2, arg3, arg4);
    }

    @KeepForSdk public void a(String arg2, String arg3, Object arg4) {
        if(!com.google.firebase.analytics.connector.internal.b.a(arg2)) {
            return;
        }

        if(!com.google.firebase.analytics.connector.internal.b.a(arg2, arg3)) {
            return;
        }

        this.c.setUserPropertyInternal(arg2, arg3, arg4);
    }
}

