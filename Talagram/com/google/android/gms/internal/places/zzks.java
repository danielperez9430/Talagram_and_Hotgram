package com.google.android.gms.internal.places;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class zzks {
    private static final Charset ISO_8859_1;
    protected static final Charset UTF_8;
    public static final Object zzaao;

    static {
        zzks.UTF_8 = Charset.forName("UTF-8");
        zzks.ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzks.zzaao = new Object();
    }

    public static boolean equals(int[] arg1, int[] arg2) {
        if(arg1 != null) {
            if(arg1.length == 0) {
            }
            else {
                return Arrays.equals(arg1, arg2);
            }
        }

        if(arg2 != null) {
            if(arg2.length == 0) {
            }
            else {
                return 0;
            }
        }

        return 1;
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

    public static int hashCode(int[] arg1) {
        if(arg1 != null) {
            if(arg1.length == 0) {
            }
            else {
                return Arrays.hashCode(arg1);
            }
        }

        return 0;
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

    public static int zzb(byte[][] arg4) {
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

    public static void zzb(zzko arg1, zzko arg2) {
        if(arg1.zzaaf != null) {
            arg2.zzaaf = arg1.zzaaf.clone();
        }
    }

    public static boolean zzb(byte[][] arg8, byte[][] arg9) {
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

