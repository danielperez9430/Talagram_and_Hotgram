package com.crashlytics.android.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

class v implements ak {
    private final Context a;
    private final String b;

    public v(Context arg1, String arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public String a() {
        PackageManager v0 = this.a.getPackageManager();
        String v1 = null;
        try {
            Bundle v0_1 = v0.getApplicationInfo(this.b, 128).metaData;
            if(v0_1 != null) {
                v1 = v0_1.getString("io.fabric.unity.crashlytics.version");
            }

            return v1;
        }
        catch(Exception ) {
            return v1;
        }
    }
}

