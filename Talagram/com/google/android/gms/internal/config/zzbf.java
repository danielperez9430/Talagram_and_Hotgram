package com.google.android.gms.internal.config;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class zzbf {
    private static final Charset ISO_8859_1;
    protected static final Charset UTF_8;
    public static final Object zzcp;

    static {
        zzbf.UTF_8 = Charset.forName("UTF-8");
        zzbf.ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzbf.zzcp = new Object();
    }

    public static boolean equals(Object[] arg8, Object[] arg9) {
        int v1 = arg8 == null ? 0 : arg8.length;
        int v2 = arg9 == null ? 0 : arg9.length;
        int v3 = 0;
        int v4 = 0;
    label_11:
        while(v3 < v1) {
            if(arg8[v3] != null) {
                break;
            }

            ++v3;
        }

        while(v4 < v2) {
            if(arg9[v4] != null) {
                break;
            }

            ++v4;
        }

        int v6 = v3 >= v1 ? 1 : 0;
        int v7 = v4 >= v2 ? 1 : 0;
        if(v6 != 0 && v7 != 0) {
            return 1;
        }

        if(v6 != v7) {
            return 0;
        }

        if(!arg8[v3].equals(arg9[v4])) {
            return 0;
        }

        ++v3;
        ++v4;
        goto label_11;
    }

    public static int hashCode(Object[] arg4) {
        int v0 = 0;
        int v1 = arg4 == null ? 0 : arg4.length;
        int v2 = 0;
        while(v0 < v1) {
            Object v3 = arg4[v0];
            if(v3 != null) {
                v2 = v2 * 31 + v3.hashCode();
            }

            ++v0;
        }

        return v2;
    }

    public static int zza(byte[][] arg4) {
        int v0 = 0;
        int v1 = arg4 == null ? 0 : arg4.length;
        int v2 = 0;
        while(v0 < v1) {
            byte[] v3 = arg4[v0];
            if(v3 != null) {
                v2 = v2 * 31 + Arrays.hashCode(v3);
            }

            ++v0;
        }

        return v2;
    }

    public static void zza(zzbb arg1, zzbb arg2) {
        if(arg1.zzch != null) {
            arg2.zzch = arg1.zzch.clone();
        }
    }

    public static boolean zza(byte[][] arg8, byte[][] arg9) {
        int v1 = arg8 == null ? 0 : arg8.length;
        int v2 = arg9 == null ? 0 : arg9.length;
        int v3 = 0;
        int v4 = 0;
    label_11:
        while(v3 < v1) {
            if(arg8[v3] != null) {
                break;
            }

            ++v3;
        }

        while(v4 < v2) {
            if(arg9[v4] != null) {
                break;
            }

            ++v4;
        }

        int v6 = v3 >= v1 ? 1 : 0;
        int v7 = v4 >= v2 ? 1 : 0;
        if(v6 != 0 && v7 != 0) {
            return 1;
        }

        if(v6 != v7) {
            return 0;
        }

        if(!Arrays.equals(arg8[v3], arg9[v4])) {
            return 0;
        }

        ++v3;
        ++v4;
        goto label_11;
    }
}

