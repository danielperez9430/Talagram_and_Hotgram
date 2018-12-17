package com.google.android.gms.common.config;

import android.content.Context;

final class zzb extends GservicesValue {
    zzb(String arg1, Long arg2) {
        super(arg1, arg2);
    }

    protected final Object retrieve(String arg3) {
        return GservicesValue.zzch().getLong(this.mKey, this.mDefaultValue);
    }

    protected final Object retrieveFromDirectBootCache(Context arg1, String arg2, Object arg3) {
        return zzb.zza(arg1, arg2, ((Long)arg3));
    }

    private static Long zza(Context arg2, String arg3, Long arg4) {
        String v2 = arg2.getSharedPreferences("gservices-direboot-cache", 0).getString(arg3, null);
        if(v2 == null) {
            return arg4;
        }

        try {
            return Long.valueOf(Long.parseLong(v2));
        }
        catch(NumberFormatException ) {
            return arg4;
        }
    }
}

