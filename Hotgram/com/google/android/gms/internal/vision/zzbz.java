package com.google.android.gms.internal.vision;

final class zzbz extends zzbx {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzhf;
    private int zzhg;
    private int zzhh;
    private int zzhi;

    zzbz(byte[] arg1, int arg2, int arg3, boolean arg4, zzby arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    private zzbz(byte[] arg2, int arg3, int arg4, boolean arg5) {
        super(null);
        this.zzhi = 2147483647;
        this.buffer = arg2;
        this.limit = arg4 + arg3;
        this.pos = arg3;
        this.zzhh = this.pos;
        this.zzhf = arg5;
    }

    public final int zzay() {
        return this.pos - this.zzhh;
    }

    public final int zzn(int arg3) {
        if(arg3 >= 0) {
            arg3 += ((zzbx)this).zzay();
            int v0 = this.zzhi;
            if(arg3 <= v0) {
                this.zzhi = arg3;
                this.limit += this.zzhg;
                arg3 = this.limit - this.zzhh;
                if(arg3 > this.zzhi) {
                    this.zzhg = arg3 - this.zzhi;
                    this.limit -= this.zzhg;
                }
                else {
                    this.zzhg = 0;
                }

                return v0;
            }

            throw zzcx.zzcb();
        }

        throw zzcx.zzcc();
    }
}

