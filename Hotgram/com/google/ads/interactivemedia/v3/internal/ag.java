package com.google.ads.interactivemedia.v3.internal;

import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewParent;

public final class ag {
    public static float a(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getZ();
        }

        return 0;
    }

    public static View b(View arg1) {
        ViewParent v1 = arg1.getParent();
        if((v1 instanceof View)) {
            return ((View)v1);
        }

        return null;
    }

    public static boolean c(View arg3) {
        if(Build$VERSION.SDK_INT >= 19 && !arg3.isAttachedToWindow()) {
            return 0;
        }

        if(!arg3.isShown()) {
            return 0;
        }

        while(arg3 != null) {
            if(arg3.getAlpha() == 0f) {
                return 0;
            }

            arg3 = ag.b(arg3);
        }

        return 1;
    }

    public static boolean d(View arg3) {
        if(Build$VERSION.SDK_INT >= 19 && !arg3.isAttachedToWindow()) {
            return 0;
        }

        if(arg3.getVisibility() != 0) {
            return 0;
        }

        if(arg3.getAlpha() == 0f) {
            return 0;
        }

        return 1;
    }
}

