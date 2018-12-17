package com.google.android.gms.internal.phenotype;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build$VERSION;

public final class zzh {
    private static final Object zzak;
    @SuppressLint(value={"StaticFieldLeak"}) private static Context zzal;
    private static boolean zzam;
    private static volatile Boolean zzan;
    private static volatile Boolean zzbq;

    static {
        zzh.zzak = new Object();
        zzh.zzal = null;
        zzh.zzam = false;
        zzh.zzan = null;
        zzh.zzbq = null;
    }

    public static void init(Context arg3) {
        Object v0 = zzh.zzak;
        __monitor_enter(v0);
        try {
            if(Build$VERSION.SDK_INT < 24 || !arg3.isDeviceProtectedStorage()) {
                Context v1 = arg3.getApplicationContext();
                if(v1 == null) {
                }
                else {
                    arg3 = v1;
                }
            }
            else {
            }

            if(zzh.zzal != arg3) {
                zzh.zzan = null;
            }

            zzh.zzal = arg3;
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_22:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_22;
            }

            throw v3;
        }

        zzh.zzam = false;
    }

    public static void maybeInit(Context arg1) {
        if(zzh.zzal == null) {
            zzh.init(arg1);
        }
    }
}

