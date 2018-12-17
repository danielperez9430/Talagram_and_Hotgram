package com.persianswitch.sdk.payment;

import android.content.Context;
import com.persianswitch.sdk.base.preference.IPreference;
import com.persianswitch.sdk.base.preference.SqlitePreference;
import com.persianswitch.sdk.base.preference.SqliteSecurePreference;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import com.persianswitch.sdk.payment.database.SDKDatabase;

public final class SDKSetting {
    private static SqlitePreference a;
    private static SqlitePreference b;
    private static String c;

    public SDKSetting() {
        super();
    }

    public static IPreference a(Context arg4) {
        if(SDKSetting.b == null) {
            String v0 = SDKSetting.e(arg4);
            if(StringUtils.a(v0)) {
                v0 = SqliteSecurePreference.f();
                SDKSetting.a(arg4, v0);
            }

            SDKSetting.b = new SqliteSecurePreference(SDKSetting.c, v0, new SDKDatabase(arg4), "secure_pref");
        }

        return SDKSetting.b;
    }

    private static void a(Context arg1, String arg2) {
        SDKSetting.d(arg1).b("salt", arg2);
    }

    public static void a(Context arg1, boolean arg2) {
        SDKSetting.a(arg1).b("rootWarningShowed", arg2);
    }

    public static void a(String arg0) {
        SDKSetting.c = arg0;
        SDKSetting.a = null;
        SDKSetting.b = null;
    }

    public static void b(Context arg1, boolean arg2) {
        SDKSetting.a(arg1).b("numberValidatedIsShowed", arg2);
    }

    public static boolean b(Context arg2) {
        return SDKSetting.a(arg2).a("rootWarningShowed", false);
    }

    public static boolean c(Context arg2) {
        return SDKSetting.a(arg2).a("numberValidatedIsShowed", false);
    }

    private static IPreference d(Context arg2) {
        if(SDKSetting.a == null) {
            SDKSetting.a = new SqlitePreference(new SDKDatabase(arg2), "pref");
        }

        return SDKSetting.a;
    }

    private static String e(Context arg2) {
        return SDKSetting.d(arg2).a("salt", null);
    }
}

