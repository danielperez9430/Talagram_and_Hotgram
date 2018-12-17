package com.persianswitch.sdk.base.utils;

import java.io.File;

public final class RootUtils {
    public RootUtils() {
        super();
    }

    public static boolean a() {
        return RootUtils.a("su");
    }

    private static boolean a(String arg8) {
        int v4;
        int v0 = 8;
        boolean v1 = false;
        try {
            String[] v0_1 = new String[v0];
            v0_1[0] = "/sbin/";
            v0_1[1] = "/system/bin/";
            v0_1[2] = "/system/xbin/";
            v0_1[3] = "/data/local/xbin/";
            v0_1[4] = "/data/local/bin/";
            v0_1[5] = "/system/sd/xbin/";
            v0_1[6] = "/system/bin/failsafe/";
            v0_1[7] = "/data/local/";
            int v2 = v0_1.length;
            v4 = 0;
            while(true) {
            label_28:
                if(v4 >= v2) {
                    return v1;
                }

                String v5 = v0_1[v4];
                StringBuilder v7 = new StringBuilder();
                v7.append(v5);
                v7.append(arg8);
                if(!new File(v7.toString()).exists()) {
                    goto label_41;
                }

                return true;
            }
        }
        catch(Exception v8) {
            goto label_44;
        }

        return true;
    label_41:
        ++v4;
        goto label_28;
    label_44:
        v8.printStackTrace();
        return v1;
    }
}

