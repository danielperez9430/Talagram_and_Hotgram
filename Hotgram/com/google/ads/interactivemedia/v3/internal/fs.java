package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.os.Trace;

public final class fs {
    public static void a(String arg2) {
        if(ft.a >= 18) {
            fs.b(arg2);
        }
    }

    public static void a() {
        if(ft.a >= 18) {
            fs.b();
        }
    }

    @TargetApi(value=18) private static void b() {
        Trace.endSection();
    }

    @TargetApi(value=18) private static void b(String arg0) {
        Trace.beginSection(arg0);
    }
}

