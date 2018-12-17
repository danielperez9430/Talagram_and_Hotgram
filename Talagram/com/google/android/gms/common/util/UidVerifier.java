package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.wrappers.Wrappers;

public final class UidVerifier {
    private UidVerifier() {
        super();
    }

    public static boolean isGooglePlayServicesUid(Context arg3, int arg4) {
        PackageInfo v4_1;
        if(!UidVerifier.uidHasPackageName(arg3, arg4, "com.google.android.gms")) {
            return 0;
        }

        PackageManager v4 = arg3.getPackageManager();
        try {
            v4_1 = v4.getPackageInfo("com.google.android.gms", 64);
        }
        catch(PackageManager$NameNotFoundException ) {
            if(Log.isLoggable("UidVerifier", 3)) {
                Log.d("UidVerifier", "Package manager can\'t find google play services package, defaulting to false");
            }

            return 0;
        }

        return GoogleSignatureVerifier.getInstance(arg3).isGooglePublicSignedPackage(v4_1);
    }

    @TargetApi(value=19) public static boolean uidHasPackageName(Context arg0, int arg1, String arg2) {
        return Wrappers.packageManager(arg0).uidHasPackageName(arg1, arg2);
    }
}

