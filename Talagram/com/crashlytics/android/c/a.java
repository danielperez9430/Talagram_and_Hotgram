package com.crashlytics.android.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import c.a.a.a.a.b.p;

class a {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;

    a(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
    }

    public static a a(Context arg7, p arg8, String arg9, String arg10) {
        String v4 = arg7.getPackageName();
        String v3 = arg8.i();
        PackageInfo v7 = arg7.getPackageManager().getPackageInfo(v4, 0);
        String v5 = Integer.toString(v7.versionCode);
        String v7_1 = v7.versionName == null ? "0.0" : v7.versionName;
        String v6 = v7_1;
        return new a(arg9, arg10, v3, v4, v5, v6);
    }
}

