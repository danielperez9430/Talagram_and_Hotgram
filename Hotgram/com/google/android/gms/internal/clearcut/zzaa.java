package com.google.android.gms.internal.clearcut;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.UserManager;

public class zzaa {
    private static volatile UserManager zzdc;
    private static volatile boolean zzdd;

    static {
        zzaa.zzdd = zzaa.zzf() ^ 1;
    }

    private zzaa() {
        super();
    }

    public static boolean zze(Context arg1) {
        if((zzaa.zzf()) && !zzaa.zzf(arg1)) {
            return 1;
        }

        return 0;
    }

    private static boolean zzf() {
        if(Build$VERSION.SDK_INT >= 24) {
            return 1;
        }

        return 0;
    }

    @TargetApi(value=24) private static boolean zzf(Context arg2) {
        Object v0_2;
        boolean v0 = zzaa.zzdd;
        if(!v0) {
            UserManager v0_1 = zzaa.zzdc;
            if(v0_1 == null) {
                Class v1 = zzaa.class;
                __monitor_enter(v1);
                try {
                    v0_1 = zzaa.zzdc;
                    if(v0_1 == null) {
                        Object v2_1 = arg2.getSystemService(UserManager.class);
                        zzaa.zzdc = ((UserManager)v2_1);
                        if(v2_1 == null) {
                            zzaa.zzdd = true;
                            __monitor_exit(v1);
                            return 1;
                        }
                        else {
                            v0_2 = v2_1;
                        }
                    }

                    __monitor_exit(v1);
                    goto label_22;
                label_20:
                    __monitor_exit(v1);
                }
                catch(Throwable v2) {
                    goto label_20;
                }

                throw v2;
            }

        label_22:
            v0 = ((UserManager)v0_2).isUserUnlocked();
            zzaa.zzdd = v0;
            if(!v0) {
                return v0;
            }

            zzaa.zzdc = null;
        }

        return v0;
    }
}

