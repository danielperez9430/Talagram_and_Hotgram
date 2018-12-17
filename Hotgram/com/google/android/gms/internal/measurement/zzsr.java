package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzsr extends zzsl {
    zzsr(zzsv arg2, String arg3, Boolean arg4) {
        super(arg2, arg3, arg4, null);
    }

    protected final Object zzfj(String arg5) {
        if(zzsg.zzbqe.matcher(((CharSequence)arg5)).matches()) {
            return Boolean.valueOf(true);
        }

        if(zzsg.zzbqf.matcher(((CharSequence)arg5)).matches()) {
            return Boolean.valueOf(false);
        }

        String v1 = this.zzbrc;
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 28 + String.valueOf(arg5).length());
        v3.append("Invalid boolean value for ");
        v3.append(v1);
        v3.append(": ");
        v3.append(arg5);
        Log.e("PhenotypeFlag", v3.toString());
        return null;
    }
}

