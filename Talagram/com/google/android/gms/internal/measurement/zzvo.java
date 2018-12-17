package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzvo {
    private static final Charset ISO_8859_1;
    static final Charset UTF_8;
    public static final byte[] zzbzj;
    private static final ByteBuffer zzbzk;
    private static final zzuo zzbzl;

    static {
        zzvo.UTF_8 = Charset.forName("UTF-8");
        zzvo.ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] v1 = new byte[0];
        zzvo.zzbzj = v1;
        zzvo.zzbzk = ByteBuffer.wrap(v1);
        zzvo.zzbzl = zzuo.zza(zzvo.zzbzj, 0, zzvo.zzbzj.length, false);
    }

    static Object checkNotNull(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static int hashCode(byte[] arg2) {
        int v2 = zzvo.zza(arg2.length, arg2, 0, arg2.length);
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

    static Object zza(Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }

    static Object zzb(Object arg0, Object arg1) {
        return ((zzwt)arg0).zzwd().zza(((zzwt)arg1)).zzwi();
    }

    public static int zzbf(long arg2) {
        return ((int)(arg2 ^ arg2 >>> 32));
    }

    static boolean zzf(zzwt arg0) {
        return 0;
    }

    public static boolean zzl(byte[] arg0) {
        return zzyj.zzl(arg0);
    }

    public static String zzm(byte[] arg2) {
        return new String(arg2, zzvo.UTF_8);
    }

    public static int zzw(boolean arg0) {
        if(arg0) {
            return 1231;
        }

        return 1237;
    }
}

