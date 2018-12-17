package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzsp extends zzsl {
    zzsp(zzsv arg2, String arg3, Long arg4) {
        super(arg2, arg3, arg4, null);
    }

    protected final Object zzfj(String arg1) {
        return this.zzfk(arg1);
    }

    private final Long zzfk(String arg5) {
        try {
            return Long.valueOf(Long.parseLong(arg5));
        }
        catch(NumberFormatException ) {
            String v1 = this.zzbrc;
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 25 + String.valueOf(arg5).length());
            v3.append("Invalid long value for ");
            v3.append(v1);
            v3.append(": ");
            v3.append(arg5);
            Log.e("PhenotypeFlag", v3.toString());
            return null;
        }
    }
}

