package com.google.android.gms.internal.places;

import java.util.Arrays;

final class zzgc extends zzga {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzoh;
    private int zzoi;
    private int zzoj;
    private int zzok;
    private int zzol;

    zzgc(byte[] arg1, int arg2, int arg3, boolean arg4, zzgb arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    private zzgc(byte[] arg2, int arg3, int arg4, boolean arg5) {
        super(null);
        this.zzol = 2147483647;
        this.buffer = arg2;
        this.limit = arg4 + arg3;
        this.pos = arg3;
        this.zzoj = this.pos;
        this.zzoh = arg5;
    }

    public final double readDouble() {
        return Double.longBitsToDouble(this.zzcp());
    }

    public final float readFloat() {
        return Float.intBitsToFloat(this.zzco());
    }

    public final String readString() {
        int v0 = this.zzcm();
        if(v0 > 0 && v0 <= this.limit - this.pos) {
            String v1 = new String(this.buffer, this.pos, v0, zzhb.UTF_8);
            this.pos += v0;
            return v1;
        }

        if(v0 == 0) {
            return "";
        }

        if(v0 < 0) {
            throw zzhh.zzea();
        }

        throw zzhh.zzdz();
    }

    public final void zzah(int arg2) {
        if(this.zzok == arg2) {
            return;
        }

        throw zzhh.zzec();
    }

    public final boolean zzai(int arg6) {
        int v1 = 4;
        int v2 = 0;
        switch(arg6 & 7) {
            case 0: {
                goto label_25;
            }
            case 1: {
                goto label_22;
            }
            case 2: {
                goto label_19;
            }
            case 3: {
                goto label_10;
            }
            case 4: {
                return 0;
            }
            case 5: {
                goto label_7;
            }
        }

        throw zzhh.zzed();
    label_19:
        ((zzga)this).zzam(this.zzcm());
        return 1;
    label_22:
        ((zzga)this).zzam(8);
        return 1;
    label_7:
        ((zzga)this).zzam(v1);
        return 1;
    label_25:
        int v0 = 10;
        if(this.limit - this.pos >= v0) {
            while(true) {
                if(v2 < v0) {
                    byte[] v6 = this.buffer;
                    v1 = this.pos;
                    this.pos = v1 + 1;
                    if(v6[v1] < 0) {
                        ++v2;
                        continue;
                    }
                }
                else {
                    break;
                }

                return 1;
            }

            throw zzhh.zzeb();
        }
        else {
            while(true) {
                if(v2 >= v0) {
                    break;
                }
                else if(this.zzcr() < 0) {
                    ++v2;
                    continue;
                }

                return 1;
            }

            throw zzhh.zzeb();
        }

        return 1;
        do {
        label_10:
            v0 = ((zzga)this).zzcj();
            if(v0 == 0) {
                break;
            }
        }
        while(((zzga)this).zzai(v0));

        ((zzga)this).zzah(arg6 >>> 3 << 3 | v1);
        return 1;
    }

    public final int zzak(int arg2) {
        if(arg2 >= 0) {
            arg2 += ((zzga)this).zzcl();
            int v0 = this.zzol;
            if(arg2 <= v0) {
                this.zzol = arg2;
                this.zzcq();
                return v0;
            }

            throw zzhh.zzdz();
        }

        throw zzhh.zzea();
    }

    public final void zzal(int arg1) {
        this.zzol = arg1;
        this.zzcq();
    }

    public final void zzam(int arg3) {
        if(arg3 >= 0 && arg3 <= this.limit - this.pos) {
            this.pos += arg3;
            return;
        }

        if(arg3 < 0) {
            throw zzhh.zzea();
        }

        throw zzhh.zzdz();
    }

    public final zzih zzb(zzir arg4, zzgl arg5) {
        int v0 = this.zzcm();
        if(this.zzob < this.zzoc) {
            v0 = ((zzga)this).zzak(v0);
            ++this.zzob;
            Object v4 = arg4.zzb(((zzga)this), arg5);
            ((zzga)this).zzah(0);
            --this.zzob;
            ((zzga)this).zzal(v0);
            return ((zzih)v4);
        }

        throw zzhh.zzee();
    }

    public final boolean zzbf() {
        if(this.pos == this.limit) {
            return 1;
        }

        return 0;
    }

    public final long zzbi() {
        return this.zzcn();
    }

    public final long zzbj() {
        return this.zzcn();
    }

    public final int zzbk() {
        return this.zzcm();
    }

    public final long zzbl() {
        return this.zzcp();
    }

    public final int zzbm() {
        return this.zzco();
    }

    public final boolean zzbn() {
        if(this.zzcn() != 0) {
            return 1;
        }

        return 0;
    }

    public final String zzbo() {
        int v0 = this.zzcm();
        if(v0 > 0 && v0 <= this.limit - this.pos) {
            if(zzjy.zzh(this.buffer, this.pos, this.pos + v0)) {
                int v1 = this.pos;
                this.pos += v0;
                return new String(this.buffer, v1, v0, zzhb.UTF_8);
            }
            else {
                throw zzhh.zzeg();
            }
        }

        if(v0 == 0) {
            return "";
        }

        if(v0 <= 0) {
            throw zzhh.zzea();
        }

        throw zzhh.zzdz();
    }

    public final zzfr zzbp() {
        byte[] v0_1;
        int v0 = this.zzcm();
        if(v0 > 0 && v0 <= this.limit - this.pos) {
            zzfr v1 = zzfr.zzc(this.buffer, this.pos, v0);
            this.pos += v0;
            return v1;
        }

        if(v0 == 0) {
            return zzfr.zznt;
        }

        if(v0 > 0 && v0 <= this.limit - this.pos) {
            int v1_1 = this.pos;
            this.pos += v0;
            v0_1 = Arrays.copyOfRange(this.buffer, v1_1, this.pos);
        }
        else if(v0 > 0) {
            goto label_36;
        }
        else if(v0 == 0) {
            v0_1 = zzhb.zztl;
        }
        else {
            goto label_34;
        }

        return zzfr.zzc(v0_1);
    label_34:
        throw zzhh.zzea();
    label_36:
        throw zzhh.zzdz();
    }

    public final int zzbq() {
        return this.zzcm();
    }

    public final int zzbr() {
        return this.zzcm();
    }

    public final int zzbs() {
        return this.zzco();
    }

    public final long zzbt() {
        return this.zzcp();
    }

    public final int zzbu() {
        return zzgc.zzan(this.zzcm());
    }

    public final long zzbv() {
        return zzgc.zzd(this.zzcn());
    }

    public final int zzcj() {
        if(((zzga)this).zzbf()) {
            this.zzok = 0;
            return 0;
        }

        this.zzok = this.zzcm();
        if(this.zzok >>> 3 != 0) {
            return this.zzok;
        }

        throw new zzhh("Protocol message contained an invalid tag (zero).");
    }

    final long zzck() {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < 64; v2 += 7) {
            int v3 = this.zzcr();
            v0 |= (((long)(v3 & 127))) << v2;
            if((v3 & 128) == 0) {
                return v0;
            }
        }

        throw zzhh.zzeb();
    }

