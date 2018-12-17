package com.google.android.gms.common.config;

import android.content.Context;

final class zza extends GservicesValue {
    zza(String arg1, Boolean arg2) {
        super(arg1, arg2);
    }

    protected final Object retrieve(String arg3) {
        return GservicesValue.zzch().zza(this.mKey, this.mDefaultValue);
    }

    protected final Object retrieveFromDirectBootCache(Context arg1, String arg2, Object arg3) {
        return zza.zza(arg1, arg2, ((Boolean)arg3));
    }

    private static Boolean zza(Context arg2, String arg3, Boolean arg4) {
        String v2 = arg2.getSharedPreferences("gservices-direboot-cache", 0).getString(arg3, null);
        if(v2 == null) {
            return arg4;
        }

        try {
            return Boolean.valueOf(Boolean.parseBoolean(v2));
        }
        catch(NumberFormatException ) {
            return arg4;
        }
    }
}

