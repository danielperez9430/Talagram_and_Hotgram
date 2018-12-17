package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzci {
    private static final Charset ISO_8859_1;
    static final Charset UTF_8;
    public static final byte[] zzkt;
    private static final ByteBuffer zzku;
    private static final zzbk zzkv;

    static {
        zzci.UTF_8 = Charset.forName("UTF-8");
        zzci.ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] v1 = new byte[0];
        zzci.zzkt = v1;
        zzci.zzku = ByteBuffer.wrap(v1);
        zzci.zzkv = zzbk.zza(zzci.zzkt, 0, zzci.zzkt.length, false);
    }

    static Object checkNotNull(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static int hashCode(byte[] arg2) {
        int v2 = zzci.zza(arg2.length, arg2, 0, arg2.length);
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
        return ((zzdo)arg0).zzbc().zza(((zzdo)arg1)).zzbi();
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

    public static boolean zze(byte[] arg0) {
        return zzff.zze(arg0);
    }

    static boolean zzf(zzdo arg0) {
        return 0;
    }

    public static String zzf(byte[] arg2) {
        return new String(arg2, zzci.UTF_8);
    }

    public static int zzl(long arg2) {
        return ((int)(arg2 ^ arg2 >>> 32));
    }
}

