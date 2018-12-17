package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Log;

final class zzaj extends zzae {
    zzaj(zzao arg2, String arg3, Boolean arg4) {
        super(arg2, arg3, arg4, null);
    }

    protected final Object zza(SharedPreferences arg1) {
        return this.zzb(arg1);
    }

    private final Boolean zzb(SharedPreferences arg5) {
        try {
            return Boolean.valueOf(arg5.getBoolean(this.zzds, false));
        }
        catch(ClassCastException v5) {
            String v0 = "PhenotypeFlag";
            String v1 = "Invalid boolean value in SharedPreferences for ";
            String v2 = String.valueOf(this.zzds);
            v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
            Log.e(v0, v1, ((Throwable)v5));
            return null;
        }
    }

    protected final Object zzb(String arg5) {
        if(zzy.zzcr.matcher(((CharSequence)arg5)).matches()) {
            return Boolean.valueOf(true);
        }

        if(zzy.zzcs.matcher(((CharSequence)arg5)).matches()) {
            return Boolean.valueOf(false);
        }

        String v1 = this.zzds;
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 28 + String.valueOf(arg5).length());
        v3.append("Invalid boolean value for ");
        v3.append(v1);
        v3.append(": ");
        v3.append(arg5);
        Log.e("PhenotypeFlag", v3.toString());
        return null;
    }
}

