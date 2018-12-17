package com.google.android.gms.internal.clearcut;

import java.nio.charset.Charset;

class zzbi extends zzbh {
    protected final byte[] zzfp;

    zzbi(byte[] arg1) {
        super();
        this.zzfp = arg1;
    }

    public final boolean equals(Object arg5) {
        if((((zzbi)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzbb)) {
            return 0;
        }

        if(((zzbb)this).size() != arg5.size()) {
            return 0;
        }

        if(((zzbb)this).size() == 0) {
            return 1;
        }

        if((arg5 instanceof zzbi)) {
            int v0 = ((zzbb)this).zzab();
            int v1 = ((zzbb)arg5).zzab();
            if(v0 != 0 && v1 != 0 && v0 != v1) {
                return 0;
            }

            return ((zzbh)this).zza(((zzbb)arg5), 0, ((zzbb)this).size());
        }

        return arg5.equals(this);
    }

    public int size() {
        return this.zzfp.length;
    }

    protected final int zza(int arg2, int arg3, int arg4) {
        return zzci.zza(arg2, this.zzfp, this.zzac(), arg4);
    }

    public final zzbb zza(int arg3, int arg4) {
        arg3 = zzbi.zzb(0, arg4, ((zzbb)this).size());
        if(arg3 == 0) {
            return zzbb.zzfi;
        }

        return new zzbe(this.zzfp, this.zzac(), arg3);
    }

    protected final String zza(Charset arg5) {
        return new String(this.zzfp, this.zzac(), ((zzbb)this).size(), arg5);
    }

    final void zza(zzba arg4) {
        arg4.zza(this.zzfp, this.zzac(), ((zzbb)this).size());
    }

    final boolean zza(zzbb arg6, int arg7, int arg8) {
        StringBuilder v1_1;
        if(arg8 <= arg6.size()) {
            if(arg8 <= arg6.size()) {
                if((arg6 instanceof zzbi)) {
                    byte[] v7 = this.zzfp;
                    byte[] v1 = ((zzbi)arg6).zzfp;
                    int v2 = this.zzac() + arg8;
                    arg8 = this.zzac();
                    int v6;
                    for(v6 = ((zzbi)arg6).zzac(); arg8 < v2; ++v6) {
                        if(v7[arg8] != v1[v6]) {
                            return 0;
                        }

                        ++arg8;
                    }

                    return 1;
                }

                return arg6.zza(0, arg8).equals(((zzbb)this).zza(0, arg8));
            }

            v6 = arg6.size();
            v1_1 = new StringBuilder(59);
            v1_1.append("Ran off end of other: 0, ");
            v1_1.append(arg8);
            v1_1.append(", ");
            v1_1.append(v6);
            throw new IllegalArgumentException(v1_1.toString());
        }

        arg7 = ((zzbb)this).size();
        v1_1 = new StringBuilder(40);
        v1_1.append("Length too large: ");
        v1_1.append(arg8);
        v1_1.append(arg7);
        throw new IllegalArgumentException(v1_1.toString());
    }

    public final boolean zzaa() {
        int v0 = this.zzac();
        return zzff.zze(this.zzfp, v0, ((zzbb)this).size() + v0);
    }

    protected int zzac() {
        return 0;
    }

    public byte zzj(int arg2) {
        return this.zzfp[arg2];
    }
}

