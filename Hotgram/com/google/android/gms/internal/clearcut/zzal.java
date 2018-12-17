package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

final class zzal extends zzae {
    private final Object lock;
    private String zzec;
    private Object zzed;

    zzal(zzao arg1, String arg2, Object arg3, zzan arg4) {
        this.zzee = arg4;
        super(arg1, arg2, arg3, null);
        this.lock = new Object();
    }

    protected final Object zza(SharedPreferences arg5) {
        try {
            return ((zzae)this).zzb(arg5.getString(this.zzds, ""));
        }
        catch(ClassCastException v5) {
            String v0 = "PhenotypeFlag";
            String v1 = "Invalid byte[] value in SharedPreferences for ";
            String v2 = String.valueOf(this.zzds);
            v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
            Log.e(v0, v1, ((Throwable)v5));
            return null;
        }
    }

    protected final Object zzb(String arg5) {
        Object v0;
        try {
            v0 = this.lock;
            __monitor_enter(v0);
        }
        catch(IllegalArgumentException ) {
            goto label_17;
        }

        try {
            if(!arg5.equals(this.zzec)) {
                Object v1_1 = this.zzee.zzb(Base64.decode(arg5, 3));
                this.zzec = arg5;
                this.zzed = v1_1;
            }

            __monitor_exit(v0);
            return this.zzed;
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_15;
        }

        try {
            throw v1;
        }
        catch(IllegalArgumentException ) {
        label_17:
            String v1_2 = this.zzds;
            StringBuilder v3 = new StringBuilder(String.valueOf(v1_2).length() + 27 + String.valueOf(arg5).length());
            v3.append("Invalid byte[] value for ");
            v3.append(v1_2);
            v3.append(": ");
            v3.append(arg5);
            Log.e("PhenotypeFlag", v3.toString());
            return null;
        }
    }
}

