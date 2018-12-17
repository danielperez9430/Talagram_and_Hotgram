package com.google.ads.interactivemedia.v3.internal;

import android.os.Build$VERSION;
import android.os.Build;
import org.json.JSONObject;

public final class ab {
    public static String a() {
        String v0 = Build.MANUFACTURER;
        String v1 = Build.MODEL;
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 2 + String.valueOf(v1).length());
        v3.append(v0);
        v3.append("; ");
        v3.append(v1);
        return v3.toString();
    }

    public static String b() {
        return Integer.toString(Build$VERSION.SDK_INT);
    }

    public static String c() {
        return "Android";
    }

    public static JSONObject d() {
        JSONObject v0 = new JSONObject();
        ac.a(v0, "deviceType", ab.a());
        ac.a(v0, "osVersion", ab.b());
        ac.a(v0, "os", ab.c());
        return v0;
    }
}

