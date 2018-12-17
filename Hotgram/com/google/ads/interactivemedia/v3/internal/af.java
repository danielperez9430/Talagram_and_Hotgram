package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;

public class af {
    public static void a() {
        if(a.b()) {
            return;
        }

        throw new IllegalStateException("Method called before OMID activation");
    }

    public static void a(h arg1) {
        if(!arg1.equals(h.c)) {
            return;
        }

        throw new IllegalArgumentException("Impression owner is none");
    }

    public static void a(Object arg0, String arg1) {
        if(arg0 != null) {
            return;
        }

        throw new IllegalArgumentException(arg1);
    }

    public static void a(String arg0, int arg1, String arg2) {
        if(arg0.length() <= arg1) {
            return;
        }

        throw new IllegalArgumentException(arg2);
    }

    public static void a(String arg0, String arg1) {
        if(!TextUtils.isEmpty(((CharSequence)arg0))) {
            return;
        }

        throw new IllegalArgumentException(arg1);
    }
}

