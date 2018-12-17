package com.google.android.gms.common.config;

import android.content.Context;

final class zzd extends GservicesValue {
    zzd(String arg1, Double arg2) {
        super(arg1, arg2);
    }

    protected final Object retrieve(String arg3) {
        return GservicesValue.zzch().zza(this.mKey, this.mDefaultValue);
    }

    protected final Object retrieveFromDirectBootCache(Context arg1, String arg2, Object arg3) {
        return zzd.zza(arg1, arg2, ((Double)arg3));
    }

    private static Double zza(Context arg2, String arg3, Double arg4) {
        String v2 = arg2.getSharedPreferences("gservices-direboot-cache", 0).getString(arg3, null);
        if(v2 == null) {
            return arg4;
        }

        try {
            return Double.valueOf(Double.parseDouble(v2));
        }
        catch(NumberFormatException ) {
            return arg4;
        }
    }
}

