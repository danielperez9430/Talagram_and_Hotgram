package net.hockeyapp.android.e;

import android.content.Context;
import android.os.Build$VERSION;
import android.provider.Settings$Global;
import android.provider.Settings$Secure;

public class h {
    public static boolean a(Context arg2) {
        String v2_1;
        String v0;
        int v1 = 26;
        if(Build$VERSION.SDK_INT >= v1) {
            boolean v2 = arg2.getApplicationInfo().targetSdkVersion < v1 || (arg2.getPackageManager().canRequestPackageInstalls()) ? true : false;
            return v2;
        }

        if(Build$VERSION.SDK_INT < 17 || Build$VERSION.SDK_INT >= 21) {
            v0 = "1";
            v2_1 = Settings$Secure.getString(arg2.getContentResolver(), "install_non_market_apps");
        }
        else {
            v0 = "1";
            v2_1 = Settings$Global.getString(arg2.getContentResolver(), "install_non_market_apps");
        }

        return v0.equals(v2_1);
    }

    public static boolean a(int[] arg4) {
        int v0 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg4[v2] != 0) {
                return 0;
            }
        }

        return 1;
    }

    public static int[] a(Context arg3, String[] arg4) {
        if(arg4 == null) {
            return null;
        }

        int[] v0 = new int[arg4.length];
        int v1;
        for(v1 = 0; v1 < arg4.length; ++v1) {
            v0[v1] = arg3.checkCallingOrSelfPermission(arg4[v1]);
        }

        return v0;
    }
}

