package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

public final class ko extends km {
    public static int a(Object[] arg0) {
        return Arrays.hashCode(arg0);
    }

    public static boolean a(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != arg1) {
            if(arg0 != null && (arg0.equals(arg1))) {
                goto label_7;
            }

            v0 = false;
        }
        else {
        label_7:
            v0 = true;
        }

        return v0;
    }
}

