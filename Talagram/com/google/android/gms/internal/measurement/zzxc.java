package com.google.android.gms.internal.measurement;

final class zzxc {
    private static final zzxa zzcbq;
    private static final zzxa zzcbr;

    static {
        zzxc.zzcbq = zzxc.zzxm();
        zzxc.zzcbr = new zzxb();
    }

    static zzxa zzxk() {
        return zzxc.zzcbq;
    }

    static zzxa zzxl() {
        return zzxc.zzcbr;
    }

    private static zzxa zzxm() {
        try {
            return Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

