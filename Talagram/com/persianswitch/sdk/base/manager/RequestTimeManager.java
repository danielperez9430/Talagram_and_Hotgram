package com.persianswitch.sdk.base.manager;

import android.content.Context;
import com.persianswitch.sdk.base.BaseSetting;

public final class RequestTimeManager {
    public RequestTimeManager() {
        super();
    }

    public long a(Context arg5) {
        return System.currentTimeMillis() / 1000 + BaseSetting.k(arg5);
    }

    public void a(Context arg7, Long arg8) {
        if(arg8 != null) {
            BaseSetting.c(arg7, arg8.longValue() - System.currentTimeMillis() / 1000);
        }
    }
}

