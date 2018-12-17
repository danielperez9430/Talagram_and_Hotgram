package com.google.android.gms.internal.wearable;

import java.util.Arrays;

public final class zzj extends zzn {
    public byte[] zzgd;
    public String zzge;
    public double zzgf;
    public float zzgg;
    public long zzgh;
    public int zzgi;
    public int zzgj;
    public boolean zzgk;
    public zzh[] zzgl;
    public zzi[] zzgm;
    public String[] zzgn;
    public long[] zzgo;
    public float[] zzgp;
    public long zzgq;

    public zzj() {
        super();
        this.zzgd = zzw.zzhy;
        this.zzge = "";
        this.zzgf = 0;
        this.zzgg = 0f;
        this.zzgh = 0;
        this.zzgi = 0;
        this.zzgj = 0;
        this.zzgk = false;
        this.zzgl = zzh.zzh();
        this.zzgm = zzi.zzi();
        this.zzgn = zzw.zzhw;
        this.zzgo = zzw.zzhs;
        this.zzgp = zzw.zzht;
        this.zzgq = 0;
        this.zzhc = null;
        this.zzhl = -1;
    }

    public final boolean equals(Object arg8) {
        if((((zzj)arg8)) == this) {
            return 1;
        }

        if(!(arg8 instanceof zzj)) {
            return 0;
        }

        if(!Arrays.equals(this.zzgd, ((zzj)arg8).zzgd)) {
            return 0;
        }

        if(this.zzge == null) {
            if(((zzj)arg8).zzge != null) {
                return 0;
            }
        }
        else if(!this.zzge.equals(((zzj)arg8).zzge)) {
            return 0;
        }

        if(Double.doubleToLongBits(this.zzgf) != Double.doubleToLongBits(((zzj)arg8).zzgf)) {
            return 0;
        }

        if(Float.floatToIntBits(this.zzgg) != Float.floatToIntBits(((zzj)arg8).zzgg)) {
            return 0;
        }

        if(this.zzgh != ((zzj)arg8).zzgh) {
            return 0;
        }

        if(this.zzgi != ((zzj)arg8).zzgi) {
            return 0;
        }

        if(this.zzgj != ((zzj)arg8).zzgj) {
            return 0;
        }

        if(this.zzgk != ((zzj)arg8).zzgk) {
            return 0;
        }

        if(!zzr.equals(this.zzgl, ((zzj)arg8).zzgl)) {
            return 0;
        }

        if(!zzr.equals(this.zzgm, ((zzj)arg8).zzgm)) {
            return 0;
        }

        if(!zzr.equals(this.zzgn, ((zzj)arg8).zzgn)) {
            return 0;
        }

        if(!zzr.equals(this.zzgo, ((zzj)arg8).zzgo)) {
            return 0;
        }

        if(!zzr.equals(this.zzgp, ((zzj)arg8).zzgp)) {
            return 0;
        }

        if(this.zzgq != ((zzj)arg8).zzgq) {
            return 0;
        }

        if(this.zzhc != null) {
            if(this.zzhc.isEmpty()) {
            }
            else {
                return this.zzhc.equals(((zzj)arg8).zzhc);
            }
        }

        if(((zzj)arg8).zzhc != null) {
            if(((zzj)arg8).zzhc.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((this.getClass().getName().hashCode() + 527) * 31 + Arrays.hashCode(this.zzgd)) * 31;
        int v2 = 0;
        int v1 = this.zzge == null ? 0 : this.zzge.hashCode();
        long v3 = Double.doubleToLongBits(this.zzgf);
        v0 = (v0 + v1) * 31;
        v1 = 32;
        v0 = (((((v0 + (((int)(v3 ^ v3 >>> v1)))) * 31 + Float.floatToIntBits(this.zzgg)) * 31 + (((int)(this.zzgh ^ this.zzgh >>> v1)))) * 31 + this.zzgi) * 31 + this.zzgj) * 31;
        int v3_1 = this.zzgk ? 1231 : 1237;
        v0 = (((((((v0 + v3_1) * 31 + zzr.hashCode(this.zzgl)) * 31 + zzr.hashCode(this.zzgm)) * 31 + zzr.hashCode(this.zzgn)) * 31 + zzr.hashCode(this.zzgo)) * 31 + zzr.hashCode(this.zzgp)) * 31 + (((int)(this.zzgq ^ this.zzgq >>> v1)))) * 31;
        if(this.zzhc != null) {
            if(this.zzhc.isEmpty()) {
            }
            else {
                v2 = this.zzhc.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzt zza(zzk arg7) {
        long[] v0_5;
        String[] v0_4;
        zzi[] v0_3;
        zzh[] v0_2;
        float[] v0_1;
        int v3;
        int v1_1;
        int v0;
        while(true) {
        label_0:
            v0 = arg7.zzj();
            boolean v1 = true;
            switch(v0) {
                case 0: {
                    return this;
                }
                case 10: {
                    goto label_233;
                }
                case 18: {
                    goto label_230;
                }
                case 25: {
                    goto label_226;
                }
                case 37: {
                    goto label_222;
                }
                case 40: {
                    goto label_219;
                }
                case 48: {
                    goto label_216;
                }
                case 56: {
                    goto label_209;
                }
                case 64: {
                    goto label_203;
                }
                case 74: {
                    goto label_172;
                }
                case 82: {
                    goto label_141;
                }
                case 90: {
                    goto label_116;
                }
                case 96: {
                    goto label_91;
                }
                case 98: {
                    goto label_61;
                }
                case 104: {
                    goto label_58;
                }
                case 114: {
                    goto label_34;
                }
                case 117: {
                    goto label_7;
                }
            }

            if(super.zza(arg7, v0)) {
                continue;
            }

            return this;
        label_34:
            v0 = arg7.zzk();
            v1_1 = arg7.zze(v0);
            v0 /= 4;
            v3 = this.zzgp == null ? 0 : this.zzgp.length;
            v0_1 = new float[v0 + v3];
            if(v3 != 0) {
                System.arraycopy(this.zzgp, 0, v0_1, 0, v3);
                goto label_48;
            label_226:
                this.zzgf = Double.longBitsToDouble(arg7.zzn());
                continue;
            label_230:
                this.zzge = arg7.readString();
                continue;
            label_7:
                v0 = zzw.zzb(arg7, 117);
                v3 = this.zzgp == null ? 0 : this.zzgp.length;
                v0_1 = new float[v0 + v3];
                if(v3 != 0) {
                    System.arraycopy(this.zzgp, 0, v0_1, 0, v3);
                    goto label_20;
                label_233:
                    this.zzgd = arg7.readBytes();
                    continue;
                label_203:
                    if(arg7.zzk() != 0) {
                    }
                    else {
                        v1 = false;
                    }

                    this.zzgk = v1;
                    continue;
                    return this;
                label_172:
                    v0 = zzw.zzb(arg7, 74);
                    v3 = this.zzgl == null ? 0 : this.zzgl.length;
                    v0_2 = new zzh[v0 + v3];
                    if(v3 != 0) {
                        System.arraycopy(this.zzgl, 0, v0_2, 0, v3);
                        break;
                    label_141:
                        v0 = zzw.zzb(arg7, 82);
                        v3 = this.zzgm == null ? 0 : this.zzgm.length;
                        v0_3 = new zzi[v0 + v3];
                        if(v3 != 0) {
                            System.arraycopy(this.zzgm, 0, v0_3, 0, v3);
                            goto label_154;
                        label_209:
                            v0 = arg7.zzk();
                            this.zzgj = -(v0 & 1) ^ v0 >>> 1;
                            continue;
                        label_116:
                            v0 = zzw.zzb(arg7, 90);
                            v3 = this.zzgn == null ? 0 : this.zzgn.length;
                            v0_4 = new String[v0 + v3];
                            if(v3 != 0) {
                                System.arraycopy(this.zzgn, 0, v0_4, 0, v3);
                                goto label_129;
                            label_216:
                                this.zzgi = arg7.zzk();
                                continue;
                            label_58:
                                this.zzgq = arg7.zzl();
                                continue;
                            label_91:
                                v0 = zzw.zzb(arg7, 96);
                                v3 = this.zzgo == null ? 0 : this.zzgo.length;
                                v0_5 = new long[v0 + v3];
                                if(v3 != 0) {
                                    System.arraycopy(this.zzgo, 0, v0_5, 0, v3);
                                    goto label_104;
                                label_219:
                                    this.zzgh = arg7.zzl();
                                    continue;
                                label_61:
                                    v0 = arg7.zze(arg7.zzk());
                                    v1_1 = arg7.getPosition();
                                    v3 = 0;
                                    goto label_65;
                                label_222:
                                    this.zzgg = Float.intBitsToFloat(arg7.zzm());
                                    continue;
                                }

                                goto label_104;
                            }

                            goto label_129;
                        }

                        goto label_154;
                    }

                    break;
                }

                goto label_20;
            }

            goto label_48;
        }

        while(v3 < v0_2.length - 1) {
            v0_2[v3] = new zzh();
            arg7.zza(v0_2[v3]);
            arg7.zzj();
            ++v3;
        }

        v0_2[v3] = new zzh();
        arg7.zza(v0_2[v3]);
        this.zzgl = v0_2;
        goto label_0;
    label_154:
        while(v3 < v0_3.length - 1) {
            v0_3[v3] = new zzi();
            arg7.zza(v0_3[v3]);
            arg7.zzj();
            ++v3;
        }

        v0_3[v3] = new zzi();
        arg7.zza(v0_3[v3]);
        this.zzgm = v0_3;
        goto label_0;
    label_129:
        while(v3 < v0_4.length - 1) {
            v0_4[v3] = arg7.readString();
            arg7.zzj();
            ++v3;
        }

        v0_4[v3] = arg7.readString();
        this.zzgn = v0_4;
        goto label_0;
    label_104:
        while(v3 < v0_5.length - 1) {
            v0_5[v3] = arg7.zzl();
            arg7.zzj();
            ++v3;
        }

        v0_5[v3] = arg7.zzl();
        this.zzgo = v0_5;
        goto label_0;
    label_65:
        while(arg7.zzp() > 0) {
            arg7.zzl();
            ++v3;
        }

        arg7.zzg(v1_1);
        v1_1 = this.zzgo == null ? 0 : this.zzgo.length;
        long[] v3_1 = new long[v3 + v1_1];
        if(v1_1 != 0) {
            System.arraycopy(this.zzgo, 0, v3_1, 0, v1_1);
        }

        while(v1_1 < v3_1.length) {
            v3_1[v1_1] = arg7.zzl();
            ++v1_1;
        }

        this.zzgo = v3_1;
        arg7.zzf(v0);
        goto label_0;
    label_48:
        while(v3 < v0_1.length) {
            v0_1[v3] = Float.intBitsToFloat(arg7.zzm());
            ++v3;
        }

        this.zzgp = v0_1;
        arg7.zzf(v1_1);
        goto label_0;
    label_20:
        while(v3 < v0_1.length - 1) {
            v0_1[v3] = Float.intBitsToFloat(arg7.zzm());
            arg7.zzj();
            ++v3;
        }

        v0_1[v3] = Float.intBitsToFloat(arg7.zzm());
        this.zzgp = v0_1;
        goto label_0;
    }

    public final void zza(zzl arg9) {
        int v0_2;
        int v1 = 2;
        if(!Arrays.equals(this.zzgd, zzw.zzhy)) {
            byte[] v0 = this.zzgd;
            arg9.zzf(1, v1);
            arg9.zzl(v0.length);
            arg9.zzc(v0);
        }

        if(this.zzge != null && !this.zzge.equals("")) {
            arg9.zza(v1, this.zzge);
        }

        if(Double.doubleToLongBits(this.zzgf) != Double.doubleToLongBits(0)) {
            double v0_1 = this.zzgf;
            arg9.zzf(3, 1);
            arg9.zzb(Double.doubleToLongBits(v0_1));
        }

        if(Float.floatToIntBits(this.zzgg) != Float.floatToIntBits(0f)) {
            arg9.zza(4, this.zzgg);
        }

        long v2 = 0;
        if(this.zzgh != v2) {
            arg9.zza(5, this.zzgh);
        }

        if(this.zzgi != 0) {
            arg9.zzd(6, this.zzgi);
        }

        v1 = 0;
        if(this.zzgj != 0) {
            v0_2 = this.zzgj;
            arg9.zzf(7, 0);
            arg9.zzl(zzl.zzn(v0_2));
        }

        if(this.zzgk) {
            boolean v0_3 = this.zzgk;
            arg9.zzf(8, 0);
            arg9.zza(((byte)(((int)v0_3))));
        }

        if(this.zzgl != null && this.zzgl.length > 0) {
            for(v0_2 = 0; v0_2 < this.zzgl.length; ++v0_2) {
                zzh v4 = this.zzgl[v0_2];
                if(v4 != null) {
                    arg9.zza(9, ((zzt)v4));
                }
            }
        }

        if(this.zzgm != null && this.zzgm.length > 0) {
            for(v0_2 = 0; v0_2 < this.zzgm.length; ++v0_2) {
                zzi v4_1 = this.zzgm[v0_2];
                if(v4_1 != null) {
                    arg9.zza(10, ((zzt)v4_1));
                }
            }
        }

        if(this.zzgn != null && this.zzgn.length > 0) {
            for(v0_2 = 0; v0_2 < this.zzgn.length; ++v0_2) {
                String v4_2 = this.zzgn[v0_2];
                if(v4_2 != null) {
                    arg9.zza(11, v4_2);
                }
            }
        }

        if(this.zzgo != null && this.zzgo.length > 0) {
            for(v0_2 = 0; v0_2 < this.zzgo.length; ++v0_2) {
                arg9.zza(12, this.zzgo[v0_2]);
            }
        }

        if(this.zzgq != v2) {
            arg9.zza(13, this.zzgq);
        }

        if(this.zzgp != null && this.zzgp.length > 0) {
            while(v1 < this.zzgp.length) {
                arg9.zza(14, this.zzgp[v1]);
                ++v1;
            }
        }

        super.zza(arg9);
    }

    protected final int zzg() {
        // Method was not decompiled
    }
}

