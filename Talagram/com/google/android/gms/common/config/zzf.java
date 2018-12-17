package com.google.android.gms.common.config;

import android.content.Context;

final class zzf extends GservicesValue {
    zzf(String arg1, String arg2) {
        super(arg1, arg2);
    }

    protected final Object retrieve(String arg3) {
        return GservicesValue.zzch().getString(this.mKey, this.mDefaultValue);
    }

    protected final Object retrieveFromDirectBootCache(Context arg3, String arg4, Object arg5) {
        return arg3.getSharedPreferences("gservices-direboot-cache", 0).getString(arg4, ((String)arg5));
    }
}

