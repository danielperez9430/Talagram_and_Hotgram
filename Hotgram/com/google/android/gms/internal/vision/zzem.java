package com.google.android.gms.internal.vision;

final class zzem implements zzdv {
    private final int flags;
    private final String info;
    private final Object[] zznf;
    private final zzdx zzni;

    zzem(zzdx arg4, String arg5, Object[] arg6) {
        super();
        this.zzni = arg4;
        this.info = arg5;
        this.zznf = arg6;
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

    public final int zzcv() {
        if((this.flags & 1) == 1) {
            return zzd.zzlg;
        }

        return zzd.zzlh;
    }

    public final boolean zzcw() {
        if((this.flags & 2) == 2) {
            return 1;
        }

        return 0;
    }

    public final zzdx zzcx() {
        return this.zzni;
    }

    final String zzde() {
        return this.info;
    }

    final Object[] zzdf() {
        return this.zznf;
    }
}

