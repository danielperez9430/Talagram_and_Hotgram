package com.google.android.gms.internal.places;

import java.nio.charset.Charset;

class zzfy extends zzfx {
    protected final byte[] zzoa;

    zzfy(byte[] arg1) {
        super();
        this.zzoa = arg1;
    }

    public final boolean equals(Object arg5) {
        if((((zzfy)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzfr)) {
            return 0;
        }

        if(((zzfr)this).size() != arg5.size()) {
            return 0;
        }

        if(((zzfr)this).size() == 0) {
            return 1;
        }

        if((arg5 instanceof zzfy)) {
            int v0 = ((zzfr)this).zzcf();
            int v1 = ((zzfr)arg5).zzcf();
            if(v0 != 0 && v1 != 0 && v0 != v1) {
                return 0;
            }

            return ((zzfx)this).zzb(((zzfr)arg5), 0, ((zzfr)this).size());
        }

        return arg5.equals(this);
    }

    public int size() {
        return this.zzoa.length;
    }

    public byte zzaf(int arg2) {
        return this.zzoa[arg2];
    }

    protected final int zzb(int arg2, int arg3, int arg4) {
        return zzhb.zzb(arg2, this.zzoa, this.zzcg(), arg4);
    }

    protected final String zzb(Charset arg5) {
        return new String(this.zzoa, this.zzcg(), ((zzfr)this).size(), arg5);
    }

    final void zzb(zzfq arg4) {
        arg4.zzb(this.zzoa, this.zzcg(), ((zzfr)this).size());
    }

    protected void zzb(byte[] arg1, int arg2, int arg3, int arg4) {
        System.arraycopy(this.zzoa, 0, arg1, 0, arg4);
    }

    final boolean zzb(zzfr arg6, int arg7, int arg8) {
        StringBuilder v1_1;
        if(arg8 <= arg6.size()) {
            if(arg8 <= arg6.size()) {
                if((arg6 instanceof zzfy)) {
                    byte[] v7 = this.zzoa;
                    byte[] v1 = ((zzfy)arg6).zzoa;
                    int v2 = this.zzcg() + arg8;
                    arg8 = this.zzcg();
                    int v6;
                    for(v6 = ((zzfy)arg6).zzcg(); arg8 < v2; ++v6) {
                        if(v7[arg8] != v1[v6]) {
                            return 0;
                        }

                        ++arg8;
                    }

                    return 1;
                }

                return arg6.zzc(0, arg8).equals(((zzfr)this).zzc(0, arg8));
            }

            v6 = arg6.size();
            v1_1 = new StringBuilder(59);
            v1_1.append("Ran off end of other: 0, ");
            v1_1.append(arg8);
            v1_1.append(", ");
            v1_1.append(v6);
            throw new IllegalArgumentException(v1_1.toString());
        }

        arg7 = ((zzfr)this).size();
        v1_1 = new StringBuilder(40);
        v1_1.append("Length too large: ");
        v1_1.append(arg8);
        v1_1.append(arg7);
        throw new IllegalArgumentException(v1_1.toString());
    }

    public final zzfr zzc(int arg3, int arg4) {
        arg3 = zzfy.zzc(0, arg4, ((zzfr)this).size());
        if(arg3 == 0) {
            return zzfr.zznt;
        }

        return new zzfu(this.zzoa, this.zzcg(), arg3);
    }

    public final boolean zzce() {
        int v0 = this.zzcg();
        return zzjy.zzh(this.zzoa, v0, ((zzfr)this).size() + v0);
    }

    protected int zzcg() {
        return 0;
    }
}

