package com.google.android.gms.common.util;

import android.os.Build$VERSION;
import android.support.v4.os.a;

@VisibleForTesting public final class PlatformVersion {
    private PlatformVersion() {
        super();
    }

    public static boolean isAtLeastFroyo() {
        return 1;
    }

    public static boolean isAtLeastGingerbread() {
        return 1;
    }

    public static boolean isAtLeastGingerbreadMR1() {
        return 1;
    }

    public static boolean isAtLeastHoneycomb() {
        return 1;
    }

    public static boolean isAtLeastHoneycombMR1() {
        return 1;
    }

    public static boolean isAtLeastHoneycombMR2() {
        return 1;
    }

    public static boolean isAtLeastIceCreamSandwich() {
        return 1;
    }

    public static boolean isAtLeastIceCreamSandwichMR1() {
        if(Build$VERSION.SDK_INT >= 15) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastJellyBean() {
        if(Build$VERSION.SDK_INT >= 16) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastJellyBeanMR1() {
        if(Build$VERSION.SDK_INT >= 17) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastJellyBeanMR2() {
        if(Build$VERSION.SDK_INT >= 18) {
            return 1;
        }

        return 0;
    }

    @Deprecated public static boolean isAtLeastKeyLimePie() {
        return PlatformVersion.isAtLeastKitKat();
    }

    public static boolean isAtLeastKitKat() {
        if(Build$VERSION.SDK_INT >= 19) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastKitKatWatch() {
        if(Build$VERSION.SDK_INT >= 20) {
            return 1;
        }

        return 0;
    }

    @Deprecated public static boolean isAtLeastL() {
        return PlatformVersion.isAtLeastLollipop();
    }

    public static boolean isAtLeastLollipop() {
        if(Build$VERSION.SDK_INT >= 21) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastLollipopMR1() {
        if(Build$VERSION.SDK_INT >= 22) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastM() {
        if(Build$VERSION.SDK_INT >= 23) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastN() {
        if(Build$VERSION.SDK_INT >= 24) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastNMR1() {
        if(Build$VERSION.SDK_INT >= 25) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastO() {
        if(Build$VERSION.SDK_INT >= 26) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastOMR1() {
        if(Build$VERSION.SDK_INT >= 27) {
            return 1;
        }

        return 0;
    }

    public static boolean isAtLeastP() {
        return a.b();
    }
}