    public final int zzcl() {
        return this.pos - this.zzoj;
    }

    private final int zzcm() {
        int v0 = this.pos;
        if(this.limit != v0) {
            byte[] v1 = this.buffer;
            int v2 = v0 + 1;
            v0 = v1[v0];
            if(v0 >= 0) {
                this.pos = v2;
                return v0;
            }
            else if(this.limit - v2 >= 9) {
                int v3 = v2 + 1;
                v0 ^= v1[v2] << 7;
                if(v0 < 0) {
                    v0 ^= -128;
                }
                else {
                    v2 = v3 + 1;
                    v0 ^= v1[v3] << 14;
                    if(v0 >= 0) {
                        v0 ^= 16256;
                    }
                    else {
                        v3 = v2 + 1;
                        v0 ^= v1[v2] << 21;
                        if(v0 < 0) {
                            v0 ^= -2080896;
                            goto label_58;
                        }
                        else {
                            v2 = v3 + 1;
                            v0 = v0 ^ v1[v3] << 28 ^ 266354560;
                            if(v1[v3] < 0) {
                                v3 = v2 + 1;
                                if(v1[v2] < 0) {
                                    v2 = v3 + 1;
                                    if(v1[v3] < 0) {
                                        v3 = v2 + 1;
                                        if(v1[v2] < 0) {
                                            v2 = v3 + 1;
                                            if(v1[v3] < 0) {
                                                v3 = v2 + 1;
                                                if(v1[v2] < 0) {
                                                    goto label_60;
                                                }

                                                goto label_58;
                                            }
                                        }
                                        else {
                                            goto label_58;
                                        }
                                    }
                                }
                                else {
                                    goto label_58;
                                }
                            }
                        }
                    }

                    v3 = v2;
                }

            label_58:
                this.pos = v3;
                return v0;
            }
        }

    label_60:
        return ((int)((zzga)this).zzck());
    }

