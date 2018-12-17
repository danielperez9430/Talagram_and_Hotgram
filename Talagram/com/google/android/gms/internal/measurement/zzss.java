package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzss extends zzsl {
    zzss(zzsv arg2, String arg3, Double arg4) {
        super(arg2, arg3, arg4, null);
    }

    protected final Object zzfj(String arg1) {
        return this.zzfm(arg1);
    }

    private final Double zzfm(String arg5) {
        try {
            return Double.valueOf(Double.parseDouble(arg5));
        }
        catch(NumberFormatException ) {
            String v1 = this.zzbrc;
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 27 + String.valueOf(arg5).length());
            v3.append("Invalid double value for ");
            v3.append(v1);
            v3.append(": ");
            v3.append(arg5);
            Log.e("PhenotypeFlag", v3.toString());
            return null;
        }
    }
}

