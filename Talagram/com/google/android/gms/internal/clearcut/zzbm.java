package com.google.android.gms.internal.clearcut;

final class zzbm extends zzbk {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzfu;
    private int zzfv;
    private int zzfw;
    private int zzfx;

    zzbm(byte[] arg1, int arg2, int arg3, boolean arg4, zzbl arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    private zzbm(byte[] arg2, int arg3, int arg4, boolean arg5) {
        super(null);
        this.zzfx = 2147483647;
        this.buffer = arg2;
        this.limit = arg4 + arg3;
        this.pos = arg3;
        this.zzfw = this.pos;
        this.zzfu = arg5;
    }

    public final int zzaf() {
        return this.pos - this.zzfw;
    }

    public final int zzl(int arg3) {
        if(arg3 >= 0) {
            arg3 += ((zzbk)this).zzaf();
            int v0 = this.zzfx;
            if(arg3 <= v0) {
                this.zzfx = arg3;
                this.limit += this.zzfv;
                arg3 = this.limit - this.zzfw;
                if(arg3 > this.zzfx) {
                    this.zzfv = arg3 - this.zzfx;
                    this.limit -= this.zzfv;
                }
                else {
                    this.zzfv = 0;
                }

                return v0;
            }

            throw zzco.zzbl();
        }

        throw new zzco("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
}

