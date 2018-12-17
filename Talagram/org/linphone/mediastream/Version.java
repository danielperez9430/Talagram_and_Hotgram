package org.linphone.mediastream;

import android.content.Context;
import android.os.Build$VERSION;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;

public class Version {
    public static final int API03_CUPCAKE_15 = 3;
    public static final int API04_DONUT_16 = 4;
    public static final int API05_ECLAIR_20 = 5;
    public static final int API06_ECLAIR_201 = 6;
    public static final int API07_ECLAIR_21 = 7;
    public static final int API08_FROYO_22 = 8;
    public static final int API09_GINGERBREAD_23 = 9;
    public static final int API10_GINGERBREAD_MR1_233 = 10;
    public static final int API11_HONEYCOMB_30 = 11;
    public static final int API12_HONEYCOMB_MR1_31X = 12;
    public static final int API13_HONEYCOMB_MR2_32 = 13;
    public static final int API14_ICE_CREAM_SANDWICH_40 = 14;
    public static final int API15_ICE_CREAM_SANDWICH_403 = 15;
    public static final int API16_JELLY_BEAN_41 = 16;
    public static final int API17_JELLY_BEAN_42 = 17;
    public static final int API18_JELLY_BEAN_43 = 18;
    public static final int API19_KITKAT_44 = 19;
    public static final int API21_LOLLIPOP_50 = 21;
    public static final int API22_LOLLIPOP_51 = 22;
    public static final int API23_MARSHMALLOW_60 = 23;
    public static final int API24_NOUGAT_70 = 24;
    private static final int buildVersion;
    private static Boolean hasNeon;
    private static Boolean sCacheHasZrtp;

    static {
        Version.buildVersion = Build$VERSION.SDK_INT;
    }

    public Version() {
        super();
    }

    public static void dumpCapabilities() {
        StringBuilder v0 = new StringBuilder(" ==== Capabilities dump ====\n");
        if(Version.isArmv7()) {
            v0.append("Has neon: ");
            v0.append(Boolean.toString(Version.hasNeon()));
            v0.append("\n");
        }

        v0.append("Has ZRTP: ");
        v0.append(Boolean.toString(Version.hasZrtp()));
        v0.append("\n");
        Log.i(new Object[]{v0.toString()});
    }

    public static List getCpuAbis() {
        ArrayList v0 = new ArrayList();
        if(Version.sdkAboveOrEqual(21)) {
            try {
                Object v2_1 = Build.class.getField("SUPPORTED_ABIS").get(null);
                int v3 = v2_1.length;
                int v4;
                for(v4 = 0; true; ++v4) {
                    if(v4 >= v3) {
                        goto label_28;
                    }

                    ((List)v0).add(v2_1[v4]);
                }
            }
            catch(Throwable v2) {
                Log.e(new Object[]{v2});
                goto label_28;
            }
        }

        ((List)v0).add(Build.CPU_ABI);
        ((List)v0).add(Build.CPU_ABI2);
    label_28:
        return ((List)v0);
    }

    public static boolean hasFastCpu() {
        return Version.isArmv5() ^ 1;
    }

    public static boolean hasFastCpuWithAsmOptim() {
        boolean v0 = !Version.isX86() && !Version.isArmv5() && (Version.hasNeon()) || (Version.isX86()) ? true : false;
        return v0;
    }

    public static boolean hasNeon() {
        if(Version.hasNeon == null) {
            Version.hasNeon = Boolean.valueOf(Version.nativeHasNeon());
        }

        return Version.hasNeon.booleanValue();
    }

    public static boolean hasZrtp() {
        if(Version.sCacheHasZrtp == null) {
            Version.sCacheHasZrtp = Boolean.valueOf(Version.nativeHasZrtp());
        }

        return Version.sCacheHasZrtp.booleanValue();
    }

    private static boolean isArmv5() {
        try {
            return Version.getCpuAbis().get(0).equals("armeabi");
        }
        catch(Throwable v1) {
            Log.e(new Object[]{v1});
            return 0;
        }
    }

    private static boolean isArmv7() {
        try {
            return Version.getCpuAbis().get(0).startsWith("armeabi-v7");
        }
        catch(Throwable v1) {
            Log.e(new Object[]{v1});
            return 0;
        }
    }

    public static boolean isHDVideoCapable() {
        int v0 = Runtime.getRuntime().availableProcessors();
        boolean v2 = true;
        if(!Version.isVideoCapable() || !Version.hasFastCpuWithAsmOptim() || v0 <= 1) {
            v2 = false;
        }
        else {
        }

        return v2;
    }

    public static boolean isVideoCapable() {
        boolean v0 = (Version.sdkStrictlyBelow(5)) || !Version.hasFastCpu() ? false : true;
        return v0;
    }

    private static boolean isX86() {
        try {
            return Version.getCpuAbis().get(0).startsWith("x86");
        }
        catch(Throwable v1) {
            Log.e(new Object[]{v1});
            return 0;
        }
    }

    public static boolean isXLargeScreen(Context arg1) {
        boolean v1 = (arg1.getResources().getConfiguration().screenLayout & 15) >= 4 ? true : false;
        return v1;
    }

    private static native boolean nativeHasNeon() {
    }

    private static native boolean nativeHasZrtp() {
    }

    public static int sdk() {
        return Version.buildVersion;
    }

    public static final boolean sdkAboveOrEqual(int arg1) {
        boolean v1 = Version.buildVersion >= arg1 ? true : false;
        return v1;
    }

    public static final boolean sdkStrictlyBelow(int arg1) {
        boolean v1 = Version.buildVersion < arg1 ? true : false;
        return v1;
    }
}