    private final long zzcn() {
        long v0_1;
        int v6;
        long v2_1;
        long v9;
        int v0 = this.pos;
        if(this.limit != v0) {
            byte[] v1 = this.buffer;
            int v2 = v0 + 1;
            v0 = v1[v0];
            if(v0 >= 0) {
                this.pos = v2;
                return ((long)v0);
            }
            else if(this.limit - v2 >= 9) {
                int v3 = v2 + 1;
                v0 ^= v1[v2] << 7;
                if(v0 < 0) {
                    v0 ^= -128;
                    goto label_20;
                }
                else {
                    v2 = v3 + 1;
                    v0 ^= v1[v3] << 14;
                    if(v0 >= 0) {
                        v9 = ((long)(v0 ^ 16256));
                        v0 = v2;
                        goto label_23;
                    }
                    else {
                        v3 = v2 + 1;
                        v0 ^= v1[v2] << 21;
                        if(v0 < 0) {
                            v0 ^= -2080896;
                        label_20:
                            v9 = ((long)v0);
                            v0 = v3;
                        label_23:
                            v2_1 = v9;
                        }
                        else {
                            long v4 = ((long)v0);
                            v0 = v3 + 1;
                            v2_1 = (((long)v1[v3])) << 28 ^ v4;
                            v4 = 0;
                            if(v2_1 >= v4) {
                                v4 = 266354560;
                            }
                            else {
                                v6 = v0 + 1;
                                v2_1 ^= (((long)v1[v0])) << 35;
                                if(v2_1 < v4) {
                                    v0_1 = -34093383808L;
                                    goto label_63;
                                }
                                else {
                                    v0 = v6 + 1;
                                    v2_1 ^= (((long)v1[v6])) << 42;
                                    if(v2_1 >= v4) {
                                        v4 = 4363953127296L;
                                        goto label_53;
                                    }
                                    else {
                                        goto label_76;
                                    }
                                }

                                goto label_65;
                            }

                        label_53:
                            v2_1 ^= v4;
                            goto label_99;
                        label_76:
                            v6 = v0 + 1;
                            v2_1 ^= (((long)v1[v0])) << 49;
                            if(v2_1 < v4) {
                                v0_1 = -558586000294016L;
                            label_63:
                                v2_1 ^= v0_1;
                            }
                            else {
                                v0 = v6 + 1;
                                v2_1 = v2_1 ^ (((long)v1[v6])) << 56 ^ 71499008037633920L;
                                if(v2_1 < v4) {
                                    v6 = v0 + 1;
                                    if((((long)v1[v0])) >= v4) {
                                    }
                                    else {
                                        goto label_101;
                                    }
                                }
                                else {
                                    goto label_99;
                                }
                            }

                        label_65:
                            v0 = v6;
                        }
                    }
                }

            label_99:
                this.pos = v0;
                return v2_1;
            }
        }

    label_101:
        return ((zzga)this).zzck();
    }

    private final int zzco() {
        int v0 = this.pos;
        if(this.limit - v0 >= 4) {
            byte[] v1 = this.buffer;
            this.pos = v0 + 4;
            return (v1[v0 + 3] & 255) << 24 | (v1[v0] & 255 | (v1[v0 + 1] & 255) << 8 | (v1[v0 + 2] & 255) << 16);
        }

        throw zzhh.zzdz();
    }

    private final long zzcp() {
        int v0 = this.pos;
        int v2 = 8;
        if(this.limit - v0 >= v2) {
            byte[] v1 = this.buffer;
            this.pos = v0 + 8;
            return ((((long)v1[v0 + 7])) & 255) << 56 | ((((long)v1[v0])) & 255 | ((((long)v1[v0 + 1])) & 255) << v2 | ((((long)v1[v0 + 2])) & 255) << 16 | ((((long)v1[v0 + 3])) & 255) << 24 | ((((long)v1[v0 + 4])) & 255) << 32 | ((((long)v1[v0 + 5])) & 255) << 40 | ((((long)v1[v0 + 6])) & 255) << 48);
        }

        throw zzhh.zzdz();
    }

    private final void zzcq() {
        this.limit += this.zzoi;
        int v0 = this.limit - this.zzoj;
        if(v0 > this.zzol) {
            this.zzoi = v0 - this.zzol;
            this.limit -= this.zzoi;
            return;
        }

        this.zzoi = 0;
    }

    private final byte zzcr() {
        if(this.pos != this.limit) {
            byte[] v0 = this.buffer;
            int v1 = this.pos;
            this.pos = v1 + 1;
            return v0[v1];
        }

        throw zzhh.zzdz();
    }
}

