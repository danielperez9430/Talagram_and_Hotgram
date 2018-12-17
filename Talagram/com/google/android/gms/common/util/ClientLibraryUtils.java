package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.common.wrappers.Wrappers;

public class ClientLibraryUtils {
    public static final int GMS_CLIENT_VERSION_UNKNOWN = -1;

    private ClientLibraryUtils() {
        super();
    }

    public static int getClientVersion(Context arg0, String arg1) {
        return ClientLibraryUtils.getClientVersion(ClientLibraryUtils.getPackageInfo(arg0, arg1));
    }

    public static int getClientVersion(PackageInfo arg2) {
        int v0 = -1;
        if(arg2 != null) {
            if(arg2.applicationInfo == null) {
            }
            else {
                Bundle v2 = arg2.applicationInfo.metaData;
                if(v2 == null) {
                    return v0;
                }
                else {
                    return v2.getInt("com.google.android.gms.version", v0);
                }
            }
        }

        return v0;
    }

    public static PackageInfo getPackageInfo(Context arg1, String arg2) {
        try {
            return Wrappers.packageManager(arg1).getPackageInfo(arg2, 128);
        }
        catch(PackageManager$NameNotFoundException ) {
            return null;
        }
    }

    public static boolean isPackageSide() {
        return 0;
    }

    public static boolean isPackageStopped(Context arg1, String arg2) {
        "com.google.android.gms".equals(arg2);
        try {
            if((Wrappers.packageManager(arg1).getApplicationInfo(arg2, 0).flags & 2097152) == 0) {
                return 0;
            }
        }
        catch(PackageManager$NameNotFoundException ) {
            return 0;
        }

        return 1;
    }
}

