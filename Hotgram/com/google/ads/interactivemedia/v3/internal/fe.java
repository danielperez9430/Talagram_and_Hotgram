package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;

public final class fe {
    public static String a(String arg1) {
        if(!TextUtils.isEmpty(((CharSequence)arg1))) {
            return arg1;
        }

        throw new IllegalArgumentException();
    }

    public static Object a(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static void a(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException(String.valueOf(arg1));
    }

    public static void a(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void b(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException();
    }

    public static void b(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException(String.valueOf(arg1));
    }
}

