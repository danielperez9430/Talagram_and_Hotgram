package com.persianswitch.sdk.base.security;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings$Secure;
import com.persianswitch.sdk.base.BaseSetting;
import com.persianswitch.sdk.base.Config;
import com.persianswitch.sdk.base.manager.LanguageManager;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import java.util.Locale;

public class DeviceInfo {
    public DeviceInfo() {
        super();
    }

    public static int a(Context arg3, String arg4) {
        Object[] v1 = new Object[2];
        int v2 = 0;
        v1[0] = DeviceInfo.b(arg3);
        v1[1] = arg4;
        String v3 = StringUtils.a("", v1);
        int v4 = 0;
        while(v2 < v3.length()) {
            v4 += v3.charAt(v2) * v2;
            ++v2;
        }

        return v4;
    }

    public static String a() {
        return String.format(Locale.US, "%s(%s)", Build.MODEL, Build.MANUFACTURER);
    }

    public static String a(Context arg3, Config arg4) {
        try {
            String v0 = ";";
            Object[] v1 = new Object[4];
            String v3 = LanguageManager.a(arg3).b() ? "1" : "2";
            v1[0] = v3;
            v1[1] = arg4.b();
            v1[2] = arg4.c();
            v1[3] = arg4.d();
            return StringUtils.a(v0, v1);
        }
        catch(Exception ) {
            return "";
        }
    }

    public static String a(Context arg3, String arg4, String arg5) {
        return StringUtils.a("|", new Object[]{DeviceInfo.c(arg3), arg4, arg5});
    }

    public static boolean a(Context arg4) {
        boolean v1 = false;
        int v0 = (arg4.getResources().getConfiguration().screenLayout & 15) == 4 ? 1 : 0;
        int v4 = (arg4.getResources().getConfiguration().screenLayout & 15) == 3 ? 1 : 0;
        if(v0 != 0 || v4 != 0) {
            v1 = true;
        }

        return v1;
    }

    public static String b(Context arg2) {
        return DeviceInfo.a(arg2, BaseSetting.d(arg2), BaseSetting.e(arg2));
    }

    @SuppressLint(value={"HardwareIds"}) public static String c(Context arg1) {
        try {
            return Settings$Secure.getString(arg1.getContentResolver(), "android_id");
        }
        catch(Exception v1) {
            v1.printStackTrace();
            return "";
        }
    }
}

