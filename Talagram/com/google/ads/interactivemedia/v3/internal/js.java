package com.google.ads.interactivemedia.v3.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings$Secure;
import android.provider.Settings$SettingNotFoundException;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class js {
    public String deviceId;
    public String idType;
    public boolean isLimitedAdTracking;

    public js() {
        super();
        this.deviceId = "";
        this.idType = "";
        this.isLimitedAdTracking = false;
    }

    public js(Context arg4) {
        super();
        this.deviceId = "";
        this.idType = "";
        this.isLimitedAdTracking = false;
        try {
            Info v1 = this.getInfo(arg4);
            this.deviceId = v1.getId();
            this.idType = "adid";
            this.isLimitedAdTracking = v1.isLimitAdTrackingEnabled();
        }
        catch(Exception ) {
            try {
                ContentResolver v4 = arg4.getContentResolver();
                this.deviceId = Settings$Secure.getString(v4, "advertising_id");
                this.idType = "afai";
                boolean v4_1 = Settings$Secure.getInt(v4, "limit_ad_tracking") == 0 ? true : false;
                this.isLimitedAdTracking = v4_1;
            }
            catch(Settings$SettingNotFoundException ) {
                Log.w("IMASDK", "Failed to get advertising ID.");
                this.deviceId = "";
                this.idType = "";
                this.isLimitedAdTracking = false;
            }
        }
    }

    protected Info getInfo(Context arg1) {
        return AdvertisingIdClient.getAdvertisingIdInfo(arg1);
    }
}

