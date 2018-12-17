package com.google.android.gms.internal.measurement;

import java.nio.charset.Charset;

class zzum extends zzul {
    protected final byte[] zzbug;

    zzum(byte[] arg1) {
        super();
        if(arg1 != null) {
            this.zzbug = arg1;
            return;
        }

        throw new NullPointerException();
    }

    public final boolean equals(Object arg5) {
        if((((zzum)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzud)) {
            return 0;
        }

        if(((zzud)this).size() != arg5.size()) {
            return 0;
        }

        if(((zzud)this).size() == 0) {
            return 1;
        }

        if((arg5 instanceof zzum)) {
            int v0 = ((zzud)this).zzuc();
            int v1 = ((zzud)arg5).zzuc();
            if(v0 != 0 && v1 != 0 && v0 != v1) {
                return 0;
            }

            return ((zzul)this).zza(((zzud)arg5), 0, ((zzud)this).size());
        }

        return arg5.equals(this);
    }

    public int size() {
        return this.zzbug.length;
    }

    protected final int zza(int arg2, int arg3, int arg4) {
        return zzvo.zza(arg2, this.zzbug, this.zzud(), arg4);
    }

    protected final String zza(Charset arg5) {
        return new String(this.zzbug, this.zzud(), ((zzud)this).size(), arg5);
    }

    final void zza(zzuc arg4) {
        arg4.zza(this.zzbug, this.zzud(), ((zzud)this).size());
    }

    final boolean zza(zzud arg6, int arg7, int arg8) {
        StringBuilder v1_1;
        if(arg8 <= arg6.size()) {
            if(arg8 <= arg6.size()) {
                if((arg6 instanceof zzum)) {
                    byte[] v7 = this.zzbug;
                    byte[] v1 = ((zzum)arg6).zzbug;
                    int v2 = this.zzud() + arg8;
                    arg8 = this.zzud();
                    int v6;
                    for(v6 = ((zzum)arg6).zzud(); arg8 < v2; ++v6) {
                        if(v7[arg8] != v1[v6]) {
                            return 0;
                        }

                        ++arg8;
                    }

                    return 1;
                }

                return arg6.zzb(0, arg8).equals(((zzud)this).zzb(0, arg8));
            }

            v6 = arg6.size();
            v1_1 = new StringBuilder(59);
            v1_1.append("Ran off end of other: 0, ");
            v1_1.append(arg8);
            v1_1.append(", ");
            v1_1.append(v6);
            throw new IllegalArgumentException(v1_1.toString());
        }

        arg7 = ((zzud)this).size();
        v1_1 = new StringBuilder(40);
        v1_1.append("Length too large: ");
        v1_1.append(arg8);
        v1_1.append(arg7);
        throw new IllegalArgumentException(v1_1.toString());
    }

    public byte zzal(int arg2) {
        return this.zzbug[arg2];
    }

    public final zzud zzb(int arg3, int arg4) {
        arg3 = zzum.zzb(0, arg4, ((zzud)this).size());
        if(arg3 == 0) {
            return zzud.zzbtz;
        }

        return new zzuh(this.zzbug, this.zzud(), arg3);
    }

    public final boolean zzub() {
        int v0 = this.zzud();
        return zzyj.zzf(this.zzbug, v0, ((zzud)this).size() + v0);
    }

    protected int zzud() {
        return 0;
    }
}

