package com.google.android.gms.internal.vision;

import java.nio.charset.Charset;

class zzbv extends zzbu {
    protected final byte[] zzha;

    zzbv(byte[] arg1) {
        super();
        this.zzha = arg1;
    }

    public final boolean equals(Object arg5) {
        if((((zzbv)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzbo)) {
            return 0;
        }

        if(((zzbo)this).size() != arg5.size()) {
            return 0;
        }

        if(((zzbo)this).size() == 0) {
            return 1;
        }

        if((arg5 instanceof zzbv)) {
            int v0 = ((zzbo)this).zzau();
            int v1 = ((zzbo)arg5).zzau();
            if(v0 != 0 && v1 != 0 && v0 != v1) {
                return 0;
            }

            return ((zzbu)this).zza(((zzbo)arg5), 0, ((zzbo)this).size());
        }

        return arg5.equals(this);
    }

    public int size() {
        return this.zzha.length;
    }

    protected final int zza(int arg2, int arg3, int arg4) {
        return zzct.zza(arg2, this.zzha, this.zzav(), arg4);
    }

    protected final String zza(Charset arg5) {
        return new String(this.zzha, this.zzav(), ((zzbo)this).size(), arg5);
    }

    final void zza(zzbn arg4) {
        arg4.zza(this.zzha, this.zzav(), ((zzbo)this).size());
    }

    final boolean zza(zzbo arg6, int arg7, int arg8) {
        StringBuilder v1_1;
        if(arg8 <= arg6.size()) {
            if(arg8 <= arg6.size()) {
                if((arg6 instanceof zzbv)) {
                    byte[] v7 = this.zzha;
                    byte[] v1 = ((zzbv)arg6).zzha;
                    int v2 = this.zzav() + arg8;
                    arg8 = this.zzav();
                    int v6;
                    for(v6 = ((zzbv)arg6).zzav(); arg8 < v2; ++v6) {
                        if(v7[arg8] != v1[v6]) {
                            return 0;
                        }

                        ++arg8;
                    }

                    return 1;
                }

                return arg6.zzc(0, arg8).equals(((zzbo)this).zzc(0, arg8));
            }

            v6 = arg6.size();
            v1_1 = new StringBuilder(59);
            v1_1.append("Ran off end of other: 0, ");
            v1_1.append(arg8);
            v1_1.append(", ");
            v1_1.append(v6);
            throw new IllegalArgumentException(v1_1.toString());
        }

        arg7 = ((zzbo)this).size();
        v1_1 = new StringBuilder(40);
        v1_1.append("Length too large: ");
        v1_1.append(arg8);
        v1_1.append(arg7);
        throw new IllegalArgumentException(v1_1.toString());
    }

    public final boolean zzat() {
        int v0 = this.zzav();
        return zzfn.zze(this.zzha, v0, ((zzbo)this).size() + v0);
    }

    protected int zzav() {
        return 0;
    }

    public final zzbo zzc(int arg3, int arg4) {
        arg3 = zzbv.zzb(0, arg4, ((zzbo)this).size());
        if(arg3 == 0) {
            return zzbo.zzgt;
        }

        return new zzbr(this.zzha, this.zzav(), arg3);
    }

    public byte zzl(int arg2) {
        return this.zzha[arg2];
    }
}

