package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzbt;
import com.google.android.gms.measurement.internal.zzk;
import com.google.firebase.iid.FirebaseInstanceId;

public final class FirebaseAnalytics {
    private static volatile FirebaseAnalytics a;
    private final zzbt b;
    private final Object c;

    private FirebaseAnalytics(zzbt arg1) {
        super();
        Preconditions.checkNotNull(arg1);
        this.b = arg1;
        this.c = new Object();
    }

    @Keep public final String getFirebaseInstanceId() {
        return FirebaseInstanceId.a().c();
    }

    @Keep public static FirebaseAnalytics getInstance(Context arg2) {
        if(FirebaseAnalytics.a == null) {
            Class v0 = FirebaseAnalytics.class;
            __monitor_enter(v0);
            try {
                if(FirebaseAnalytics.a == null) {
                    FirebaseAnalytics.a = new FirebaseAnalytics(zzbt.zza(arg2, null));
                }

                __monitor_exit(v0);
                goto label_16;
            label_14:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_14;
            }

            throw v2;
        }

    label_16:
        return FirebaseAnalytics.a;
    }

    @Keep public final void setCurrentScreen(Activity arg2, String arg3, String arg4) {
        if(!zzk.isMainThread()) {
            this.b.zzgo().zzjg().zzbx("setCurrentScreen must be called from the main thread");
            return;
        }

        this.b.zzgh().setCurrentScreen(arg2, arg3, arg4);
    }
}

