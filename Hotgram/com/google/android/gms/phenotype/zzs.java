package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Log;

final class zzs extends PhenotypeFlag {
    zzs(Factory arg2, String arg3, String arg4) {
        super(arg2, arg3, arg4, null);
    }

    public final Object zza(SharedPreferences arg1) {
        return this.zzb(arg1);
    }

    public final Object zza(String arg1) {
        return arg1;
    }

    private final String zzb(SharedPreferences arg6) {
        String v0 = null;
        try {
            return arg6.getString(this.zzap, v0);
        }
        catch(ClassCastException v6) {
            String v1 = "PhenotypeFlag";
            String v2 = "Invalid string value in SharedPreferences for ";
            String v3 = String.valueOf(this.zzap);
            v2 = v3.length() != 0 ? v2.concat(v3) : new String(v2);
            Log.e(v1, v2, ((Throwable)v6));
            return v0;
        }
    }
}

