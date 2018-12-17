package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

public class Wrappers {
    private PackageManagerWrapper zzaba;
    private static Wrappers zzabb;

    static {
        Wrappers.zzabb = new Wrappers();
    }

    public Wrappers() {
        super();
        this.zzaba = null;
    }

    @VisibleForTesting public PackageManagerWrapper getPackageManagerWrapper(Context arg2) {
        PackageManagerWrapper v2_1;
        __monitor_enter(this);
        try {
            if(this.zzaba == null) {
                if(arg2.getApplicationContext() == null) {
                }
                else {
                    arg2 = arg2.getApplicationContext();
                }

                this.zzaba = new PackageManagerWrapper(arg2);
            }

            v2_1 = this.zzaba;
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }

    public static PackageManagerWrapper packageManager(Context arg1) {
        return Wrappers.zzabb.getPackageManagerWrapper(arg1);
    }

    @VisibleForTesting public static void resetForTests() {
        Wrappers.zzabb = new Wrappers();
    }

    public static void setWrappers(Wrappers arg0) {
        Wrappers.zzabb = arg0;
    }
}

