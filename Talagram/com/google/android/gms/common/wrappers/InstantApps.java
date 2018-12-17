package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;

public class InstantApps {
    private static Context zzaay;
    private static Boolean zzaaz;

    public InstantApps() {
        super();
    }

    public static boolean isInstantApp(Context arg3) {
        Boolean v3_2;
        boolean v3_1;
        Context v1;
        Class v0 = InstantApps.class;
        __monitor_enter(v0);
        try {
            v1 = arg3.getApplicationContext();
            if(InstantApps.zzaay != null && InstantApps.zzaaz != null && InstantApps.zzaay == v1) {
                v3_1 = InstantApps.zzaaz.booleanValue();
                goto label_11;
            }

            goto label_13;
        }
        catch(Throwable v3) {
            goto label_38;
        }

    label_11:
        __monitor_exit(v0);
        return v3_1;
    label_13:
        Boolean v2 = null;
        try {
            InstantApps.zzaaz = v2;
            if(PlatformVersion.isAtLeastO()) {
                v3_2 = Boolean.valueOf(v1.getPackageManager().isInstantApp());
                goto label_20;
            }

            try {
                arg3.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                InstantApps.zzaaz = Boolean.valueOf(true);
                goto label_32;
            }
            catch(ClassNotFoundException ) {
                try {
                    v3_2 = Boolean.valueOf(false);
                label_20:
                    InstantApps.zzaaz = v3_2;
                label_32:
                    InstantApps.zzaay = v1;
                    v3_1 = InstantApps.zzaaz.booleanValue();
                }
                catch(Throwable v3) {
                label_38:
                    __monitor_exit(v0);
                    throw v3;
                }
            }
        }
        catch(Throwable v3) {
            goto label_38;
        }

        __monitor_exit(v0);
        return v3_1;
    }
}

