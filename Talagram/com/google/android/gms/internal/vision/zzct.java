package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzct {
    private static final Charset ISO_8859_1;
    static final Charset UTF_8;
    public static final byte[] zzlo;
    private static final ByteBuffer zzlp;
    private static final zzbx zzlq;

    static {
        zzct.UTF_8 = Charset.forName("UTF-8");
        zzct.ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] v1 = new byte[0];
        zzct.zzlo = v1;
        zzct.zzlp = ByteBuffer.wrap(v1);
        zzct.zzlq = zzbx.zza(zzct.zzlo, 0, zzct.zzlo.length, false);
    }

    static Object checkNotNull(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static int hashCode(byte[] arg2) {
        int v2 = zzct.zza(arg2.length, arg2, 0, arg2.length);
        if(v2 == 0) {
            v2 = 1;
        }

        return v2;
    }

    static int zza(int arg2, byte[] arg3, int arg4, int arg5) {
        int v0 = arg2;
        for(arg2 = arg4; arg2 < arg4 + arg5; ++arg2) {
            v0 = v0 * 31 + arg3[arg2];
        }

        return v0;
    }

    static Object zza(Object arg0, Object arg1) {
        return ((zzdx)arg0).zzbu().zza(((zzdx)arg1)).zzbz();
    }

    static Object zza(Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }

    public static int zzc(boolean arg0) {
        if(arg0) {
            return 1231;
        }

        return 1237;
    }

    static boolean zzf(zzdx arg0) {
        return 0;
    }

    public static boolean zzf(byte[] arg0) {
        return zzfn.zzf(arg0);
    }

    public static String zzg(byte[] arg2) {
        return new String(arg2, zzct.UTF_8);
    }

    public static int zzk(long arg2) {
        return ((int)(arg2 ^ arg2 >>> 32));
    }
}

