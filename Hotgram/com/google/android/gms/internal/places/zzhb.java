package com.google.android.gms.internal.places;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzhb {
    private static final Charset ISO_8859_1;
    static final Charset UTF_8;
    public static final byte[] zztl;
    private static final ByteBuffer zztm;
    private static final zzga zztn;

    static {
        zzhb.UTF_8 = Charset.forName("UTF-8");
        zzhb.ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] v1 = new byte[0];
        zzhb.zztl = v1;
        zzhb.zztm = ByteBuffer.wrap(v1);
        zzhb.zztn = zzga.zzb(zzhb.zztl, 0, zzhb.zztl.length, false);
    }

    static Object checkNotNull(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static int hashCode(byte[] arg2) {
        int v2 = zzhb.zzb(arg2.length, arg2, 0, arg2.length);
        if(v2 == 0) {
            v2 = 1;
        }

        return v2;
    }

    static int zzb(int arg2, byte[] arg3, int arg4, int arg5) {
        int v0 = arg2;
        for(arg2 = arg4; arg2 < arg4 + arg5; ++arg2) {
            v0 = v0 * 31 + arg3[arg2];
        }

        return v0;
    }

    static Object zzb(Object arg0, Object arg1) {
        return ((zzih)arg0).zzdq().zzb(((zzih)arg1)).zzdw();
    }

    static Object zzb(Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }

    public static int zzf(boolean arg0) {
        if(arg0) {
            return 1231;
        }

        return 1237;
    }

    public static boolean zzf(byte[] arg0) {
        return zzjy.zzf(arg0);
    }

    static boolean zzg(zzih arg0) {
        return 0;
    }

    public static String zzg(byte[] arg2) {
        return new String(arg2, zzhb.UTF_8);
    }

    public static int zzo(long arg2) {
        return ((int)(arg2 ^ arg2 >>> 32));
    }
}

