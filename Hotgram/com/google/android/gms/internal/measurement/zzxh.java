package com.google.android.gms.internal.measurement;

final class zzxh implements zzwr {
    private final int flags;
    private final String info;
    private final Object[] zzcba;
    private final zzwt zzcbd;

    zzxh(zzwt arg4, String arg5, Object[] arg6) {
        super();
        this.zzcbd = arg4;
        this.info = arg5;
        this.zzcba = arg6;
        int v4 = arg5.charAt(0);
        int v6 = 55296;
        if(v4 < v6) {
            this.flags = v4;
            return;
        }

        v4 &= 8191;
        int v0 = 13;
        int v1;
        for(v1 = 1; true; v1 = v2) {
            int v2 = v1 + 1;
            v1 = arg5.charAt(v1);
            if(v1 < v6) {
                break;
            }

            v4 |= (v1 & 8191) << v0;
            v0 += 13;
        }

        this.flags = v4 | v1 << v0;
    }

    public final int zzxg() {
        if((this.flags & 1) == 1) {
            return zze.zzbzb;
        }

        return zze.zzbzc;
    }

    public final boolean zzxh() {
        if((this.flags & 2) == 2) {
            return 1;
        }

        return 0;
    }

    public final zzwt zzxi() {
        return this.zzcbd;
    }

    final String zzxp() {
        return this.info;
    }

    final Object[] zzxq() {
        return this.zzcba;
    }
}

