package com.crashlytics.android;

import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;

class b implements a {
    b() {
        super();
    }

    public boolean a(Context arg4) {
        boolean v0 = true;
        try {
            Bundle v4 = arg4.getPackageManager().getApplicationInfo(arg4.getPackageName(), 128).metaData;
            if(v4 != null) {
                if(v4.getBoolean("firebase_crashlytics_collection_enabled", true)) {
                    return v0;
                }
                else {
                    return false;
                }
            }
        }
        catch(PackageManager$NameNotFoundException ) {
        }

        return v0;
    }
}

