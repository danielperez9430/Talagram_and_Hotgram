package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

public final class zzb {
    private SharedPreferences zzs;

    public zzb(Context arg4) {
        super();
        SharedPreferences v0 = null;
        try {
            arg4 = GooglePlayServicesUtilLight.getRemoteContext(arg4);
            SharedPreferences v4_1 = arg4 == null ? v0 : arg4.getSharedPreferences("google_ads_flags", 0);
            this.zzs = v4_1;
            return;
        }
        catch(Throwable v4) {
            Log.w("GmscoreFlag", "Error while getting SharedPreferences ", v4);
            this.zzs = v0;
            return;
        }
    }

    public final boolean getBoolean(String arg3, boolean arg4) {
        try {
            if(this.zzs == null) {
                return 0;
            }

            return this.zzs.getBoolean(arg3, false);
        }
        catch(Throwable v3) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", v3);
            return 0;
        }
    }

    final float getFloat(String arg3, float arg4) {
        try {
            if(this.zzs == null) {
                return 0;
            }

            return this.zzs.getFloat(arg3, 0f);
        }
        catch(Throwable v3) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", v3);
            return 0;
        }
    }

    final String getString(String arg3, String arg4) {
        try {
            if(this.zzs == null) {
                return arg4;
            }

            return this.zzs.getString(arg3, arg4);
        }
        catch(Throwable v3) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", v3);
            return arg4;
        }
    }
}

