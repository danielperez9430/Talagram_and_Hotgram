package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class DowngradeableSafeParcelHelper {
        public DowngradeableSafeParcelHelper() {
            super();
        }

        public static Bundle getExtras(Intent arg1, Context arg2, Integer arg3) {
            Object v0 = DowngradeableSafeParcel.zzcs();
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return DowngradeableSafeParcel.getExtras(arg1, arg2, arg3);
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_6;
            }

            throw v1;
        }

        public static Parcelable getParcelable(Intent arg1, String arg2, Context arg3, Integer arg4) {
            Object v0 = DowngradeableSafeParcel.zzcs();
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return DowngradeableSafeParcel.getParcelable(arg1, arg2, arg3, arg4);
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_6;
            }

            throw v1;
        }

        public static Parcelable getParcelable(Bundle arg1, String arg2, Context arg3, Integer arg4) {
            Object v0 = DowngradeableSafeParcel.zzcs();
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return DowngradeableSafeParcel.getParcelable(arg1, arg2, arg3, arg4);
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_6;
            }

            throw v1;
        }

        public static boolean putParcelable(Bundle arg0, String arg1, DowngradeableSafeParcel arg2, Context arg3, Integer arg4) {
            return DowngradeableSafeParcel.putParcelable(arg0, arg1, arg2, arg3, arg4);
        }
    }

    private static final Object zzsj;
    private static ClassLoader zzsk;
    private static Integer zzsl;
    private boolean zzsm;

    static {
        DowngradeableSafeParcel.zzsj = new Object();
        DowngradeableSafeParcel.zzsk = null;
        DowngradeableSafeParcel.zzsl = null;
    }

    public DowngradeableSafeParcel() {
        super();
        this.zzsm = false;
    }

    protected static boolean canUnparcelSafely(String arg1) {
        ClassLoader v0 = DowngradeableSafeParcel.getUnparcelClassLoader();
        if(v0 == null) {
            return 1;
        }

        try {
            return DowngradeableSafeParcel.zza(v0.loadClass(arg1));
        }
        catch(Exception ) {
            return 0;
        }
    }

    static Bundle getExtras(Intent arg1, Context arg2, Integer arg3) {
        Bundle v2;
        ClassLoader v0 = null;
        if(arg2 != null) {
            try {
                DowngradeableSafeParcel.zza(arg2.getClassLoader(), arg3);
                if(arg1.getExtras() != null) {
                    v2 = new Bundle();
                    v2.putAll(arg1.getExtras());
                }
                else {
                    goto label_14;
                }
            }
            catch(Throwable v1) {
                DowngradeableSafeParcel.zza(v0, ((Integer)v0));
                throw v1;
            }
        }
        else {
        label_14:
            v2 = ((Bundle)v0);
        }

        DowngradeableSafeParcel.zza(v0, ((Integer)v0));
        return v2;
    }

    static Parcelable getParcelable(Intent arg1, String arg2, Context arg3, Integer arg4) {
        Parcelable v1_1;
        ClassLoader v0 = null;
        if(arg3 != null) {
            try {
                DowngradeableSafeParcel.zza(arg3.getClassLoader(), arg4);
                v1_1 = arg1.getParcelableExtra(arg2);
            }
            catch(Throwable v1) {
                DowngradeableSafeParcel.zza(v0, ((Integer)v0));
                throw v1;
            }
        }
        else {
            v1_1 = ((Parcelable)v0);
        }

        DowngradeableSafeParcel.zza(v0, ((Integer)v0));
        return v1_1;
    }

    static Parcelable getParcelable(Bundle arg1, String arg2, Context arg3, Integer arg4) {
        Parcelable v1_1;
        ClassLoader v0 = null;
        if(arg3 != null) {
            try {
                DowngradeableSafeParcel.zza(arg3.getClassLoader(), arg4);
                v1_1 = arg1.getParcelable(arg2);
            }
            catch(Throwable v1) {
                DowngradeableSafeParcel.zza(v0, ((Integer)v0));
                throw v1;
            }
        }
        else {
            v1_1 = ((Parcelable)v0);
        }

        DowngradeableSafeParcel.zza(v0, ((Integer)v0));
        return v1_1;
    }

    protected static ClassLoader getUnparcelClassLoader() {
        Object v0 = DowngradeableSafeParcel.zzsj;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return DowngradeableSafeParcel.zzsk;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    protected static Integer getUnparcelClientVersion() {
        Object v0 = DowngradeableSafeParcel.zzsj;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return DowngradeableSafeParcel.zzsl;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    protected abstract boolean prepareForClientVersion(int arg1);

    static boolean putParcelable(Bundle arg1, String arg2, DowngradeableSafeParcel arg3, Context arg4, Integer arg5) {
        boolean v0 = false;
        if(arg4 == null && arg5 == null) {
            return 0;
        }

        if(arg3.zza(arg4, arg5)) {
            arg1.putParcelable(arg2, ((Parcelable)arg3));
            v0 = true;
        }

        return v0;
    }

    public void setShouldDowngrade(boolean arg1) {
        this.zzsm = arg1;
    }

    protected boolean shouldDowngrade() {
        return this.zzsm;
    }

    private static boolean zza(Class arg3) {
        try {
            return "SAFE_PARCELABLE_NULL_STRING".equals(arg3.getField("NULL").get(null));
        }
        catch(IllegalAccessException ) {
            return 0;
        }
    }

    private static void zza(ClassLoader arg1, Integer arg2) {
        Object v0 = DowngradeableSafeParcel.zzsj;
        __monitor_enter(v0);
        try {
            DowngradeableSafeParcel.zzsk = arg1;
            DowngradeableSafeParcel.zzsl = arg2;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    private final boolean zza(Context arg1, Integer arg2) {
        if(arg2 != null) {
            return this.prepareForClientVersion(arg2.intValue());
        }

        try {
            this.setShouldDowngrade(DowngradeableSafeParcel.zza(arg1.getClassLoader().loadClass(this.getClass().getCanonicalName())) ^ 1);
            return 1;
        }
        catch(Exception ) {
            return 0;
        }
    }

    static Object zzcs() {
        return DowngradeableSafeParcel.zzsj;
    }
}

