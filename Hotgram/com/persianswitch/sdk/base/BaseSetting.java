package com.persianswitch.sdk.base;

import android.content.Context;
import com.persianswitch.sdk.base.db.BaseDatabase;
import com.persianswitch.sdk.base.manager.LanguageManager;
import com.persianswitch.sdk.base.preference.IPreference;
import com.persianswitch.sdk.base.preference.SqlitePreference;
import com.persianswitch.sdk.base.preference.SqliteSecurePreference;
import com.persianswitch.sdk.base.utils.strings.StringUtils;

public class BaseSetting {
    private static IPreference a;
    private static IPreference b;
    private static String c;

    public BaseSetting() {
        super();
    }

    public static IPreference a(Context arg4) {
        if(BaseSetting.b == null) {
            String v0 = BaseSetting.n(arg4);
            if(StringUtils.a(v0)) {
                v0 = SqliteSecurePreference.f();
                BaseSetting.f(arg4, v0);
            }

            BaseSetting.b = new SqliteSecurePreference(BaseSetting.c, v0, new BaseDatabase(arg4), "secure_pref");
        }

        return BaseSetting.b;
    }

    public static void a(Context arg1, int arg2) {
        BaseSetting.a(arg1).b("host_id", arg2);
    }

    public static void a(Context arg1, long arg2) {
        BaseSetting.a(arg1).b("application_id", arg2);
    }

    public static void a(Context arg1, String arg2) {
        BaseSetting.a(arg1).b("imei", arg2);
    }

    public static void a(Context arg1, boolean arg2) {
        BaseSetting.m(arg1).b("need_re_verification", arg2);
    }

    public static void a(String arg0) {
        BaseSetting.c = arg0;
        BaseSetting.a = null;
        BaseSetting.b = null;
    }

    public static long b(Context arg7) {
        long v1;
        Class v0 = BaseSetting.class;
        __monitor_enter(v0);
        try {
            v1 = BaseSetting.m(arg7).a("last_tran_id", 1);
            BaseSetting.m(arg7).b("last_tran_id", 1 + v1);
        }
        catch(Throwable v7) {
            __monitor_exit(v0);
            throw v7;
        }

        __monitor_exit(v0);
        return v1;
    }

    public static void b(Context arg1, long arg2) {
        BaseSetting.m(arg1).b("last_time_cards_synced", arg2);
    }

    public static void b(Context arg1, String arg2) {
        BaseSetting.a(arg1).b("mac", arg2);
    }

    public static long c(Context arg3) {
        return BaseSetting.a(arg3).a("application_id", 0);
    }

    public static void c(Context arg1, long arg2) {
        BaseSetting.m(arg1).b("server_time_diff", arg2);
    }

    public static void c(Context arg1, String arg2) {
        BaseSetting.a(arg1).b("application_token", arg2);
    }

    public static String d(Context arg2) {
        return BaseSetting.a(arg2).a("imei", "");
    }

    public static void d(Context arg1, String arg2) {
        BaseSetting.a(arg1).b("mobile_number", arg2);
    }

    public static String e(Context arg2) {
        return BaseSetting.a(arg2).a("mac", "");
    }

    public static void e(Context arg1, String arg2) {
        BaseSetting.m(arg1).b("language", arg2);
    }

    private static void f(Context arg1, String arg2) {
        BaseSetting.m(arg1).b("salt", arg2);
    }

    public static String f(Context arg2) {
        return BaseSetting.a(arg2).a("application_token", "");
    }

    public static int g(Context arg2) {
        return BaseSetting.a(arg2).a("host_id", 0);
    }

    public static String h(Context arg2) {
        return BaseSetting.a(arg2).a("mobile_number", "");
    }

    public static String i(Context arg2) {
        return BaseSetting.m(arg2).a("language", LanguageManager.a());
    }

    public static long j(Context arg3) {
        return BaseSetting.m(arg3).a("last_time_cards_synced", 0);
    }

    public static long k(Context arg3) {
        return BaseSetting.m(arg3).a("server_time_diff", 0);
    }

    public static boolean l(Context arg2) {
        return BaseSetting.m(arg2).a("need_re_verification", false);
    }

    private static IPreference m(Context arg2) {
        if(BaseSetting.a == null) {
            BaseSetting.a = new SqlitePreference(new BaseDatabase(arg2), "pref");
        }

        return BaseSetting.a;
    }

    private static String n(Context arg2) {
        return BaseSetting.m(arg2).a("salt", null);
    }
}

