package com.crashlytics.android.a;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.text.TextUtils;
import c.a.a.a.a.d.a;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

class af implements a {
    af() {
        super();
    }

    public byte[] a(ad arg2) {
        return this.b(arg2).toString().getBytes("UTF-8");
    }

    public byte[] a(Object arg1) {
        return this.a(((ad)arg1));
    }

    @TargetApi(value=9) public JSONObject b(ad arg5) {
        String v3;
        String v2;
        JSONObject v0;
        try {
            v0 = new JSONObject();
            ae v1 = arg5.a;
            v0.put("appBundleId", v1.a);
            v0.put("executionId", v1.b);
            v0.put("installationId", v1.c);
            if(TextUtils.isEmpty(v1.e)) {
                v2 = "androidId";
                v3 = v1.d;
            }
            else {
                v2 = "advertisingId";
                v3 = v1.e;
            }

            v0.put(v2, v3);
            v0.put("limitAdTrackingEnabled", v1.f);
            v0.put("betaDeviceToken", v1.g);
            v0.put("buildId", v1.h);
            v0.put("osVersion", v1.i);
            v0.put("deviceModel", v1.j);
            v0.put("appVersionCode", v1.k);
            v0.put("appVersionName", v1.l);
            v0.put("timestamp", arg5.b);
            v0.put("type", arg5.c.toString());
            if(arg5.d != null) {
                v0.put("details", new JSONObject(arg5.d));
            }

            v0.put("customType", arg5.e);
            if(arg5.f != null) {
                v0.put("customAttributes", new JSONObject(arg5.f));
            }

            v0.put("predefinedType", arg5.g);
            if(arg5.h != null) {
                v0.put("predefinedAttributes", new JSONObject(arg5.h));
            }
        }
        catch(JSONException v5) {
            if(Build$VERSION.SDK_INT >= 9) {
                throw new IOException(v5.getMessage(), ((Throwable)v5));
            }
            else {
                throw new IOException(v5.getMessage());
            }
        }

        return v0;
    }
}

