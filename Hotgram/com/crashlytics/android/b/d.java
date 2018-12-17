package com.crashlytics.android.b;

import java.io.InputStream;
import java.util.Properties;

class d {
    public final String a;
    public final String b;
    public final String c;
    public final String d;

    d(String arg1, String arg2, String arg3, String arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public static d a(InputStream arg1) {
        Properties v0 = new Properties();
        v0.load(arg1);
        return d.a(v0);
    }

    public static d a(Properties arg4) {
        return new d(arg4.getProperty("version_code"), arg4.getProperty("version_name"), arg4.getProperty("build_id"), arg4.getProperty("package_name"));
    }
}

