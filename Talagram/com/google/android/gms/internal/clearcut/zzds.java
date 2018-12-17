package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import sun.misc.Unsafe;

final class zzds implements zzef {
    private static final Unsafe zzmh;
    private final int[] zzmi;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final zzdo zzmn;
    private final boolean zzmo;
    private final boolean zzmp;
    private final boolean zzmq;
    private final boolean zzmr;
    private final int[] zzms;
    private final int[] zzmt;
    private final int[] zzmu;
    private final zzdw zzmv;
    private final zzcy zzmw;
    private final zzex zzmx;
    private final zzbu zzmy;
    private final zzdj zzmz;

    static {
        zzds.zzmh = zzfd.zzef();
    }

    private zzds(int[] arg6, Object[] arg7, int arg8, int arg9, int arg10, zzdo arg11, boolean arg12, boolean arg13, int[] arg14, int[] arg15, int[] arg16, zzdw arg17, zzcy arg18, zzex arg19, zzbu arg20, zzdj arg21) {
        zzds v0 = this;
        zzdo v1 = arg11;
        zzbu v2 = arg20;
        super();
        v0.zzmi = arg6;
        v0.zzmj = arg7;
        v0.zzmk = arg8;
        v0.zzml = arg9;
        v0.zzmm = arg10;
        v0.zzmp = v1 instanceof zzcg;
        v0.zzmq = arg12;
        boolean v4 = v2 == null || !v2.zze(arg11) ? false : true;
        v0.zzmo = v4;
        v0.zzmr = false;
        v0.zzms = arg14;
        v0.zzmt = arg15;
        v0.zzmu = arg16;
        v0.zzmv = arg17;
        v0.zzmw = arg18;
        v0.zzmx = arg19;
        v0.zzmy = v2;
        v0.zzmn = v1;
        v0.zzmz = arg21;
    }

    public final boolean equals(Object arg10, Object arg11) {
        int v0 = this.zzmi.length;
        int v2;
        for(v2 = 0; true; v2 += 4) {
            boolean v3 = true;
            if(v2 >= v0) {
                break;
            }

            int v4 = this.zzag(v2);
            int v5 = 1048575;
            long v6 = ((long)(v4 & v5));
            switch((v4 & 267386880) >>> 20) {
                case 0: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzk(arg10, v6) == zzfd.zzk(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 1: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzj(arg10, v6) == zzfd.zzj(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 2: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzk(arg10, v6) == zzfd.zzk(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 3: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzk(arg10, v6) == zzfd.zzk(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 4: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzj(arg10, v6) == zzfd.zzj(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 5: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzk(arg10, v6) == zzfd.zzk(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 6: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzj(arg10, v6) == zzfd.zzj(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 7: {
                    if((this.zzc(arg10, arg11, v2)) && zzfd.zzl(arg10, v6) == zzfd.zzl(arg11, v6)) {
                        goto label_142;
                    }

                label_141:
                    v3 = false;
                    break;
                }
                case 8: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzeh.zzd(zzfd.zzo(arg10, v6), zzfd.zzo(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 9: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzeh.zzd(zzfd.zzo(arg10, v6), zzfd.zzo(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 10: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzeh.zzd(zzfd.zzo(arg10, v6), zzfd.zzo(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 11: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzj(arg10, v6) == zzfd.zzj(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 12: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzj(arg10, v6) == zzfd.zzj(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 13: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzj(arg10, v6) == zzfd.zzj(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 14: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzk(arg10, v6) == zzfd.zzk(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 15: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzj(arg10, v6) == zzfd.zzj(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 16: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzfd.zzk(arg10, v6) == zzfd.zzk(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 17: {
                    if(!this.zzc(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzeh.zzd(zzfd.zzo(arg10, v6), zzfd.zzo(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 31: 
                case 32: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 47: 
                case 48: 
                case 49: 
                case 50: {
                    v3 = zzeh.zzd(zzfd.zzo(arg10, v6), zzfd.zzo(arg11, v6));
                    break;
                }
                case 51: 
                case 52: 
                case 53: 
                case 54: 
                case 55: 
                case 56: 
                case 57: 
                case 58: 
                case 59: 
                case 60: 
                case 61: 
                case 62: 
                case 63: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: {
                    long v4_1 = ((long)(this.zzah(v2) & v5));
                    if(zzfd.zzj(arg10, v4_1) != zzfd.zzj(arg11, v4_1)) {
                        goto label_141;
                    }

                    if(zzeh.zzd(zzfd.zzo(arg10, v6), zzfd.zzo(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                default: {
                    break;
                }
            }

        label_142:
            if(!v3) {
                return 0;
            }
        }

        if(!this.zzmx.zzq(arg10).equals(this.zzmx.zzq(arg11))) {
            return 0;
        }

        if(this.zzmo) {
            return this.zzmy.zza(arg10).equals(this.zzmy.zza(arg11));
        }

        return 1;
    }

    public final int hashCode(Object arg9) {
        int v0 = this.zzmi.length;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            int v3 = this.zzag(v1);
            int v4 = this.zzmi[v1];
            long v5 = ((long)(1048575 & v3));
            int v7 = 37;
            switch((v3 & 267386880) >>> 20) {
                case 0: {
                    goto label_114;
                }
                case 1: {
                    goto label_110;
                }
                case 7: {
                    goto label_100;
                }
                case 8: {
                    goto label_96;
                }
                case 9: {
                    goto label_90;
                }
                case 4: 
                case 6: 
                case 11: 
                case 12: 
                case 13: 
                case 15: {
                    goto label_104;
                }
                case 2: 
                case 3: 
                case 5: 
                case 14: 
                case 16: {
                    goto label_107;
                }
                case 17: {
                    goto label_83;
                }
                case 10: 
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 31: 
                case 32: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 47: 
                case 48: 
                case 49: 
                case 50: {
                    goto label_86;
                }
                case 51: {
                    goto label_78;
                }
                case 52: {
                    goto label_73;
                }
                case 53: {
                    goto label_68;
                }
                case 54: {
                    goto label_65;
                }
                case 55: {
                    goto label_60;
                }
                case 56: {
                    goto label_57;
                }
                case 57: {
                    goto label_54;
                }
                case 58: {
                    goto label_49;
                }
                case 59: {
                    goto label_46;
                }
                case 60: {
                    goto label_41;
                }
                case 61: {
                    goto label_38;
                }
                case 62: {
                    goto label_35;
                }
                case 63: {
                    goto label_32;
                }
                case 64: {
                    goto label_29;
                }
                case 65: {
                    goto label_26;
                }
                case 66: {
                    goto label_23;
                }
                case 67: {
                    goto label_20;
                }
                case 68: {
                    goto label_17;
                }
            }

            goto label_119;
        label_100:
            v2 *= 53;
            boolean v3_1 = zzfd.zzl(arg9, v5);
            goto label_102;
        label_104:
            v2 *= 53;
            v3 = zzfd.zzj(arg9, v5);
            goto label_118;
        label_107:
            v2 *= 53;
            long v3_2 = zzfd.zzk(arg9, v5);
            goto label_117;
        label_110:
            v2 *= 53;
            float v3_3 = zzfd.zzm(arg9, v5);
            goto label_112;
        label_114:
            v2 *= 53;
            double v3_4 = zzfd.zzn(arg9, v5);
            goto label_116;
        label_17:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_43;
        label_20:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_23:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_26:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_29:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_32:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_35:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_38:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_86;
        label_41:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

        label_43:
            Object v3_5 = zzfd.zzo(arg9, v5);
            v2 *= 53;
            goto label_88;
        label_46:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_96;
        label_49:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            v2 *= 53;
            v3_1 = zzds.zzi(arg9, v5);
        label_102:
            v3 = zzci.zzc(v3_1);
            goto label_118;
        label_54:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_57:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_60:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

        label_62:
            v2 *= 53;
            v3 = zzds.zzg(arg9, v5);
            goto label_118;
        label_65:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_68:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

        label_70:
            v2 *= 53;
            v3_2 = zzds.zzh(arg9, v5);
            goto label_117;
        label_73:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            v2 *= 53;
            v3_3 = zzds.zzf(arg9, v5);
        label_112:
            v3 = Float.floatToIntBits(v3_3);
            goto label_118;
        label_78:
            if(!this.zza(arg9, v4, v1)) {
                goto label_119;
            }

            v2 *= 53;
            v3_4 = zzds.zze(arg9, v5);
        label_116:
            v3_2 = Double.doubleToLongBits(v3_4);
        label_117:
            v3 = zzci.zzl(v3_2);
            goto label_118;
        label_83:
            v3_5 = zzfd.zzo(arg9, v5);
            if(v3_5 == null) {
                goto label_93;
            }

            goto label_92;
        label_86:
            v2 *= 53;
            v3_5 = zzfd.zzo(arg9, v5);
        label_88:
            v3 = v3_5.hashCode();
            goto label_118;
        label_90:
            v3_5 = zzfd.zzo(arg9, v5);
            if(v3_5 != null) {
            label_92:
                v7 = v3_5.hashCode();
            }

        label_93:
            v2 = v2 * 53 + v7;
            goto label_119;
        label_96:
            v2 *= 53;
            v3 = zzfd.zzo(arg9, v5).hashCode();
        label_118:
            v2 += v3;
        label_119:
            v1 += 4;
        }

        v2 = v2 * 53 + this.zzmx.zzq(arg9).hashCode();
        if(this.zzmo) {
            v2 = v2 * 53 + this.zzmy.zza(arg9).hashCode();
        }

        return v2;
    }

    public final Object newInstance() {
        return this.zzmv.newInstance(this.zzmn);
    }

    static zzds zza(Class arg22, zzdm arg23, zzdw arg24, zzcy arg25, zzex arg26, zzbu arg27, zzdj arg28) {
        int v14;
        int v2;
        int v9;
        int v8;
        int v5;
        zzdm v0 = arg23;
        if((v0 instanceof zzec)) {
            boolean v12 = ((zzec)v0).zzcf() == zzg.zzkm ? true : false;
            if(((zzec)v0).getFieldCount() == 0) {
                v5 = 0;
                v8 = 0;
                v9 = 0;
            }
            else {
                int v1 = ((zzec)v0).zzcp();
                v2 = ((zzec)v0).zzcq();
                v5 = ((zzec)v0).zzcu();
                v8 = v1;
                v9 = v2;
            }

            int[] v6 = new int[v5 << 2];
            Object[] v7 = new Object[v5 << 1];
            int[] v2_1 = null;
            int[] v15 = ((zzec)v0).zzcr() > 0 ? new int[((zzec)v0).zzcr()] : v2_1;
            int[] v16 = ((zzec)v0).zzcs() > 0 ? new int[((zzec)v0).zzcs()] : v2_1;
            zzed v1_1 = ((zzec)v0).zzco();
            if(v1_1.next()) {
                v2 = v1_1.zzcx();
                v5 = 0;
                int v10 = 0;
                int v11 = 0;
                while(true) {
                    if(v2 >= ((zzec)v0).zzcv() || v5 >= v2 - v8 << 2) {
                        if(v1_1.zzda()) {
                            v2 = ((int)zzfd.zza(v1_1.zzdb()));
                            v13 = ((int)zzfd.zza(v1_1.zzdc()));
                            goto label_69;
                        }
                        else {
                            v2 = ((int)zzfd.zza(v1_1.zzdd()));
                            if(v1_1.zzde()) {
                                v13 = ((int)zzfd.zza(v1_1.zzdf()));
                                v14 = v1_1.zzdg();
                            }
                            else {
                                v13 = 0;
                            label_69:
                                v14 = 0;
                            }
                        }

                        v6[v5] = v1_1.zzcx();
                        int v17 = v5 + 1;
                        int v18 = v1_1.zzdi() ? 536870912 : 0;
                        int v19 = v1_1.zzdh() ? 268435456 : 0;
                        v6[v17] = v18 | v19 | v1_1.zzcy() << 20 | v2;
                        v6[v5 + 2] = v13 | v14 << 20;
                        if(v1_1.zzdl() != null) {
                            v2 = v5 / 4 << 1;
                            v7[v2] = v1_1.zzdl();
                            if(v1_1.zzdj() != null) {
                                v7[v2 + 1] = v1_1.zzdj();
                            }
                            else if(v1_1.zzdk() != null) {
                                v7[v2 + 1] = v1_1.zzdk();
                            }
                        }
                        else if(v1_1.zzdj() != null) {
                            v7[(v5 / 4 << 1) + 1] = v1_1.zzdj();
                        }
                        else if(v1_1.zzdk() != null) {
                            v7[(v5 / 4 << 1) + 1] = v1_1.zzdk();
                        }

                        v2 = v1_1.zzcy();
                        if(v2 == zzcb.zziw.ordinal()) {
                            v15[v10] = v5;
                            ++v10;
                        }
                        else if(v2 >= 18 && v2 <= 49) {
                            v16[v11] = v6[v17] & 1048575;
                            ++v11;
                        }

                        if(!v1_1.next()) {
                            break;
                        }

                        v2 = v1_1.zzcx();
                    }
                    else {
                        int v13;
                        for(v13 = 0; v13 < 4; ++v13) {
                            v6[v5 + v13] = -1;
                        }
                    }

                    v5 += 4;
                }
            }

            return new zzds(v6, v7, v8, v9, ((zzec)v0).zzcv(), ((zzec)v0).zzch(), v12, false, ((zzec)v0).zzct(), v15, v16, arg24, arg25, arg26, arg27, arg28);
        }

        ((zzes)v0).zzcf();
        throw new NoSuchMethodError();
    }

    private static int zza(int arg6, byte[] arg7, int arg8, int arg9, Object arg10, zzay arg11) {
        return zzax.zza(arg6, arg7, arg8, arg9, zzds.zzn(arg10), arg11);
    }

    private static int zza(zzef arg2, int arg3, byte[] arg4, int arg5, int arg6, zzcn arg7, zzay arg8) {
        arg5 = zzds.zza(arg2, arg4, arg5, arg6, arg8);
        while(true) {
            arg7.add(arg8.zzff);
            if(arg5 < arg6) {
                int v0 = zzax.zza(arg4, arg5, arg8);
                if(arg3 == arg8.zzfd) {
                    arg5 = zzds.zza(arg2, arg4, v0, arg6, arg8);
                    continue;
                }
            }

            return arg5;
        }

        return arg5;
    }

    private static int zza(zzef arg6, byte[] arg7, int arg8, int arg9, zzay arg10) {
        int v0 = arg8 + 1;
        arg8 = arg7[arg8];
        if(arg8 < 0) {
            v0 = zzax.zza(arg8, arg7, v0, arg10);
            arg8 = arg10.zzfd;
        }

        int v3 = v0;
        if(arg8 >= 0 && arg8 <= arg9 - v3) {
            Object v9 = arg6.newInstance();
            arg8 += v3;
            arg6.zza(v9, arg7, v3, arg8, arg10);
            arg6.zzc(v9);
            arg10.zzff = v9;
            return arg8;
        }

        throw zzco.zzbl();
    }

    private static int zza(zzef arg8, byte[] arg9, int arg10, int arg11, int arg12, zzay arg13) {
        Object v7 = ((zzds)arg8).newInstance();
        int v9 = arg8.zza(v7, arg9, arg10, arg11, arg12, arg13);
        ((zzds)arg8).zzc(v7);
        arg13.zzff = v7;
        return v9;
    }

    private final int zza(Object arg32, byte[] arg33, int arg34, int arg35, int arg36, zzay arg37) {
        Object v10_1;
        int v9_1;
        int v15_1;
        int v30;
        zzcn v0_3;
        Object v0_2;
        Unsafe v29;
        long v7_1;
        int v24;
        int v22;
        int v18;
        int v2;
        int v5;
        int v4;
        int v17;
        zzds v15 = this;
        Object v14 = arg32;
        byte[] v12 = arg33;
        int v13 = arg35;
        int v11 = arg36;
        zzay v9 = arg37;
        Unsafe v10 = zzds.zzmh;
        int v8 = -1;
        int v0 = arg34;
        int v1 = 0;
        int v6 = 0;
        int v7 = -1;
        while(true) {
            v17 = 1048575;
            if(v0 >= v13) {
                break;
            }

            v1 = v0 + 1;
            v0 = v12[v0];
            if(v0 < 0) {
                v4 = zzax.zza(v0, v12, v1, v9);
                v5 = v9.zzfd;
            }
            else {
                v5 = v0;
                v4 = v1;
            }

            int v3 = v5 >>> 3;
            v2 = v5 & 7;
            v1 = v15.zzai(v3);
            if(v1 != v8) {
                v8 = (v15.zzmi[v1 + 1] & 267386880) >>> 20;
                int v20 = v5;
                long v11_1 = ((long)(v15.zzmi[v1 + 1] & v17));
                int v21 = v15.zzmi[v1 + 1];
                if(v8 <= 17) {
                    v18 = 1 << (v15.zzmi[v1 + 2] >>> 20);
                    v5 = v15.zzmi[v1 + 2] & v17;
                    if(v5 != v7) {
                        if(v7 != -1) {
                            v22 = v1;
                            v10.putInt(v14, ((long)v7), v6);
                        }
                        else {
                            v22 = v1;
                        }

                        v6 = v10.getInt(v14, ((long)v5));
                        v7 = v5;
                    }
                    else {
                        v22 = v1;
                    }

                    v0 = 5;
                    switch(v8) {
                        case 0: {
                            goto label_300;
                        }
                        case 1: {
                            goto label_288;
                        }
                        case 2: 
                        case 3: {
                            goto label_271;
                        }
                        case 7: {
                            goto label_213;
                        }
                        case 8: {
                            goto label_196;
                        }
                        case 9: {
                            goto label_174;
                        }
                        case 10: {
                            goto label_154;
                        }
                        case 4: 
                        case 11: {
                            goto label_259;
                        }
                        case 12: {
                            goto label_125;
                        }
                        case 6: 
                        case 13: {
                            goto label_229;
                        }
                        case 5: 
                        case 14: {
                            goto label_240;
                        }
                        case 15: {
                            goto label_116;
                        }
                        case 16: {
                            goto label_100;
                        }
                        case 17: {
                            goto label_72;
                        }
                    }

                    goto label_65;
                label_259:
                    v5 = v4;
                    v24 = v7;
                    v7_1 = v11_1;
                    int v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 != 0) {
                        goto label_318;
                    }

                    v0 = zzax.zza(v12, v5, v9);
                    v10.putInt(v14, v7_1, v9.zzfd);
                    goto label_312;
                label_196:
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 != 2) {
                        goto label_257;
                    }

                    v0 = (v21 & 536870912) == 0 ? zzax.zzc(v12, v4, v9) : zzax.zzd(v12, v4, v9);
                    Object v1_1 = v9.zzff;
                    goto label_211;
                label_100:
                    v8 = v20;
                    if(v2 != 0) {
                        goto label_169;
                    }

                    long v2_1 = v11_1;
                    v12 = arg33;
                    v11 = zzax.zzb(v12, v4, v9);
                    v10.putLong(arg32, v2_1, zzbk.zza(v9.zzfe));
                    v6 |= v18;
                    v1 = v8;
                    v0 = v11;
                    goto label_165;
                label_229:
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 != v0) {
                        goto label_257;
                    }

                    v10.putInt(v14, v7_1, zzax.zzc(v12, v4));
                    v0 = v4 + 4;
                    goto label_312;
                label_72:
                    if(v2 == 3) {
                        v8 = v20;
                        v0 = zzds.zza(v15.zzad(v22), arg33, v4, arg35, v3 << 3 | 4, arg37);
                        v1_1 = (v6 & v18) == 0 ? v9.zzff : zzci.zza(v10.getObject(v14, v11_1), v9.zzff);
                        v10.putObject(v14, v11_1, v1_1);
                        v6 |= v18;
                        v1 = v8;
                        v8 = -1;
                        v11 = arg36;
                        v12 = arg33;
                        goto label_167;
                    }

                label_65:
                    v5 = v4;
                    v24 = v7;
                    v25 = v20;
                    goto label_318;
                label_300:
                    v5 = v4;
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 != 1) {
                        goto label_318;
                    }

                    zzfd.zza(v14, v7_1, zzax.zze(v12, v5));
                    v0 = v5 + 8;
                    goto label_312;
                label_174:
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v1 = v22;
                    v12 = arg33;
                    if(v2 != 2) {
                        goto label_257;
                    }

                    v13 = arg35;
                    v0 = zzds.zza(v15.zzad(v1), v12, v4, v13, v9);
                    v1_1 = (v6 & v18) == 0 ? v9.zzff : zzci.zza(v10.getObject(v14, v7_1), v9.zzff);
                label_211:
                    v10.putObject(v14, v7_1, v1_1);
                    goto label_312;
                label_271:
                    v5 = v4;
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 != 0) {
                        goto label_318;
                    }

                    v17 = zzax.zzb(v12, v5, v9);
                    v10.putLong(arg32, v7_1, v9.zzfe);
                    v6 |= v18;
                    v0 = v17;
                    goto label_313;
                label_240:
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 != 1) {
                        goto label_257;
                    }

                    v10.putLong(arg32, v7_1, zzax.zzd(v12, v4));
                    v0 = v4 + 8;
                    goto label_312;
                label_116:
                    long v0_1 = v11_1;
                    v8 = v20;
                    v12 = arg33;
                    if(v2 != 0) {
                        goto label_169;
                    }

                    v2 = zzax.zza(v12, v4, v9);
                    v10.putInt(v14, v0_1, zzbk.zzm(v9.zzfd));
                    goto label_162;
                label_213:
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 == 0) {
                        v0 = zzax.zzb(v12, v4, v9);
                        boolean v1_2 = v9.zzfe != 0 ? true : false;
                        zzfd.zza(v14, v7_1, v1_2);
                        goto label_312;
                    }

                label_257:
                    v5 = v4;
                    goto label_318;
                label_154:
                    v0_1 = v11_1;
                    v8 = v20;
                    v12 = arg33;
                    if(v2 != 2) {
                        goto label_169;
                    }

                    v2 = zzax.zze(v12, v4, v9);
                    v10.putObject(v14, v0_1, v9.zzff);
                label_162:
                    v6 |= v18;
                    v0 = v2;
                    goto label_164;
                label_125:
                    long v13_1 = v11_1;
                    v8 = v20;
                    v1 = v22;
                    v12 = arg33;
                    if(v2 == 0) {
                        v0 = zzax.zza(v12, v4, v9);
                        v2 = v9.zzfd;
                        zzck v1_3 = v15.zzaf(v1);
                        if(v1_3 != null) {
                            if(v1_3.zzb(v2) != null) {
                            }
                            else {
                                zzds.zzn(arg32).zzb(v8, Long.valueOf(((long)v2)));
                                v1 = v8;
                                v8 = -1;
                                v11 = arg36;
                                v13 = arg35;
                                v14 = arg32;
                                continue;
                            }
                        }

                        long v3_1 = v13_1;
                        v14 = arg32;
                        v10.putInt(v14, v3_1, v2);
                        v6 |= v18;
                    label_164:
                        v1 = v8;
                    label_165:
                        v8 = -1;
                        v11 = arg36;
                    label_167:
                        v13 = arg35;
                        continue;
                    }

                label_169:
                    v5 = v4;
                    v24 = v7;
                    v25 = v8;
                    goto label_318;
                label_288:
                    v5 = v4;
                    v24 = v7;
                    v7_1 = v11_1;
                    v25 = v20;
                    v12 = arg33;
                    v13 = arg35;
                    if(v2 == v0) {
                        zzfd.zza(v14, v7_1, zzax.zzf(v12, v5));
                        v0 = v5 + 4;
                    label_312:
                        v6 |= v18;
                    label_313:
                        v7 = v24;
                        v1 = v25;
                        goto label_315;
                    }

                label_318:
                    v2 = v5;
                    v18 = v6;
                    v29 = v10;
                    v6 = v25;
                    goto label_453;
                }
                else {
                    v5 = v4;
                    v24 = v7;
                    v0 = v8;
                    v7_1 = v11_1;
                    v4 = v20;
                    v12 = arg33;
                    if(v0 != 27) {
                        goto label_362;
                    }

                    if(v2 != 2) {
                        goto label_358;
                    }

                    v0_2 = v10.getObject(v14, v7_1);
                    if(!((zzcn)v0_2).zzu()) {
                        v2 = ((zzcn)v0_2).size();
                        if(v2 == 0) {
                            v2 = 10;
                        }
                        else {
                            v2 <<= 1;
                        }

                        v0_3 = ((zzcn)v0_2).zzi(v2);
                        v10.putObject(v14, v7_1, v0_3);
                    }

                    v0 = zzds.zza(v15.zzad(v1), v4, arg33, v5, arg35, v0_3, arg37);
                    v1 = v4;
                    v6 = v6;
                    v7 = v24;
                }

            label_315:
                v8 = -1;
                v11 = arg36;
                continue;
            label_358:
                v18 = v6;
                v30 = v4;
                v15_1 = v5;
                goto label_450;
            label_362:
                v11 = v4;
                v18 = v6;
                if(v0 <= 49) {
                    v29 = v10;
                    v30 = v11;
                    v0 = this.zza(arg32, arg33, v5, arg35, v11, v3, v2, v1, ((long)v21), v0, v7_1, arg37);
                    if(v0 != v5) {
                        goto label_395;
                    }

                    goto label_393;
                }
                else {
                    v20 = v1;
                    int v27 = v2;
                    v15_1 = v5;
                    long v22_1 = v7_1;
                    v29 = v10;
                    v30 = v11;
                    v4 = v21;
                    v21 = v3;
                    v9_1 = v0;
                    if(v9_1 != 50) {
                        v0 = this.zza(arg32, arg33, v15_1, arg35, v30, v21, v27, v4, v9_1, v22_1, v20, arg37);
                        if(v0 == v15_1) {
                        label_393:
                            v2 = v0;
                            goto label_452;
                        }
                    }
                    else if(v27 == 2) {
                        v0 = this.zza(arg32, arg33, v15_1, arg35, v20, v21, v22_1, arg37);
                        if(v0 == v15_1) {
                            goto label_393;
                        }
                    }
                    else {
                        goto label_451;
                    }
                }

            label_395:
                v12 = arg33;
                v9 = arg37;
                v6 = v18;
                v7 = v24;
                v10 = v29;
                v1 = v30;
                v8 = -1;
                v11 = arg36;
            }
            else {
                v15_1 = v4;
                v30 = v5;
                v18 = v6;
                v24 = v7;
            label_450:
                v29 = v10;
            label_451:
                v2 = v15_1;
            label_452:
                v6 = v30;
            label_453:
                v7 = arg36;
                if(v6 == v7) {
                    if(v7 == 0) {
                    }
                    else {
                        v8 = v2;
                        v9_1 = v6;
                        goto label_484;
                    }
                }

                v0 = zzds.zza(v6, arg33, v2, arg35, arg32, arg37);
                v12 = arg33;
                v9 = arg37;
                v1 = v6;
                v11 = v7;
                v6 = v18;
                v7 = v24;
                v10 = v29;
                v8 = -1;
            }

            v13 = arg35;
            v14 = arg32;
            v15 = this;
        }

        v18 = v6;
        v24 = v7;
        v29 = v10;
        v7 = v11;
        v8 = v0;
        v9_1 = v1;
    label_484:
        v1 = v18;
        v0 = v24;
        if(v0 != -1) {
            v10_1 = arg32;
            v29.putInt(v10_1, ((long)v0), v1);
        }
        else {
            v10_1 = arg32;
        }

        zzds v11_2 = this;
        if(v11_2.zzmt != null) {
            int[] v12_1 = v11_2.zzmt;
            v13 = v12_1.length;
            Object v5_1 = null;
            int v14_1;
            for(v14_1 = 0; v14_1 < v13; ++v14_1) {
                v1 = v12_1[v14_1];
                zzex v6_1 = v11_2.zzmx;
                v2 = v11_2.zzmi[v1];
                v0_2 = zzfd.zzo(v10_1, ((long)(v11_2.zzag(v1) & v17)));
                if(v0_2 == null) {
                }
                else {
                    zzck v4_1 = v11_2.zzaf(v1);
                    if(v4_1 == null) {
                    }
                    else {
                        v5_1 = this.zza(v1, v2, v11_2.zzmz.zzg(v0_2), v4_1, v5_1, v6_1);
                    }
                }
            }

            if(v5_1 == null) {
                goto label_525;
            }

            v11_2.zzmx.zzf(v10_1, v5_1);
        }

    label_525:
        if(v7 != 0) {
            if(v8 <= arg35 && v9_1 == v7) {
                return v8;
            }

            goto label_535;
        }
        else if(v8 == arg35) {
        }
        else {
            throw zzco.zzbo();
        }

        return v8;
    label_535:
        throw zzco.zzbo();
    }

    private static int zza(zzex arg0, Object arg1) {
        return arg0.zzm(arg0.zzq(arg1));
    }

    private final int zza(Object arg17, byte[] arg18, int arg19, int arg20, int arg21, int arg22, int arg23, int arg24, int arg25, long arg26, int arg28, zzay arg29) {
        Object v3_6;
        Object v15_1;
        Long v3_4;
        long v3_3;
        int v3_1;
        Long v2_1;
        zzds v0 = this;
        Object v1 = arg17;
        byte[] v3 = arg18;
        int v4 = arg19;
        int v2 = arg21;
        int v8 = arg22;
        int v5 = arg23;
        long v9 = arg26;
        int v6 = arg28;
        zzay v11 = arg29;
        Unsafe v12 = zzds.zzmh;
        long v13 = ((long)(v0.zzmi[v6 + 2] & 1048575));
        int v7 = 5;
        int v15 = 2;
        switch(arg25) {
            case 51: {
                if(v5 != 1) {
                    return v4;
                }

                Double v2_3 = Double.valueOf(zzax.zze(arg18, arg19));
            label_156:
                v12.putObject(v1, v9, v2_1);
                v2 = v4 + 8;
                goto label_158;
            }
            case 52: {
                if(v5 != v7) {
                    return v4;
                }

                Float v2_2 = Float.valueOf(zzax.zzf(arg18, arg19));
                goto label_149;
            }
            case 53: 
            case 54: {
                if(v5 != 0) {
                    return v4;
                }

                v2 = zzax.zzb(v3, v4, v11);
                v3_3 = v11.zzfe;
            label_143:
                v3_4 = Long.valueOf(v3_3);
                goto label_144;
            }
            case 58: {
                if(v5 != 0) {
                    return v4;
                }

                v2 = zzax.zzb(v3, v4, v11);
                boolean v15_2 = v11.zzfe != 0 ? true : false;
                Boolean v3_7 = Boolean.valueOf(v15_2);
                goto label_144;
            }
            case 59: {
                if(v5 != v15) {
                    return v4;
                }

                v2 = zzax.zza(v3, v4, v11);
                v4 = v11.zzfd;
                if(v4 == 0) {
                    String v3_8 = "";
                label_144:
                    v12.putObject(v1, v9, v3_4);
                    goto label_158;
                }

                if((arg24 & 536870912) != 0) {
                    if(zzff.zze(v3, v2, v2 + v4)) {
                    }
                    else {
                        throw zzco.zzbp();
                    }
                }

                v12.putObject(v1, v9, new String(v3, v2, v4, zzci.UTF_8));
            label_79:
                v2 += v4;
            label_158:
                v12.putInt(v1, v13, v8);
                return v2;
            }
            case 60: {
                if(v5 != v15) {
                    return v4;
                }

                v2 = zzds.zza(v0.zzad(v6), v3, v4, arg20, v11);
                v15_1 = v12.getInt(v1, v13) == v8 ? v12.getObject(v1, v9) : null;
                if(v15_1 == null) {
                    v3_6 = v11.zzff;
                    goto label_144;
                }

                v3_6 = zzci.zza(v15_1, v11.zzff);
                goto label_144;
            }
            case 61: {
                if(v5 != v15) {
                    return v4;
                }

                v2 = zzax.zza(v3, v4, v11);
                v4 = v11.zzfd;
                if(v4 == 0) {
                    zzbb v3_2 = zzbb.zzfi;
                    goto label_144;
                }

                v12.putObject(v1, v9, zzbb.zzb(v3, v2, v4));
                goto label_79;
            }
            case 55: 
            case 62: {
                if(v5 != 0) {
                    return v4;
                }

                v2 = zzax.zza(v3, v4, v11);
                v3_1 = v11.zzfd;
                goto label_138;
            }
            case 63: {
                if(v5 != 0) {
                    return v4;
                }

                v3_1 = zzax.zza(v3, v4, v11);
                v4 = v11.zzfd;
                zzck v5_1 = v0.zzaf(v6);
                if(v5_1 != null) {
                    if(v5_1.zzb(v4) != null) {
                    }
                    else {
                        zzds.zzn(arg17).zzb(v2, Long.valueOf(((long)v4)));
                        return v3_1;
                    }
                }

                v12.putObject(v1, v9, Integer.valueOf(v4));
                v2 = v3_1;
                goto label_158;
            }
            case 57: 
            case 64: {
                if(v5 != v7) {
                    return v4;
                }

                Integer v2_4 = Integer.valueOf(zzax.zzc(arg18, arg19));
            label_149:
                v12.putObject(v1, v9, v2_4);
                v2 = v4 + 4;
                goto label_158;
            }
            case 56: 
            case 65: {
                if(v5 != 1) {
                    return v4;
                }

                v2_1 = Long.valueOf(zzax.zzd(arg18, arg19));
                goto label_156;
            }
            case 66: {
                if(v5 != 0) {
                    return v4;
                }

                v2 = zzax.zza(v3, v4, v11);
                v3_1 = zzbk.zzm(v11.zzfd);
            label_138:
                Integer v3_5 = Integer.valueOf(v3_1);
                goto label_144;
            }
            case 67: {
                if(v5 != 0) {
                    return v4;
                }

                v2 = zzax.zzb(v3, v4, v11);
                v3_3 = zzbk.zza(v11.zzfe);
                goto label_143;
            }
            case 68: {
                if(v5 != 3) {
                    return v4;
                }

                v2 = zzds.zza(v0.zzad(v6), arg18, arg19, arg20, v2 & -8 | 4, arg29);
                v15_1 = v12.getInt(v1, v13) == v8 ? v12.getObject(v1, v9) : null;
                if(v15_1 == null) {
                    v3_6 = v11.zzff;
                    goto label_144;
                }

                v3_6 = zzci.zza(v15_1, v11.zzff);
                goto label_144;
            }
            default: {
                break;
            }
        }

        return v4;
    }

    private final int zza(Object arg17, byte[] arg18, int arg19, int arg20, int arg21, int arg22, int arg23, int arg24, long arg25, int arg27, long arg28, zzay arg30) {
        byte[] v23;
        zzef v22;
        zzef v1_1;
        String v8_1;
        String v6_1;
        int v1_2;
        Object v3_2;
        zzds v0 = this;
        Object v1 = arg17;
        byte[] v3 = arg18;
        int v4 = arg19;
        int v5 = arg20;
        int v2 = arg21;
        int v6 = arg23;
        int v8 = arg24;
        long v9 = arg28;
        zzay v7 = arg30;
        Object v11 = zzds.zzmh.getObject(v1, v9);
        if(!((zzcn)v11).zzu()) {
            int v12 = ((zzcn)v11).size();
            if(v12 == 0) {
                v12 = 10;
            }
            else {
                v12 <<= 1;
            }

            zzcn v11_1 = ((zzcn)v11).zzi(v12);
            zzds.zzmh.putObject(v1, v9, v11_1);
        }

        int v9_1 = 5;
        long v14 = 0;
        int v10 = 2;
        switch(arg27) {
            case 26: {
                if(v6 != v10) {
                    goto label_373;
                }

                if((arg25 & 536870912) != v14) {
                    goto label_177;
                }

                v1_2 = zzax.zza(v3, v4, v7);
                v4 = v7.zzfd;
                if(v4 != 0) {
                    v6_1 = new String(v3, v1_2, v4, zzci.UTF_8);
                    goto label_163;
                label_177:
                    v1_2 = zzax.zza(v3, v4, v7);
                    v4 = v7.zzfd;
                    if(v4 != 0) {
                        v6 = v1_2 + v4;
                        if(zzff.zze(v3, v1_2, v6)) {
                            v8_1 = new String(v3, v1_2, v4, zzci.UTF_8);
                        }
                        else {
                            throw zzco.zzbp();
                        }
                    }
                    else {
                        goto label_180;
                    }

                    goto label_189;
                }
                else {
                    goto label_157;
                }
            }
            case 27: {
                if(v6 != v10) {
                    goto label_373;
                }

                v1_2 = zzds.zza(v0.zzad(v8), arg21, arg18, arg19, arg20, v11, arg30);
                break;
            }
            case 28: {
                if(v6 != v10) {
                    goto label_373;
                }

                v1_2 = zzax.zza(v3, v4, v7);
                v4 = v7.zzfd;
                if(v4 != 0) {
                    goto label_128;
                }

                goto label_125;
            }
            case 18: 
            case 35: {
                if(v6 == v10) {
                    v1_2 = zzax.zza(v3, v4, v7);
                    v2 = v7.zzfd + v1_2;
                    goto label_353;
                }

                if(v6 != 1) {
                    goto label_373;
                }

                ((zzbq)v11).zzc(zzax.zze(arg18, arg19));
                while(true) {
                    v1_2 = v4 + 8;
                    if(v1_2 >= v5) {
                        return v1_2;
                    }

                    v4 = zzax.zza(v3, v1_2, v7);
                    if(v2 != v7.zzfd) {
                        return v1_2;
                    }

                    ((zzbq)v11).zzc(zzax.zze(v3, v4));
                }

            label_353:
                while(v1_2 < v2) {
                    ((zzbq)v11).zzc(zzax.zze(v3, v1_2));
                    v1_2 += 8;
                }

                if(v1_2 == v2) {
                    return v1_2;
                }

                throw zzco.zzbl();
                while(true) {
                label_341:
                    v1_2 = v4 + 4;
                    if(v1_2 >= v5) {
                        return v1_2;
                    }

                    v4 = zzax.zza(v3, v1_2, v7);
                    if(v2 != v7.zzfd) {
                        return v1_2;
                    }

                    ((zzce)v11).zzc(zzax.zzf(v3, v4));
                }

            label_329:
                while(v1_2 < v2) {
                    ((zzce)v11).zzc(zzax.zzf(v3, v1_2));
                    v1_2 += 4;
                }

                if(v1_2 == v2) {
                    return v1_2;
                }

                throw zzco.zzbl();
                while(true) {
                label_317:
                    v1_2 = zzax.zzb(v3, v4, v7);
                    ((zzdc)v11).zzm(v7.zzfe);
                    if(v1_2 >= v5) {
                        return v1_2;
                    }

                    v4 = zzax.zza(v3, v1_2, v7);
                    if(v2 != v7.zzfd) {
                        return v1_2;
                    }
                }

            label_307:
                while(v1_2 < v2) {
                    v1_2 = zzax.zzb(v3, v1_2, v7);
                    ((zzdc)v11).zzm(v7.zzfe);
                }

                if(v1_2 == v2) {
                    return v1_2;
                }

                throw zzco.zzbl();
                while(true) {
                label_284:
                    v1_2 = v4 + 8;
                    if(v1_2 >= v5) {
                        return v1_2;
                    }

                    v4 = zzax.zza(v3, v1_2, v7);
                    if(v2 != v7.zzfd) {
                        return v1_2;
                    }

                    ((zzdc)v11).zzm(zzax.zzd(v3, v4));
                }

            label_272:
                while(v1_2 < v2) {
                    ((zzdc)v11).zzm(zzax.zzd(v3, v1_2));
                    v1_2 += 8;
                }

                if(v1_2 == v2) {
                    return v1_2;
                }

                throw zzco.zzbl();
                while(true) {
                label_260:
                    v1_2 = v4 + 4;
                    if(v1_2 >= v5) {
                        return v1_2;
                    }

                    v4 = zzax.zza(v3, v1_2, v7);
                    if(v2 != v7.zzfd) {
                        return v1_2;
                    }

                    ((zzch)v11).zzac(zzax.zzc(v3, v4));
                }

            label_248:
                while(v1_2 < v2) {
                    ((zzch)v11).zzac(zzax.zzc(v3, v1_2));
                    v1_2 += 4;
                }

                if(v1_2 == v2) {
                    return v1_2;
                }

                throw zzco.zzbl();
            label_215:
                while(v2 < v4) {
                    v2 = zzax.zzb(v3, v2, v7);
                    boolean v5_1 = v7.zzfe != v14 ? true : false;
                    ((zzaz)v11).addBoolean(v5_1);
                }

                if(v2 == v4) {
                }
                else {
                    throw zzco.zzbl();
                }

                return v2;
                while(true) {
                label_89:
                    v1_2 = zzax.zza(v3, v4, v7);
                    ((zzch)v11).zzac(zzbk.zzm(v7.zzfd));
                    if(v1_2 >= v5) {
                        return v1_2;
                    }

                    v4 = zzax.zza(v3, v1_2, v7);
                    if(v2 != v7.zzfd) {
                        return v1_2;
                    }
                }

            label_78:
                while(v1_2 < v2) {
                    v1_2 = zzax.zza(v3, v1_2, v7);
                    ((zzch)v11).zzac(zzbk.zzm(v7.zzfd));
                }

                if(v1_2 == v2) {
                    return v1_2;
                }

                throw zzco.zzbl();
                while(true) {
                label_65:
                    v1_2 = zzax.zzb(v3, v4, v7);
                    ((zzdc)v11).zzm(zzbk.zza(v7.zzfe));
                    if(v1_2 >= v5) {
                        return v1_2;
                    }

                    v4 = zzax.zza(v3, v1_2, v7);
                    if(v2 != v7.zzfd) {
                        return v1_2;
                    }
                }

            label_54:
                while(v1_2 < v2) {
                    v1_2 = zzax.zzb(v3, v1_2, v7);
                    ((zzdc)v11).zzm(zzbk.zza(v7.zzfe));
                }

                if(v1_2 == v2) {
                    return v1_2;
                }

                throw zzco.zzbl();
                while(true) {
                label_36:
                    v4 = zzds.zza(v22, v23, arg24, arg20, v6, arg30);
                    ((zzcn)v11).add(v7.zzff);
                    if(v4 >= v5) {
                        goto label_373;
                    }

                    v8 = zzax.zza(v3, v4, v7);
                    if(v2 != v7.zzfd) {
                        goto label_373;
                    }

                    v22 = v1_1;
                    v23 = arg18;
                    arg24 = v8;
                }

            label_125:
                ((zzcn)v11).add(zzbb.zzfi);
            label_131:
                if(v1_2 >= v5) {
                    return v1_2;
                }

                v4 = zzax.zza(v3, v1_2, v7);
                if(v2 != v7.zzfd) {
                    return v1_2;
                }

                v1_2 = zzax.zza(v3, v4, v7);
                v4 = v7.zzfd;
                if(v4 == 0) {
                    goto label_125;
                }

            label_128:
                ((zzcn)v11).add(zzbb.zzb(v3, v1_2, v4));
                v1_2 += v4;
                goto label_131;
            label_157:
                ((zzcn)v11).add("");
            label_165:
                if(v1_2 >= v5) {
                    return v1_2;
                }

                v4 = zzax.zza(v3, v1_2, v7);
                if(v2 != v7.zzfd) {
                    return v1_2;
                }

                v1_2 = zzax.zza(v3, v4, v7);
                v4 = v7.zzfd;
                if(v4 == 0) {
                    goto label_157;
                }

                v6_1 = new String(v3, v1_2, v4, zzci.UTF_8);
            label_163:
                ((zzcn)v11).add(v6_1);
                v1_2 += v4;
                goto label_165;
            label_180:
                ((zzcn)v11).add("");
            label_191:
                if(v1_2 >= v5) {
                    return v1_2;
                }

                v4 = zzax.zza(v3, v1_2, v7);
                if(v2 != v7.zzfd) {
                    return v1_2;
                }

                v1_2 = zzax.zza(v3, v4, v7);
                v4 = v7.zzfd;
                if(v4 == 0) {
                    goto label_180;
                }

                v6 = v1_2 + v4;
                if(zzff.zze(v3, v1_2, v6)) {
                    v8_1 = new String(v3, v1_2, v4, zzci.UTF_8);
                }
                else {
                    throw zzco.zzbp();
                }

            label_189:
                ((zzcn)v11).add(v8_1);
                v1_2 = v6;
                goto label_191;
            label_232:
                boolean v6_2 = true;
            label_235:
                ((zzaz)v11).addBoolean(v6_2);
                if(v4 < v5) {
                    v6 = zzax.zza(v3, v4, v7);
                    if(v2 != v7.zzfd) {
                        goto label_373;
                    }

                    v4 = zzax.zzb(v3, v6, v7);
                    if(v7.zzfe != v14) {
                        goto label_232;
                    }

                label_234:
                    v6_2 = false;
                    goto label_235;
                }

            label_373:
                v1_2 = v4;
                break;
            }
            case 19: 
            case 36: {
                if(v6 == v10) {
                    v1_2 = zzax.zza(v3, v4, v7);
                    v2 = v7.zzfd + v1_2;
                    goto label_329;
                }

                if(v6 != v9_1) {
                    goto label_373;
                }

                ((zzce)v11).zzc(zzax.zzf(arg18, arg19));
                goto label_341;
            }
            case 20: 
            case 21: 
            case 37: 
            case 38: {
                if(v6 == v10) {
                    v1_2 = zzax.zza(v3, v4, v7);
                    v2 = v7.zzfd + v1_2;
                    goto label_307;
                }

                if(v6 != 0) {
                    goto label_373;
                }

                goto label_317;
            }
            case 25: 
            case 42: {
                if(v6 == v10) {
                    v2 = zzax.zza(v3, v4, v7);
                    v4 = v7.zzfd + v2;
                    goto label_215;
                }

                if(v6 != 0) {
                    goto label_373;
                }

                v4 = zzax.zzb(v3, v4, v7);
                if(v7.zzfe == v14) {
                    goto label_234;
                }

                goto label_232;
            }
            case 22: 
            case 29: 
            case 39: 
            case 43: {
                if(v6 == v10) {
                    return zzax.zza(v3, v4, ((zzcn)v11), v7);
                }

                if(v6 != 0) {
                    goto label_373;
                }

                v1_2 = zzax.zza(arg21, arg18, arg19, arg20, v11, arg30);
                break;
            }
            case 30: 
            case 44: {
                if(v6 == v10) {
                    v2 = zzax.zza(v3, v4, ((zzcn)v11), v7);
                }
                else if(v6 == 0) {
                    v2 = zzax.zza(arg21, arg18, arg19, arg20, v11, arg30);
                }
                else {
                    goto label_373;
                }

                zzey v3_1 = ((zzcg)v1).zzjp;
                if(v3_1 == zzey.zzea()) {
                    v3_2 = null;
                }

                v3_2 = zzeh.zza(arg22, ((List)v11), v0.zzaf(v8), v3_1, v0.zzmx);
                if(v3_2 == null) {
                    return v2;
                }

                ((zzcg)v1).zzjp = ((zzey)v3_2);
                return v2;
            }
            case 24: 
            case 31: 
            case 41: 
            case 45: {
                if(v6 == v10) {
                    v1_2 = zzax.zza(v3, v4, v7);
                    v2 = v7.zzfd + v1_2;
                    goto label_248;
                }

                if(v6 != v9_1) {
                    goto label_373;
                }

                ((zzch)v11).zzac(zzax.zzc(arg18, arg19));
                goto label_260;
            }
            case 23: 
            case 32: 
            case 40: 
            case 46: {
                if(v6 == v10) {
                    v1_2 = zzax.zza(v3, v4, v7);
                    v2 = v7.zzfd + v1_2;
                    goto label_272;
                }

                if(v6 != 1) {
                    goto label_373;
                }

                ((zzdc)v11).zzm(zzax.zzd(arg18, arg19));
                goto label_284;
            }
            case 33: 
            case 47: {
                if(v6 == v10) {
                    v1_2 = zzax.zza(v3, v4, v7);
                    v2 = v7.zzfd + v1_2;
                    goto label_78;
                }

                if(v6 != 0) {
                    goto label_373;
                }

                goto label_89;
            }
            case 34: 
            case 48: {
                if(v6 == v10) {
                    v1_2 = zzax.zza(v3, v4, v7);
                    v2 = v7.zzfd + v1_2;
                    goto label_54;
                }

                if(v6 != 0) {
                    goto label_373;
                }

                goto label_65;
            }
            case 49: {
                if(v6 != 3) {
                    goto label_373;
                }

                v1_1 = v0.zzad(v8);
                v6 = v2 & -8 | 4;
                v22 = v1_1;
                v23 = arg18;
                arg24 = arg19;
                goto label_36;
            }
            default: {
                goto label_373;
            }
        }

        return v1_2;
    }

    private final int zza(Object arg7, byte[] arg8, int arg9, int arg10, int arg11, int arg12, long arg13, zzay arg15) {
        Unsafe v12 = zzds.zzmh;
        Object v11 = this.zzae(arg11);
        Object v0 = v12.getObject(arg7, arg13);
        if(this.zzmz.zzi(v0)) {
            Object v1 = this.zzmz.zzk(v11);
            this.zzmz.zzb(v1, v0);
            v12.putObject(arg7, arg13, v1);
            v0 = v1;
        }

        zzdh v7 = this.zzmz.zzl(v11);
        Map v11_1 = this.zzmz.zzg(v0);
        arg9 = zzax.zza(arg8, arg9, arg15);
        arg12 = arg15.zzfd;
        if(arg12 >= 0 && arg12 <= arg10 - arg9) {
            arg12 += arg9;
            Object v13 = v7.zzmc;
            Object v14 = v7.zzdu;
        label_24:
            while(arg9 < arg12) {
                int v0_1 = arg9 + 1;
                arg9 = arg8[arg9];
                if(arg9 < 0) {
                    v0_1 = zzax.zza(arg9, arg8, v0_1, arg15);
                    arg9 = arg15.zzfd;
                }

                int v1_1 = v0_1;
                int v2 = arg9 & 7;
                switch(arg9 >>> 3) {
                    case 1: {
                        if(v2 != v7.zzmb.zzel()) {
                            goto label_58;
                        }

                        arg9 = zzds.zza(arg8, v1_1, arg10, v7.zzmb, null, arg15);
                        v13 = arg15.zzff;
                        goto label_24;
                    }
                    case 2: {
                        if(v2 != v7.zzmd.zzel()) {
                            goto label_58;
                        }

                        arg9 = zzds.zza(arg8, v1_1, arg10, v7.zzmd, v7.zzdu.getClass(), arg15);
                        v14 = arg15.zzff;
                        goto label_24;
                    }
                    default: {
                        break;
                    }
                }

            label_58:
                arg9 = zzax.zza(arg9, arg8, v1_1, arg10, arg15);
            }

            if(arg9 == arg12) {
                v11_1.put(v13, v14);
                return arg12;
            }

            throw zzco.zzbo();
        }

        throw zzco.zzbl();
    }

    private static int zza(byte[] arg1, int arg2, int arg3, zzfl arg4, Class arg5, zzay arg6) {
        switch(zzdt.zzgq[arg4.ordinal()]) {
            case 1: {
                goto label_49;
            }
            case 2: {
                goto label_47;
            }
            case 3: {
                goto label_42;
            }
            case 4: 
            case 5: {
                goto label_37;
            }
            case 6: 
            case 7: {
                goto label_34;
            }
            case 8: {
                goto label_31;
            }
            case 9: 
            case 10: 
            case 11: {
                goto label_26;
            }
            case 12: 
            case 13: {
                goto label_22;
            }
            case 14: {
                goto label_18;
            }
            case 15: {
                goto label_14;
            }
            case 16: {
                goto label_10;
            }
            case 17: {
                goto label_8;
            }
        }

        throw new RuntimeException("unsupported field type.");
    label_34:
        Long v1 = Long.valueOf(zzax.zzd(arg1, arg2));
        goto label_44;
    label_37:
        Integer v1_1 = Integer.valueOf(zzax.zzc(arg1, arg2));
        goto label_39;
    label_8:
        int v1_2 = zzax.zzd(arg1, arg2, arg6);
        return v1_2;
    label_42:
        Double v1_3 = Double.valueOf(zzax.zze(arg1, arg2));
    label_44:
        arg6.zzff = v1;
        return arg2 + 8;
    label_10:
        v1_2 = zzax.zzb(arg1, arg2, arg6);
        long v2 = zzbk.zza(arg6.zzfe);
        goto label_24;
    label_14:
        v1_2 = zzax.zza(arg1, arg2, arg6);
        arg2 = zzbk.zzm(arg6.zzfd);
        goto label_28;
    label_47:
        return zzax.zze(arg1, arg2, arg6);
    label_49:
        v1_2 = zzax.zzb(arg1, arg2, arg6);
        boolean v2_1 = arg6.zzfe != 0 ? true : false;
        Boolean v2_2 = Boolean.valueOf(v2_1);
        goto label_29;
    label_18:
        return zzds.zza(zzea.zzcm().zze(arg5), arg1, arg2, arg3, arg6);
    label_22:
        v1_2 = zzax.zzb(arg1, arg2, arg6);
        v2 = arg6.zzfe;
    label_24:
        Long v2_3 = Long.valueOf(v2);
        goto label_29;
    label_26:
        v1_2 = zzax.zza(arg1, arg2, arg6);
        arg2 = arg6.zzfd;
    label_28:
        Integer v2_4 = Integer.valueOf(arg2);
    label_29:
        arg6.zzff = v2_2;
        return v1_2;
    label_31:
        Float v1_4 = Float.valueOf(zzax.zzf(arg1, arg2));
    label_39:
        arg6.zzff = v1_4;
        return arg2 + 4;
    }

    private final Object zza(int arg5, int arg6, Map arg7, zzck arg8, Object arg9, zzex arg10) {
        zzdh v5 = this.zzmz.zzl(this.zzae(arg5));
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            if(arg8.zzb(((Map$Entry)v0).getValue().intValue()) != null) {
                continue;
            }

            if(arg9 == null) {
                arg9 = arg10.zzdz();
            }

            zzbg v1 = zzbb.zzk(zzdg.zza(v5, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue()));
            zzbn v2 = v1.zzae();
            try {
                zzdg.zza(v2, v5, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
            }
            catch(IOException v5_1) {
                throw new RuntimeException(((Throwable)v5_1));
            }

            arg10.zza(arg9, arg6, v1.zzad());
            v7.remove();
        }

        return arg9;
    }

    private static void zza(int arg1, Object arg2, zzfr arg3) {
        if((arg2 instanceof String)) {
            arg3.zza(arg1, ((String)arg2));
            return;
        }

        arg3.zza(arg1, ((zzbb)arg2));
    }

    private static void zza(zzex arg0, Object arg1, zzfr arg2) {
        arg0.zza(arg0.zzq(arg1), arg2);
    }

    private final void zza(zzfr arg2, int arg3, Object arg4, int arg5) {
        if(arg4 != null) {
            arg2.zza(arg3, this.zzmz.zzl(this.zzae(arg5)), this.zzmz.zzh(arg4));
        }
    }

    private final void zza(Object arg4, Object arg5, int arg6) {
        long v0 = ((long)(this.zzag(arg6) & 1048575));
        if(!this.zza(arg5, arg6)) {
            return;
        }

        Object v2 = zzfd.zzo(arg4, v0);
        arg5 = zzfd.zzo(arg5, v0);
        if(v2 != null && arg5 != null) {
            zzfd.zza(arg4, v0, zzci.zza(v2, arg5));
            this.zzb(arg4, arg6);
            return;
        }

        if(arg5 != null) {
            zzfd.zza(arg4, v0, arg5);
            this.zzb(arg4, arg6);
        }
    }

    private final boolean zza(Object arg7, int arg8) {
        int v1 = 1048575;
        if(!this.zzmq) {
            goto label_103;
        }

        arg8 = this.zzag(arg8);
        long v0 = ((long)(arg8 & v1));
        long v4 = 0;
        switch((arg8 & 267386880) >>> 20) {
            case 0: {
                goto label_98;
            }
            case 1: {
                goto label_93;
            }
            case 2: {
                goto label_89;
            }
            case 3: {
                goto label_85;
            }
            case 4: {
                goto label_81;
            }
            case 5: {
                goto label_77;
            }
            case 6: {
                goto label_73;
            }
            case 7: {
                goto label_71;
            }
            case 8: {
                goto label_54;
            }
            case 9: {
                goto label_50;
            }
            case 10: {
                goto label_44;
            }
            case 11: {
                goto label_40;
            }
            case 12: {
                goto label_36;
            }
            case 13: {
                goto label_32;
            }
            case 14: {
                goto label_28;
            }
            case 15: {
                goto label_24;
            }
            case 16: {
                goto label_20;
            }
            case 17: {
                goto label_16;
            }
        }

        throw new IllegalArgumentException();
    label_98:
        if(zzfd.zzn(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_36:
        if(zzfd.zzj(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_71:
        return zzfd.zzl(arg7, v0);
    label_40:
        if(zzfd.zzj(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_73:
        if(zzfd.zzj(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_44:
        if(!zzbb.zzfi.equals(zzfd.zzo(arg7, v0))) {
            return 1;
        }

        return 0;
    label_77:
        if(zzfd.zzk(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_16:
        if(zzfd.zzo(arg7, v0) != null) {
            return 1;
        }

        return 0;
    label_81:
        if(zzfd.zzj(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_50:
        if(zzfd.zzo(arg7, v0) != null) {
            return 1;
        }

        return 0;
    label_20:
        if(zzfd.zzk(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_85:
        if(zzfd.zzk(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_54:
        arg7 = zzfd.zzo(arg7, v0);
        if((arg7 instanceof String)) {
            if(!((String)arg7).isEmpty()) {
                return 1;
            }

            return 0;
        }

        if((arg7 instanceof zzbb)) {
            if(!zzbb.zzfi.equals(arg7)) {
                return 1;
            }

            return 0;
        }

        throw new IllegalArgumentException();
    label_24:
        if(zzfd.zzj(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_89:
        if(zzfd.zzk(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_28:
        if(zzfd.zzk(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_93:
        if(zzfd.zzm(arg7, v0) != 0f) {
            return 1;
        }

        return 0;
    label_32:
        if(zzfd.zzj(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_103:
        arg8 = this.zzah(arg8);
        if((zzfd.zzj(arg7, ((long)(arg8 & v1))) & 1 << (arg8 >>> 20)) != 0) {
            return 1;
        }

        return 0;
    }

    private final boolean zza(Object arg3, int arg4, int arg5) {
        if(zzfd.zzj(arg3, ((long)(this.zzah(arg5) & 1048575))) == arg4) {
            return 1;
        }

        return 0;
    }

    private final boolean zza(Object arg2, int arg3, int arg4, int arg5) {
        if(this.zzmq) {
            return this.zza(arg2, arg3);
        }

        if((arg4 & arg5) != 0) {
            return 1;
        }

        return 0;
    }

    private static boolean zza(Object arg2, int arg3, zzef arg4) {
        return arg4.zzo(zzfd.zzo(arg2, ((long)(arg3 & 1048575))));
    }

    public final void zza(Object arg14, zzfr arg15) {
        boolean v9_2;
        float v9_1;
        long v11_1;
        double v11;
        boolean v8_2;
        double v10_1;
        long v10;
        float v8_1;
        int v9;
        Object v1;
        Iterator v0_1;
        zzby v0;
        int v2 = 267386880;
        Object v3 = null;
        int v6 = 1048575;
        if(arg15.zzaj() == zzg.zzkp) {
            zzds.zza(this.zzmx, arg14, arg15);
            if(this.zzmo) {
                v0 = this.zzmy.zza(arg14);
                if(!v0.isEmpty()) {
                    v0_1 = v0.descendingIterator();
                    v1 = v0_1.next();
                }
                else {
                    goto label_19;
                }
            }
            else {
            label_19:
                v0_1 = ((Iterator)v3);
                v1 = v0_1;
            }

            int v7;
            for(v7 = this.zzmi.length - 4; v7 >= 0; v7 += -4) {
                int v8 = this.zzag(v7);
                v9 = this.zzmi[v7];
                while(v1 != null) {
                    if(this.zzmy.zza(((Map$Entry)v1)) <= v9) {
                        break;
                    }

                    this.zzmy.zza(arg15, ((Map$Entry)v1));
                    v1 = v0_1.hasNext() ? v0_1.next() : v3;
                }

                switch((v8 & v2) >>> 20) {
                    case 0: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v10_1 = zzfd.zzn(arg14, ((long)(v8 & v6)));
                        goto label_497;
                    }
                    case 1: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8_1 = zzfd.zzm(arg14, ((long)(v8 & v6)));
                        goto label_490;
                    }
                    case 2: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzfd.zzk(arg14, ((long)(v8 & v6)));
                    label_483:
                        arg15.zzi(v9, v10);
                        break;
                    }
                    case 3: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzfd.zzk(arg14, ((long)(v8 & v6)));
                    label_476:
                        arg15.zza(v9, v10);
                        break;
                    }
                    case 4: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzfd.zzj(arg14, ((long)(v8 & v6)));
                    label_469:
                        arg15.zzc(v9, v8);
                        break;
                    }
                    case 5: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzfd.zzk(arg14, ((long)(v8 & v6)));
                    label_462:
                        arg15.zzc(v9, v10);
                        break;
                    }
                    case 6: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzfd.zzj(arg14, ((long)(v8 & v6)));
                    label_455:
                        arg15.zzf(v9, v8);
                        break;
                    }
                    case 7: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8_2 = zzfd.zzl(arg14, ((long)(v8 & v6)));
                        goto label_448;
                    }
                    case 8: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_438;
                    }
                    case 9: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_430;
                    }
                    case 10: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_423;
                    }
                    case 11: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzfd.zzj(arg14, ((long)(v8 & v6)));
                        goto label_419;
                    }
                    case 12: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzfd.zzj(arg14, ((long)(v8 & v6)));
                        goto label_412;
                    }
                    case 13: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzfd.zzj(arg14, ((long)(v8 & v6)));
                        goto label_405;
                    }
                    case 14: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzfd.zzk(arg14, ((long)(v8 & v6)));
                        goto label_398;
                    }
                    case 15: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzfd.zzj(arg14, ((long)(v8 & v6)));
                        goto label_391;
                    }
                    case 16: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzfd.zzk(arg14, ((long)(v8 & v6)));
                        goto label_384;
                    }
                    case 17: {
                        if(!this.zza(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_373;
                    }
                    case 18: {
                        zzeh.zza(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 19: {
                        zzeh.zzb(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 20: {
                        zzeh.zzc(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 21: {
                        zzeh.zzd(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 22: {
                        zzeh.zzh(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 23: {
                        zzeh.zzf(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 24: {
                        zzeh.zzk(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 25: {
                        zzeh.zzn(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 26: {
                        zzeh.zza(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15);
                        break;
                    }
                    case 27: {
                        zzeh.zza(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, this.zzad(v7));
                        break;
                    }
                    case 28: {
                        zzeh.zzb(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15);
                        break;
                    }
                    case 29: {
                        zzeh.zzi(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 30: {
                        zzeh.zzm(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 31: {
                        zzeh.zzl(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 32: {
                        zzeh.zzg(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 33: {
                        zzeh.zzj(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 34: {
                        zzeh.zze(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 35: {
                        zzeh.zza(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 36: {
                        zzeh.zzb(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 37: {
                        zzeh.zzc(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 38: {
                        zzeh.zzd(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 39: {
                        zzeh.zzh(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 40: {
                        zzeh.zzf(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 41: {
                        zzeh.zzk(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 42: {
                        zzeh.zzn(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 43: {
                        zzeh.zzi(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 44: {
                        zzeh.zzm(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 45: {
                        zzeh.zzl(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 46: {
                        zzeh.zzg(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 47: {
                        zzeh.zzj(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 48: {
                        zzeh.zze(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 49: {
                        zzeh.zzb(this.zzmi[v7], zzfd.zzo(arg14, ((long)(v8 & v6))), arg15, this.zzad(v7));
                        break;
                    }
                    case 50: {
                        this.zza(arg15, v9, zzfd.zzo(arg14, ((long)(v8 & v6))), v7);
                        break;
                    }
                    case 51: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10_1 = zzds.zze(arg14, ((long)(v8 & v6)));
                    label_497:
                        arg15.zza(v9, v10_1);
                        break;
                    }
                    case 52: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8_1 = zzds.zzf(arg14, ((long)(v8 & v6)));
                    label_490:
                        arg15.zza(v9, v8_1);
                        break;
                    }
                    case 53: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzds.zzh(arg14, ((long)(v8 & v6)));
                        goto label_483;
                    }
                    case 54: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzds.zzh(arg14, ((long)(v8 & v6)));
                        goto label_476;
                    }
                    case 55: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzds.zzg(arg14, ((long)(v8 & v6)));
                        goto label_469;
                    }
                    case 56: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzds.zzh(arg14, ((long)(v8 & v6)));
                        goto label_462;
                    }
                    case 57: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzds.zzg(arg14, ((long)(v8 & v6)));
                        goto label_455;
                    }
                    case 58: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8_2 = zzds.zzi(arg14, ((long)(v8 & v6)));
                    label_448:
                        arg15.zzb(v9, v8_2);
                        break;
                    }
                    case 59: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_438:
                        zzds.zza(v9, zzfd.zzo(arg14, ((long)(v8 & v6))), arg15);
                        break;
                    }
                    case 60: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_430:
                        arg15.zza(v9, zzfd.zzo(arg14, ((long)(v8 & v6))), this.zzad(v7));
                        break;
                    }
                    case 61: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_423:
                        arg15.zza(v9, zzfd.zzo(arg14, ((long)(v8 & v6))));
                        break;
                    }
                    case 62: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzds.zzg(arg14, ((long)(v8 & v6)));
                    label_419:
                        arg15.zzd(v9, v8);
                        break;
                    }
                    case 63: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzds.zzg(arg14, ((long)(v8 & v6)));
                    label_412:
                        arg15.zzn(v9, v8);
                        break;
                    }
                    case 64: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzds.zzg(arg14, ((long)(v8 & v6)));
                    label_405:
                        arg15.zzm(v9, v8);
                        break;
                    }
                    case 65: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzds.zzh(arg14, ((long)(v8 & v6)));
                    label_398:
                        arg15.zzj(v9, v10);
                        break;
                    }
                    case 66: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzds.zzg(arg14, ((long)(v8 & v6)));
                    label_391:
                        arg15.zze(v9, v8);
                        break;
                    }
                    case 67: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzds.zzh(arg14, ((long)(v8 & v6)));
                    label_384:
                        arg15.zzb(v9, v10);
                        break;
                    }
                    case 68: {
                        if(!this.zza(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_373:
                        arg15.zzb(v9, zzfd.zzo(arg14, ((long)(v8 & v6))), this.zzad(v7));
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_498:
            }

            while(v1 != null) {
                this.zzmy.zza(arg15, ((Map$Entry)v1));
                if(v0_1.hasNext()) {
                    v1 = v0_1.next();
                    continue;
                }

                v1 = v3;
            }

            return;
        }

        if(this.zzmq) {
            if(this.zzmo) {
                v0 = this.zzmy.zza(arg14);
                if(!v0.isEmpty()) {
                    v0_1 = v0.iterator();
                    v1 = v0_1.next();
                }
                else {
                    goto label_522;
                }
            }
            else {
            label_522:
                v0_1 = ((Iterator)v3);
                v1 = v0_1;
            }

            v7 = this.zzmi.length;
            Object v8_3 = v1;
            int v1_1;
            for(v1_1 = 0; v1_1 < v7; v1_1 += 4) {
                v9 = this.zzag(v1_1);
                int v10_2 = this.zzmi[v1_1];
                while(v8_3 != null) {
                    if(this.zzmy.zza(((Map$Entry)v8_3)) > v10_2) {
                        break;
                    }

                    this.zzmy.zza(arg15, ((Map$Entry)v8_3));
                    v8_3 = v0_1.hasNext() ? v0_1.next() : v3;
                }

                switch((v9 & v2) >>> 20) {
                    case 0: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11 = zzfd.zzn(arg14, ((long)(v9 & v6)));
                        goto label_1001;
                    }
                    case 1: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9_1 = zzfd.zzm(arg14, ((long)(v9 & v6)));
                    label_994:
                        arg15.zza(v10_2, v9_1);
                        break;
                    }
                    case 2: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzfd.zzk(arg14, ((long)(v9 & v6)));
                    label_987:
                        arg15.zzi(v10_2, v11_1);
                        break;
                    }
                    case 3: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzfd.zzk(arg14, ((long)(v9 & v6)));
                    label_980:
                        arg15.zza(v10_2, v11_1);
                        break;
                    }
                    case 4: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzfd.zzj(arg14, ((long)(v9 & v6)));
                    label_973:
                        arg15.zzc(v10_2, v9);
                        break;
                    }
                    case 5: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzfd.zzk(arg14, ((long)(v9 & v6)));
                        goto label_966;
                    }
                    case 6: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzfd.zzj(arg14, ((long)(v9 & v6)));
                        goto label_959;
                    }
                    case 7: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9_2 = zzfd.zzl(arg14, ((long)(v9 & v6)));
                        goto label_952;
                    }
                    case 8: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_942;
                    }
                    case 9: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_934;
                    }
                    case 10: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_927;
                    }
                    case 11: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzfd.zzj(arg14, ((long)(v9 & v6)));
                        goto label_923;
                    }
                    case 12: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzfd.zzj(arg14, ((long)(v9 & v6)));
                        goto label_916;
                    }
                    case 13: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzfd.zzj(arg14, ((long)(v9 & v6)));
                        goto label_909;
                    }
                    case 14: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzfd.zzk(arg14, ((long)(v9 & v6)));
                        goto label_902;
                    }
                    case 15: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzfd.zzj(arg14, ((long)(v9 & v6)));
                        goto label_895;
                    }
                    case 16: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzfd.zzk(arg14, ((long)(v9 & v6)));
                        goto label_888;
                    }
                    case 17: {
                        if(!this.zza(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_877;
                    }
                    case 18: {
                        zzeh.zza(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 19: {
                        zzeh.zzb(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 20: {
                        zzeh.zzc(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 21: {
                        zzeh.zzd(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 22: {
                        zzeh.zzh(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 23: {
                        zzeh.zzf(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 24: {
                        zzeh.zzk(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 25: {
                        zzeh.zzn(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 26: {
                        zzeh.zza(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15);
                        break;
                    }
                    case 27: {
                        zzeh.zza(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, this.zzad(v1_1));
                        break;
                    }
                    case 28: {
                        zzeh.zzb(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15);
                        break;
                    }
                    case 29: {
                        zzeh.zzi(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 30: {
                        zzeh.zzm(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 31: {
                        zzeh.zzl(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 32: {
                        zzeh.zzg(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 33: {
                        zzeh.zzj(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 34: {
                        zzeh.zze(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 35: {
                        zzeh.zza(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 36: {
                        zzeh.zzb(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 37: {
                        zzeh.zzc(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 38: {
                        zzeh.zzd(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 39: {
                        zzeh.zzh(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 40: {
                        zzeh.zzf(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 41: {
                        zzeh.zzk(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 42: {
                        zzeh.zzn(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 43: {
                        zzeh.zzi(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 44: {
                        zzeh.zzm(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 45: {
                        zzeh.zzl(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 46: {
                        zzeh.zzg(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 47: {
                        zzeh.zzj(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 48: {
                        zzeh.zze(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 49: {
                        zzeh.zzb(this.zzmi[v1_1], zzfd.zzo(arg14, ((long)(v9 & v6))), arg15, this.zzad(v1_1));
                        break;
                    }
                    case 50: {
                        this.zza(arg15, v10_2, zzfd.zzo(arg14, ((long)(v9 & v6))), v1_1);
                        break;
                    }
                    case 51: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11 = zzds.zze(arg14, ((long)(v9 & v6)));
                    label_1001:
                        arg15.zza(v10_2, v11);
                        break;
                    }
                    case 52: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9_1 = zzds.zzf(arg14, ((long)(v9 & v6)));
                        goto label_994;
                    }
                    case 53: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzds.zzh(arg14, ((long)(v9 & v6)));
                        goto label_987;
                    }
                    case 54: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzds.zzh(arg14, ((long)(v9 & v6)));
                        goto label_980;
                    }
                    case 55: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzds.zzg(arg14, ((long)(v9 & v6)));
                        goto label_973;
                    }
                    case 56: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzds.zzh(arg14, ((long)(v9 & v6)));
                    label_966:
                        arg15.zzc(v10_2, v11_1);
                        break;
                    }
                    case 57: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzds.zzg(arg14, ((long)(v9 & v6)));
                    label_959:
                        arg15.zzf(v10_2, v9);
                        break;
                    }
                    case 58: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9_2 = zzds.zzi(arg14, ((long)(v9 & v6)));
                    label_952:
                        arg15.zzb(v10_2, v9_2);
                        break;
                    }
                    case 59: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_942:
                        zzds.zza(v10_2, zzfd.zzo(arg14, ((long)(v9 & v6))), arg15);
                        break;
                    }
                    case 60: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_934:
                        arg15.zza(v10_2, zzfd.zzo(arg14, ((long)(v9 & v6))), this.zzad(v1_1));
                        break;
                    }
                    case 61: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_927:
                        arg15.zza(v10_2, zzfd.zzo(arg14, ((long)(v9 & v6))));
                        break;
                    }
                    case 62: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzds.zzg(arg14, ((long)(v9 & v6)));
                    label_923:
                        arg15.zzd(v10_2, v9);
                        break;
                    }
                    case 63: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzds.zzg(arg14, ((long)(v9 & v6)));
                    label_916:
                        arg15.zzn(v10_2, v9);
                        break;
                    }
                    case 64: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzds.zzg(arg14, ((long)(v9 & v6)));
                    label_909:
                        arg15.zzm(v10_2, v9);
                        break;
                    }
                    case 65: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzds.zzh(arg14, ((long)(v9 & v6)));
                    label_902:
                        arg15.zzj(v10_2, v11_1);
                        break;
                    }
                    case 66: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzds.zzg(arg14, ((long)(v9 & v6)));
                    label_895:
                        arg15.zze(v10_2, v9);
                        break;
                    }
                    case 67: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzds.zzh(arg14, ((long)(v9 & v6)));
                    label_888:
                        arg15.zzb(v10_2, v11_1);
                        break;
                    }
                    case 68: {
                        if(!this.zza(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_877:
                        arg15.zzb(v10_2, zzfd.zzo(arg14, ((long)(v9 & v6))), this.zzad(v1_1));
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_1002:
            }

            while(v8_3 != null) {
                this.zzmy.zza(arg15, ((Map$Entry)v8_3));
                if(v0_1.hasNext()) {
                    v8_3 = v0_1.next();
                    continue;
                }

                v8_3 = v3;
            }

            zzds.zza(this.zzmx, arg14, arg15);
            return;
        }

        this.zzb(arg14, arg15);
    }

    public final void zza(Object arg24, byte[] arg25, int arg26, int arg27, zzay arg28) {
        int v2_1;
        int v15_1;
        Unsafe v22;
        zzcn v0_2;
        int v16;
        int v10;
        zzds v15 = this;
        Object v14 = arg24;
        byte[] v12 = arg25;
        int v13 = arg27;
        zzay v11 = arg28;
        if(v15.zzmq) {
            Unsafe v9 = zzds.zzmh;
            int v0 = arg26;
            while(v0 < v13) {
                int v1 = v0 + 1;
                v0 = v12[v0];
                if(v0 < 0) {
                    v10 = zzax.zza(v0, v12, v1, v11);
                    v16 = v11.zzfd;
                }
                else {
                    v16 = v0;
                    v10 = v1;
                }

                int v6 = v16 >>> 3;
                int v7 = v16 & 7;
                int v8 = v15.zzai(v6);
                if(v8 >= 0) {
                    int v5 = v15.zzmi[v8 + 1];
                    int v4 = (267386880 & v5) >>> 20;
                    long v2 = ((long)(1048575 & v5));
                    v1 = 2;
                    if(v4 <= 17) {
                        v0 = 5;
                        boolean v6_1 = true;
                        switch(v4) {
                            case 0: {
                                goto label_111;
                            }
                            case 1: {
                                goto label_106;
                            }
                            case 2: 
                            case 3: {
                                goto label_98;
                            }
                            case 7: {
                                goto label_74;
                            }
                            case 8: {
                                goto label_66;
                            }
                            case 9: {
                                goto label_57;
                            }
                            case 10: {
                                goto label_52;
                            }
                            case 4: 
                            case 11: {
                                goto label_93;
                            }
                            case 12: {
                                goto label_50;
                            }
                            case 6: 
                            case 13: {
                                goto label_83;
                            }
                            case 5: 
                            case 14: {
                                goto label_87;
                            }
                            case 15: {
                                goto label_45;
                            }
                            case 16: {
                                goto label_40;
                            }
                        }

                        goto label_194;
                    label_98:
                        if(v7 == 0) {
                            v6 = zzax.zzb(v12, v10, v11);
                            long v4_1 = v11.zzfe;
                            goto label_101;
                        label_66:
                            if(v7 != v1) {
                                goto label_194;
                            }
                            else if((536870912 & v5) == 0) {
                                v0 = zzax.zzc(v12, v10, v11);
                                goto label_54;
                            }
                            else {
                                v0 = zzax.zzd(v12, v10, v11);
                                goto label_54;
                            label_40:
                                if(v7 == 0) {
                                    v6 = zzax.zzb(v12, v10, v11);
                                    v4_1 = zzbk.zza(v11.zzfe);
                                label_101:
                                    v9.putLong(arg24, v2, v4_1);
                                    v0 = v6;
                                    continue;
                                label_106:
                                    if(v7 == v0) {
                                        zzfd.zza(v14, v2, zzax.zzf(v12, v10));
                                        goto label_109;
                                    label_74:
                                        if(v7 == 0) {
                                            v0 = zzax.zzb(v12, v10, v11);
                                            if(v11.zzfe != 0) {
                                            }
                                            else {
                                                v6_1 = false;
                                            }

                                            zzfd.zza(v14, v2, v6_1);
                                            continue;
                                        label_45:
                                            if(v7 != 0) {
                                                goto label_194;
                                            }

                                            v0 = zzax.zza(v12, v10, v11);
                                            v1 = zzbk.zzm(v11.zzfd);
                                            goto label_96;
                                        label_111:
                                            if(v7 != 1) {
                                                goto label_194;
                                            }

                                            zzfd.zza(v14, v2, zzax.zze(v12, v10));
                                            goto label_114;
                                        label_50:
                                            if(v7 != 0) {
                                                goto label_194;
                                            }

                                            goto label_94;
                                        label_83:
                                            if(v7 != v0) {
                                                goto label_194;
                                            }

                                            v9.putInt(v14, v2, zzax.zzc(v12, v10));
                                        label_109:
                                            v0 = v10 + 4;
                                            continue;
                                        label_52:
                                            if(v7 != v1) {
                                                goto label_194;
                                            }

                                            v0 = zzax.zze(v12, v10, v11);
                                            goto label_54;
                                        label_87:
                                            if(v7 != 1) {
                                                goto label_194;
                                            }

                                            v9.putLong(arg24, v2, zzax.zzd(v12, v10));
                                        label_114:
                                            v0 = v10 + 8;
                                            continue;
                                        label_57:
                                            if(v7 != v1) {
                                                goto label_194;
                                            }

                                            v0 = zzds.zza(v15.zzad(v8), v12, v10, v13, v11);
                                            Object v1_1 = v9.getObject(v14, v2);
                                            if(v1_1 == null) {
                                            label_54:
                                                v1_1 = v11.zzff;
                                            }
                                            else {
                                                v1_1 = zzci.zza(v1_1, v11.zzff);
                                            }

                                            v9.putObject(v14, v2, v1_1);
                                            continue;
                                        label_93:
                                            if(v7 != 0) {
                                                goto label_194;
                                            }

                                        label_94:
                                            v0 = zzax.zza(v12, v10, v11);
                                            v1 = v11.zzfd;
                                        label_96:
                                            v9.putInt(v14, v2, v1);
                                            continue;
                                        }
                                        else {
                                            goto label_194;
                                        }
                                    }
                                    else {
                                        goto label_194;
                                    }
                                }
                                else {
                                    goto label_194;
                                }
                            }
                        }
                        else {
                            goto label_194;
                        }
                    }
                    else {
                        if(v4 == 27) {
                            if(v7 != v1) {
                                goto label_194;
                            }

                            Object v0_1 = v9.getObject(v14, v2);
                            if(!((zzcn)v0_1).zzu()) {
                                v1 = ((zzcn)v0_1).size();
                                if(v1 == 0) {
                                    v1 = 10;
                                }
                                else {
                                    v1 <<= 1;
                                }

                                v0_2 = ((zzcn)v0_1).zzi(v1);
                                v9.putObject(v14, v2, v0_2);
                            }

                            v0 = zzds.zza(v15.zzad(v8), v16, arg25, v10, arg27, v0_2, arg28);
                            continue;
                        }

                        if(v4 <= 49) {
                            v22 = v9;
                            v0 = this.zza(arg24, arg25, v10, arg27, v16, v6, v7, v8, ((long)v5), v4, v2, arg28);
                            if(v0 != v10) {
                                goto label_203;
                            }
                        }
                        else {
                            long v19 = v2;
                            v22 = v9;
                            v15_1 = v10;
                            int v9_1 = v4;
                            if(v9_1 != 50) {
                                v0 = this.zza(arg24, arg25, v15_1, arg27, v16, v6, v7, v5, v9_1, v19, v8, arg28);
                                if(v0 == v15_1) {
                                }
                                else {
                                    goto label_203;
                                }
                            }
                            else if(v7 == v1) {
                                v0 = this.zza(arg24, arg25, v15_1, arg27, v8, v6, v19, arg28);
                                if(v0 == v15_1) {
                                }
                                else {
                                    goto label_203;
                                }
                            }
                            else {
                                goto label_196;
                            }
                        }

                        v2_1 = v0;
                        goto label_197;
                    }
                }
                else {
                label_194:
                    v22 = v9;
                    v15_1 = v10;
                label_196:
                    v2_1 = v15_1;
                label_197:
                    v0 = zzds.zza(v16, arg25, v2_1, arg27, arg24, arg28);
                }

            label_203:
                v14 = arg24;
                v12 = arg25;
                v11 = arg28;
                v9 = v22;
                v13 = arg27;
                v15 = this;
            }

            if(v0 == v13) {
                return;
            }

            throw zzco.zzbo();
        }

        this.zza(arg24, arg25, arg26, arg27, 0, arg28);
    }

    private final zzef zzad(int arg4) {
        arg4 = arg4 / 4 << 1;
        Object v0 = this.zzmj[arg4];
        if(v0 != null) {
            return ((zzef)v0);
        }

        zzef v0_1 = zzea.zzcm().zze(this.zzmj[arg4 + 1]);
        this.zzmj[arg4] = v0_1;
        return v0_1;
    }

    private final Object zzae(int arg2) {
        return this.zzmj[arg2 / 4 << 1];
    }

    private final zzck zzaf(int arg2) {
        return this.zzmj[(arg2 / 4 << 1) + 1];
    }

    private final int zzag(int arg2) {
        return this.zzmi[arg2 + 1];
    }

    private final int zzah(int arg2) {
        return this.zzmi[arg2 + 2];
    }

    private final int zzai(int arg7) {
        int v0;
        int v1 = -1;
        if(arg7 >= this.zzmk) {
            if(arg7 < this.zzmm) {
                v0 = arg7 - this.zzmk << 2;
                if(this.zzmi[v0] == arg7) {
                    return v0;
                }
                else {
                    return v1;
                }
            }
            else if(arg7 <= this.zzml) {
                v0 = this.zzmm - this.zzmk;
                int v2 = this.zzmi.length / 4 - 1;
                while(v0 <= v2) {
                    int v3 = v2 + v0 >>> 1;
                    int v4 = v3 << 2;
                    int v5 = this.zzmi[v4];
                    if(arg7 == v5) {
                        return v4;
                    }
                    else {
                        if(arg7 < v5) {
                            v2 = v3 - 1;
                        }
                        else {
                            v0 = v3 + 1;
                        }

                        continue;
                    }

                    return v1;
                }
            }
        }

        return v1;
    }

    private final void zzb(Object arg4, int arg5) {
        if(this.zzmq) {
            return;
        }

        arg5 = this.zzah(arg5);
        long v1 = ((long)(arg5 & 1048575));
        zzfd.zza(arg4, v1, zzfd.zzj(arg4, v1) | 1 << (arg5 >>> 20));
    }

    private final void zzb(Object arg3, int arg4, int arg5) {
        zzfd.zza(arg3, ((long)(this.zzah(arg5) & 1048575)), arg4);
    }

    private final void zzb(Object arg20, zzfr arg21) {
        boolean v13_1;
        Object v4_1;
        int v18;
        int v9;
        Object v5;
        Iterator v3_1;
        zzds v0 = this;
        Object v1 = arg20;
        zzfr v2 = arg21;
        if(v0.zzmo) {
            zzby v3 = v0.zzmy.zza(v1);
            if(!v3.isEmpty()) {
                v3_1 = v3.iterator();
                v5 = v3_1.next();
            }
            else {
                goto label_12;
            }
        }
        else {
        label_12:
            v3_1 = null;
            v5 = null;
        }

        int v6 = -1;
        int v7 = v0.zzmi.length;
        Unsafe v8 = zzds.zzmh;
        Object v10 = v5;
        int v5_1 = 0;
        int v11 = 0;
        while(v5_1 < v7) {
            int v12 = v0.zzag(v5_1);
            int v13 = v0.zzmi[v5_1];
            int v14 = (267386880 & v12) >>> 20;
            int v16 = 1048575;
            if((v0.zzmq) || v14 > 17) {
                v18 = v5_1;
                v9 = 0;
            }
            else {
                int v15 = v0.zzmi[v5_1 + 2];
                v9 = v15 & v16;
                if(v9 != v6) {
                    v18 = v5_1;
                    v11 = v8.getInt(v1, ((long)v9));
                    v6 = v9;
                }
                else {
                    v18 = v5_1;
                }

                v9 = 1 << (v15 >>> 20);
            }

            while(v10 != null) {
                if(v0.zzmy.zza(((Map$Entry)v10)) > v13) {
                    break;
                }

                v0.zzmy.zza(v2, ((Map$Entry)v10));
                v10 = v3_1.hasNext() ? v3_1.next() : null;
            }

            long v4 = ((long)(v12 & v16));
            switch(v14) {
                case 0: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zza(v13, zzfd.zzn(v1, v4));
                    break;
                }
                case 1: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zza(v13, zzfd.zzm(v1, v4));
                    break;
                }
                case 2: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzi(v13, v8.getLong(v1, v4));
                    break;
                }
                case 3: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zza(v13, v8.getLong(v1, v4));
                    break;
                }
                case 4: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzc(v13, v8.getInt(v1, v4));
                    break;
                }
                case 5: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzc(v13, v8.getLong(v1, v4));
                    break;
                }
                case 6: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzf(v13, v8.getInt(v1, v4));
                    break;
                }
                case 7: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzb(v13, zzfd.zzl(v1, v4));
                    break;
                }
                case 8: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    zzds.zza(v13, v8.getObject(v1, v4), v2);
                    break;
                }
                case 9: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zza(v13, v8.getObject(v1, v4), v0.zzad(v12));
                    break;
                }
                case 10: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zza(v13, v8.getObject(v1, v4));
                    break;
                }
                case 11: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzd(v13, v8.getInt(v1, v4));
                    break;
                }
                case 12: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzn(v13, v8.getInt(v1, v4));
                    break;
                }
                case 13: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzm(v13, v8.getInt(v1, v4));
                    break;
                }
                case 14: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzj(v13, v8.getLong(v1, v4));
                    break;
                }
                case 15: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zze(v13, v8.getInt(v1, v4));
                    break;
                }
                case 16: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getLong(v1, v4));
                    break;
                }
                case 17: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getObject(v1, v4), v0.zzad(v12));
                    break;
                }
                case 18: {
                    v12 = v18;
                    zzeh.zza(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 19: {
                    v12 = v18;
                    zzeh.zzb(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 20: {
                    v12 = v18;
                    zzeh.zzc(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 21: {
                    v12 = v18;
                    zzeh.zzd(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 22: {
                    v12 = v18;
                    zzeh.zzh(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 23: {
                    v12 = v18;
                    zzeh.zzf(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 24: {
                    v12 = v18;
                    zzeh.zzk(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 25: {
                    v12 = v18;
                    zzeh.zzn(v0.zzmi[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 26: {
                    v12 = v18;
                    zzeh.zza(v0.zzmi[v12], v8.getObject(v1, v4), v2);
                    break;
                }
                case 27: {
                    v12 = v18;
                    zzeh.zza(v0.zzmi[v12], v8.getObject(v1, v4), v2, v0.zzad(v12));
                    break;
                }
                case 28: {
                    v12 = v18;
                    zzeh.zzb(v0.zzmi[v12], v8.getObject(v1, v4), v2);
                    break;
                }
                case 29: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzmi[v12];
                    goto label_316;
                }
                case 30: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzmi[v12];
                    goto label_309;
                }
                case 31: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzmi[v12];
                    goto label_302;
                }
                case 32: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzmi[v12];
                    goto label_295;
                }
                case 33: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzmi[v12];
                    goto label_288;
                }
                case 34: {
                    v12 = v18;
                    v9 = v0.zzmi[v12];
                    v4_1 = v8.getObject(v1, v4);
                    v13_1 = false;
                    goto label_282;
                }
                case 35: {
                    v12 = v18;
                    zzeh.zza(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 36: {
                    v12 = v18;
                    zzeh.zzb(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 37: {
                    v12 = v18;
                    zzeh.zzc(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 38: {
                    v12 = v18;
                    zzeh.zzd(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 39: {
                    v12 = v18;
                    zzeh.zzh(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 40: {
                    v12 = v18;
                    zzeh.zzf(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 41: {
                    v12 = v18;
                    zzeh.zzk(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 42: {
                    v12 = v18;
                    zzeh.zzn(v0.zzmi[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 43: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzmi[v12];
                label_316:
                    zzeh.zzi(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 44: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzmi[v12];
                label_309:
                    zzeh.zzm(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 45: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzmi[v12];
                label_302:
                    zzeh.zzl(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 46: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzmi[v12];
                label_295:
                    zzeh.zzg(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 47: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzmi[v12];
                label_288:
                    zzeh.zzj(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 48: {
                    v12 = v18;
                    v9 = v0.zzmi[v12];
                    v4_1 = v8.getObject(v1, v4);
                    v13_1 = true;
                label_282:
                    zzeh.zze(v9, ((List)v4_1), v2, v13_1);
                    break;
                }
                case 49: {
                    v12 = v18;
                    zzeh.zzb(v0.zzmi[v12], v8.getObject(v1, v4), v2, v0.zzad(v12));
                    break;
                }
                case 50: {
                    v12 = v18;
                    v0.zza(v2, v13, v8.getObject(v1, v4), v12);
                    break;
                }
                case 51: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zza(v13, zzds.zze(v1, v4));
                    break;
                }
                case 52: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zza(v13, zzds.zzf(v1, v4));
                    break;
                }
                case 53: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzi(v13, zzds.zzh(v1, v4));
                    break;
                }
                case 54: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zza(v13, zzds.zzh(v1, v4));
                    break;
                }
                case 55: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzc(v13, zzds.zzg(v1, v4));
                    break;
                }
                case 56: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzc(v13, zzds.zzh(v1, v4));
                    break;
                }
                case 57: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzf(v13, zzds.zzg(v1, v4));
                    break;
                }
                case 58: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzb(v13, zzds.zzi(v1, v4));
                    break;
                }
                case 59: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    zzds.zza(v13, v8.getObject(v1, v4), v2);
                    break;
                }
                case 60: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zza(v13, v8.getObject(v1, v4), v0.zzad(v12));
                    break;
                }
                case 61: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zza(v13, v8.getObject(v1, v4));
                    break;
                }
                case 62: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzd(v13, zzds.zzg(v1, v4));
                    break;
                }
                case 63: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzn(v13, zzds.zzg(v1, v4));
                    break;
                }
                case 64: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzm(v13, zzds.zzg(v1, v4));
                    break;
                }
                case 65: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzj(v13, zzds.zzh(v1, v4));
                    break;
                }
                case 66: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zze(v13, zzds.zzg(v1, v4));
                    break;
                }
                case 67: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzb(v13, zzds.zzh(v1, v4));
                    break;
                }
                case 68: {
                    v12 = v18;
                    if(!v0.zza(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getObject(v1, v4), v0.zzad(v12));
                    break;
                }
                default: {
                    v12 = v18;
                    break;
                }
            }

        label_521:
            v5_1 = v12 + 4;
        }

        while(v10 != null) {
            v0.zzmy.zza(v2, ((Map$Entry)v10));
            if(v3_1.hasNext()) {
                v10 = v3_1.next();
                continue;
            }

            v10 = null;
        }

        zzds.zza(v0.zzmx, v1, v2);
    }

    private final void zzb(Object arg5, Object arg6, int arg7) {
        int v0 = this.zzag(arg7);
        int v1 = this.zzmi[arg7];
        long v2 = ((long)(v0 & 1048575));
        if(!this.zza(arg6, v1, arg7)) {
            return;
        }

        Object v0_1 = zzfd.zzo(arg5, v2);
        arg6 = zzfd.zzo(arg6, v2);
        if(v0_1 != null && arg6 != null) {
            zzfd.zza(arg5, v2, zzci.zza(v0_1, arg6));
            this.zzb(arg5, v1, arg7);
            return;
        }

        if(arg6 != null) {
            zzfd.zza(arg5, v2, arg6);
            this.zzb(arg5, v1, arg7);
        }
    }

    public final void zzc(Object arg9) {
        int v2;
        int[] v0;
        int v1 = 0;
        if(this.zzmt != null) {
            v0 = this.zzmt;
            v2 = v0.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                long v4 = ((long)(this.zzag(v0[v3]) & 1048575));
                Object v6 = zzfd.zzo(arg9, v4);
                if(v6 != null) {
                    zzfd.zza(arg9, v4, this.zzmz.zzj(v6));
                }
            }
        }

        if(this.zzmu != null) {
            v0 = this.zzmu;
            v2 = v0.length;
            while(v1 < v2) {
                this.zzmw.zza(arg9, ((long)v0[v1]));
                ++v1;
            }
        }

        this.zzmx.zzc(arg9);
        if(this.zzmo) {
            this.zzmy.zzc(arg9);
        }
    }

    private final boolean zzc(Object arg1, Object arg2, int arg3) {
        if(this.zza(arg1, arg3) == this.zza(arg2, arg3)) {
            return 1;
        }

        return 0;
    }

    public final void zzc(Object arg7, Object arg8) {
        if(arg8 != null) {
            int v0;
            for(v0 = 0; v0 < this.zzmi.length; v0 += 4) {
                int v1 = this.zzag(v0);
                long v2 = ((long)(1048575 & v1));
                int v4 = this.zzmi[v0];
                switch((v1 & 267386880) >>> 20) {
                    case 0: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        zzfd.zza(arg7, v2, zzfd.zzn(arg8, v2));
                    label_94:
                        this.zzb(arg7, v0);
                        break;
                    }
                    case 1: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        zzfd.zza(arg7, v2, zzfd.zzm(arg8, v2));
                        goto label_94;
                    }
                    case 2: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                    label_82:
                        zzfd.zza(arg7, v2, zzfd.zzk(arg8, v2));
                        goto label_94;
                    }
                    case 3: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 4: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                    label_74:
                        zzfd.zza(arg7, v2, zzfd.zzj(arg8, v2));
                        goto label_94;
                    }
                    case 5: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 6: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 7: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        zzfd.zza(arg7, v2, zzfd.zzl(arg8, v2));
                        goto label_94;
                    }
                    case 8: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                    label_58:
                        zzfd.zza(arg7, v2, zzfd.zzo(arg8, v2));
                        goto label_94;
                    }
                    case 10: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_58;
                    }
                    case 11: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 12: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 13: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 14: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 15: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 16: {
                        if(!this.zza(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 9: 
                    case 17: {
                        this.zza(arg7, arg8, v0);
                        break;
                    }
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: 
                    case 22: 
                    case 23: 
                    case 24: 
                    case 25: 
                    case 26: 
                    case 27: 
                    case 28: 
                    case 29: 
                    case 30: 
                    case 31: 
                    case 32: 
                    case 33: 
                    case 34: 
                    case 35: 
                    case 36: 
                    case 37: 
                    case 38: 
                    case 39: 
                    case 40: 
                    case 41: 
                    case 42: 
                    case 43: 
                    case 44: 
                    case 45: 
                    case 46: 
                    case 47: 
                    case 48: 
                    case 49: {
                        this.zzmw.zza(arg7, arg8, v2);
                        break;
                    }
                    case 50: {
                        zzeh.zza(this.zzmz, arg7, arg8, v2);
                        break;
                    }
                    case 51: 
                    case 52: 
                    case 53: 
                    case 54: 
                    case 55: 
                    case 56: 
                    case 57: 
                    case 58: 
                    case 59: {
                        if(!this.zza(arg8, v4, v0)) {
                            goto label_95;
                        }

                    label_23:
                        zzfd.zza(arg7, v2, zzfd.zzo(arg8, v2));
                        this.zzb(arg7, v4, v0);
                        break;
                    }
                    case 61: 
                    case 62: 
                    case 63: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: {
                        if(!this.zza(arg8, v4, v0)) {
                            goto label_95;
                        }

                        goto label_23;
                    }
                    case 60: 
                    case 68: {
                        this.zzb(arg7, arg8, v0);
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_95:
            }

            if(!this.zzmq) {
                zzeh.zza(this.zzmx, arg7, arg8);
                if(this.zzmo) {
                    zzeh.zza(this.zzmy, arg7, arg8);
                }
            }

            return;
        }

        throw new NullPointerException();
    }

    private static List zzd(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1);
    }

    private static double zze(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1).doubleValue();
    }

    private static float zzf(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1).floatValue();
    }

    private static int zzg(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1).intValue();
    }

    private static long zzh(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1).longValue();
    }

    private static boolean zzi(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1).booleanValue();
    }

    public final int zzm(Object arg21) {
        int v16;
        int v4;
        int v5_1;
        int v15;
        int v14;
        int v13;
        int v12;
        Unsafe v2;
        zzds v0 = this;
        Object v1 = arg21;
        int v3 = 267386880;
        int v8 = 1048575;
        long v9 = 0;
        if(v0.zzmq) {
            v2 = zzds.zzmh;
            v12 = 0;
            v13 = 0;
            while(v12 < v0.zzmi.length) {
                v14 = v0.zzag(v12);
                v15 = (v14 & v3) >>> 20;
                v3 = v0.zzmi[v12];
                long v5 = ((long)(v14 & v8));
                v14 = v15 < zzcb.zzih.id() || v15 > zzcb.zziu.id() ? 0 : v0.zzmi[v12 + 2] & v8;
                switch(v15) {
                    case 0: {
                        goto label_325;
                    }
                    case 1: {
                        goto label_321;
                    }
                    case 2: {
                        goto label_316;
                    }
                    case 3: {
                        goto label_311;
                    }
                    case 4: {
                        goto label_306;
                    }
                    case 5: {
                        goto label_302;
                    }
                    case 6: {
                        goto label_298;
                    }
                    case 7: {
                        goto label_294;
                    }
                    case 8: {
                        goto label_286;
                    }
                    case 9: {
                        goto label_280;
                    }
                    case 10: {
                        goto label_275;
                    }
                    case 11: {
                        goto label_270;
                    }
                    case 12: {
                        goto label_265;
                    }
                    case 13: {
                        goto label_261;
                    }
                    case 14: {
                        goto label_257;
                    }
                    case 15: {
                        goto label_252;
                    }
                    case 16: {
                        goto label_247;
                    }
                    case 17: {
                        goto label_241;
                    }
                    case 20: {
                        goto label_231;
                    }
                    case 21: {
                        goto label_228;
                    }
                    case 22: {
                        goto label_225;
                    }
                    case 25: {
                        goto label_222;
                    }
                    case 26: {
                        goto label_219;
                    }
                    case 27: {
                        goto label_215;
                    }
                    case 28: {
                        goto label_212;
                    }
                    case 29: {
                        goto label_209;
                    }
                    case 30: {
                        goto label_206;
                    }
                    case 19: 
                    case 24: 
                    case 31: {
                        goto label_234;
                    }
                    case 18: 
                    case 23: 
                    case 32: {
                        goto label_237;
                    }
                    case 33: {
                        goto label_203;
                    }
                    case 34: {
                        goto label_200;
                    }
                    case 35: {
                        goto label_188;
                    }
                    case 36: {
                        goto label_182;
                    }
                    case 37: {
                        goto label_176;
                    }
                    case 38: {
                        goto label_170;
                    }
                    case 39: {
                        goto label_164;
                    }
                    case 40: {
                        goto label_158;
                    }
                    case 41: {
                        goto label_152;
                    }
                    case 42: {
                        goto label_146;
                    }
                    case 43: {
                        goto label_140;
                    }
                    case 44: {
                        goto label_134;
                    }
                    case 45: {
                        goto label_128;
                    }
                    case 46: {
                        goto label_122;
                    }
                    case 47: {
                        goto label_116;
                    }
                    case 48: {
                        goto label_110;
                    }
                    case 49: {
                        goto label_106;
                    }
                    case 50: {
                        goto label_101;
                    }
                    case 51: {
                        goto label_98;
                    }
                    case 52: {
                        goto label_95;
                    }
                    case 53: {
                        goto label_91;
                    }
                    case 54: {
                        goto label_87;
                    }
                    case 55: {
                        goto label_83;
                    }
                    case 56: {
                        goto label_80;
                    }
                    case 57: {
                        goto label_77;
                    }
                    case 58: {
                        goto label_74;
                    }
                    case 59: {
                        goto label_68;
                    }
                    case 60: {
                        goto label_65;
                    }
                    case 61: {
                        goto label_62;
                    }
                    case 62: {
                        goto label_58;
                    }
                    case 63: {
                        goto label_54;
                    }
                    case 64: {
                        goto label_51;
                    }
                    case 65: {
                        goto label_48;
                    }
                    case 66: {
                        goto label_44;
                    }
                    case 67: {
                        goto label_40;
                    }
                    case 68: {
                        goto label_37;
                    }
                }

                goto label_330;
            label_225:
                v3 = zzeh.zzs(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_98:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                goto label_327;
            label_228:
                v3 = zzeh.zzp(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_101:
                v3 = v0.zzmz.zzb(v3, zzfd.zzo(v1, v5), v0.zzae(v12));
                goto label_239;
            label_231:
                v3 = zzeh.zzo(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_234:
                v3 = zzeh.zzv(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_106:
                v3 = zzeh.zzd(v3, zzds.zzd(v1, v5), v0.zzad(v12));
                goto label_239;
            label_237:
                v3 = zzeh.zzw(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_110:
                v5_1 = zzeh.zzc(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_241:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_243;
            label_116:
                v5_1 = zzeh.zzg(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_247:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                v5 = zzfd.zzk(v1, v5);
                goto label_250;
            label_122:
                v5_1 = zzeh.zzi(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_252:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzfd.zzj(v1, v5);
                goto label_255;
            label_128:
                v5_1 = zzeh.zzh(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_257:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_259;
            label_261:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_263;
            label_134:
                v5_1 = zzeh.zzd(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_265:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzfd.zzj(v1, v5);
                goto label_268;
            label_140:
                v5_1 = zzeh.zzf(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_270:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzfd.zzj(v1, v5);
                goto label_273;
            label_146:
                v5_1 = zzeh.zzj(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_275:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_277;
            label_280:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_282;
            label_152:
                v5_1 = zzeh.zzh(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_286:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                Object v5_2 = zzfd.zzo(v1, v5);
                if(!(v5_2 instanceof zzbb)) {
                    goto label_292;
                }

                goto label_278;
            label_158:
                v5_1 = zzeh.zzi(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_164:
                v5_1 = zzeh.zze(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_37:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_243:
                v3 = zzbn.zzc(v3, zzfd.zzo(v1, v5), v0.zzad(v12));
                goto label_239;
            label_294:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_296;
            label_40:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5 = zzds.zzh(v1, v5);
            label_250:
                v3 = zzbn.zzf(v3, v5);
                goto label_239;
            label_298:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_300;
            label_170:
                v5_1 = zzeh.zzb(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_44:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzds.zzg(v1, v5);
            label_255:
                v3 = zzbn.zzi(v3, v5_1);
                goto label_239;
            label_302:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_304;
            label_176:
                v5_1 = zzeh.zza(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_48:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_259:
                v3 = zzbn.zzh(v3, v9);
                goto label_239;
            label_306:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzfd.zzj(v1, v5);
                goto label_309;
            label_51:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_263:
                v3 = zzbn.zzk(v3, 0);
                goto label_239;
            label_182:
                v5_1 = zzeh.zzh(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzmr) {
                    goto label_195;
                }

                goto label_193;
            label_54:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzds.zzg(v1, v5);
            label_268:
                v3 = zzbn.zzl(v3, v5_1);
                goto label_239;
            label_311:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                v5 = zzfd.zzk(v1, v5);
                goto label_314;
            label_58:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzds.zzg(v1, v5);
            label_273:
                v3 = zzbn.zzh(v3, v5_1);
                goto label_239;
            label_316:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                v5 = zzfd.zzk(v1, v5);
                goto label_319;
            label_188:
                v5_1 = zzeh.zzi(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(v0.zzmr) {
                label_193:
                    v2.putInt(v1, ((long)v14), v5_1);
                }

            label_195:
                v3 = zzbn.zzr(v3) + zzbn.zzt(v5_1) + v5_1;
                goto label_239;
            label_62:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_277:
                v5_2 = zzfd.zzo(v1, v5);
                goto label_278;
            label_321:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

                goto label_323;
            label_65:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_282:
                v3 = zzeh.zzc(v3, zzfd.zzo(v1, v5), v0.zzad(v12));
                goto label_239;
            label_68:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5_2 = zzfd.zzo(v1, v5);
                if((v5_2 instanceof zzbb)) {
                label_278:
                    v3 = zzbn.zzc(v3, ((zzbb)v5_2));
                    goto label_239;
                }

            label_292:
                v3 = zzbn.zzb(v3, ((String)v5_2));
                goto label_239;
            label_325:
                if(!v0.zza(v1, v12)) {
                    goto label_330;
                }

            label_327:
                v3 = zzbn.zzb(v3, 0);
                goto label_239;
            label_200:
                v3 = zzeh.zzq(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_74:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_296:
                v3 = zzbn.zzc(v3, true);
                goto label_239;
            label_203:
                v3 = zzeh.zzu(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_77:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_300:
                v3 = zzbn.zzj(v3, 0);
                goto label_239;
            label_206:
                v3 = zzeh.zzr(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_80:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

            label_304:
                v3 = zzbn.zzg(v3, v9);
                goto label_239;
            label_209:
                v3 = zzeh.zzt(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_83:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzds.zzg(v1, v5);
            label_309:
                v3 = zzbn.zzg(v3, v5_1);
                goto label_239;
            label_212:
                v3 = zzeh.zzd(v3, zzds.zzd(v1, v5));
                goto label_239;
            label_215:
                v3 = zzeh.zzc(v3, zzds.zzd(v1, v5), v0.zzad(v12));
                goto label_239;
            label_87:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5 = zzds.zzh(v1, v5);
            label_314:
                v3 = zzbn.zze(v3, v5);
                goto label_239;
            label_219:
                v3 = zzeh.zzc(v3, zzds.zzd(v1, v5));
                goto label_239;
            label_91:
                if(!v0.zza(v1, v3, v12)) {
                    goto label_330;
                }

                v5 = zzds.zzh(v1, v5);
            label_319:
                v3 = zzbn.zzd(v3, v5);
                goto label_239;
            label_222:
                v3 = zzeh.zzx(v3, zzds.zzd(v1, v5), false);
                goto label_239;
            label_95:
                if(v0.zza(v1, v3, v12)) {
                label_323:
                    v3 = zzbn.zzb(v3, 0f);
                label_239:
                    v13 += v3;
                }

            label_330:
                v12 += 4;
                v3 = 267386880;
            }

            return v13 + zzds.zza(v0.zzmx, v1);
        }

        v2 = zzds.zzmh;
        v3 = 0;
        v5_1 = 0;
        int v6 = -1;
        v12 = 0;
        while(v3 < v0.zzmi.length) {
            v13 = v0.zzag(v3);
            v14 = v0.zzmi[v3];
            v15 = (v13 & 267386880) >>> 20;
            if(v15 <= 17) {
                v4 = v0.zzmi[v3 + 2];
                int v11 = v4 & v8;
                v16 = 1 << (v4 >>> 20);
                if(v11 != v6) {
                    v12 = v2.getInt(v1, ((long)v11));
                    v6 = v11;
                }
            }
            else {
                v4 = !v0.zzmr || v15 < zzcb.zzih.id() || v15 > zzcb.zziu.id() ? 0 : v0.zzmi[v3 + 2] & v8;
                v16 = 0;
            }

            v9 = ((long)(v13 & v8));
            switch(v15) {
                case 0: {
                    goto label_720;
                }
                case 1: {
                    goto label_711;
                }
                case 2: {
                    goto label_702;
                }
                case 3: {
                    goto label_695;
                }
                case 4: {
                    goto label_688;
                }
                case 5: {
                    goto label_680;
                }
                case 6: {
                    goto label_674;
                }
                case 7: {
                    goto label_670;
                }
                case 8: {
                    goto label_662;
                }
                case 9: {
                    goto label_656;
                }
                case 10: {
                    goto label_651;
                }
                case 11: {
                    goto label_646;
                }
                case 12: {
                    goto label_641;
                }
                case 13: {
                    goto label_635;
                }
                case 14: {
                    goto label_630;
                }
                case 15: {
                    goto label_625;
                }
                case 16: {
                    goto label_620;
                }
                case 17: {
                    goto label_614;
                }
                case 20: {
                    goto label_597;
                }
                case 21: {
                    goto label_593;
                }
                case 22: {
                    goto label_589;
                }
                case 25: {
                    goto label_585;
                }
                case 26: {
                    goto label_582;
                }
                case 27: {
                    goto label_578;
                }
                case 28: {
                    goto label_575;
                }
                case 29: {
                    goto label_571;
                }
                case 30: {
                    goto label_567;
                }
                case 19: 
                case 24: 
                case 31: {
                    goto label_601;
                }
                case 18: 
                case 23: 
                case 32: {
                    goto label_605;
                }
                case 33: {
                    goto label_563;
                }
                case 34: {
                    goto label_559;
                }
                case 35: {
                    goto label_547;
                }
                case 36: {
                    goto label_541;
                }
                case 37: {
                    goto label_535;
                }
                case 38: {
                    goto label_529;
                }
                case 39: {
                    goto label_523;
                }
                case 40: {
                    goto label_517;
                }
                case 41: {
                    goto label_511;
                }
                case 42: {
                    goto label_505;
                }
                case 43: {
                    goto label_499;
                }
                case 44: {
                    goto label_493;
                }
                case 45: {
                    goto label_487;
                }
                case 46: {
                    goto label_481;
                }
                case 47: {
                    goto label_475;
                }
                case 48: {
                    goto label_469;
                }
                case 49: {
                    goto label_465;
                }
                case 50: {
                    goto label_460;
                }
                case 51: {
                    goto label_455;
                }
                case 52: {
                    goto label_450;
                }
                case 53: {
                    goto label_445;
                }
                case 54: {
                    goto label_440;
                }
                case 55: {
                    goto label_435;
                }
                case 56: {
                    goto label_430;
                }
                case 57: {
                    goto label_425;
                }
                case 58: {
                    goto label_422;
                }
                case 59: {
                    goto label_416;
                }
                case 60: {
                    goto label_413;
                }
                case 61: {
                    goto label_410;
                }
                case 62: {
                    goto label_406;
                }
                case 63: {
                    goto label_402;
                }
                case 64: {
                    goto label_399;
                }
                case 65: {
                    goto label_396;
                }
                case 66: {
                    goto label_392;
                }
                case 67: {
                    goto label_388;
                }
                case 68: {
                    goto label_385;
                }
            }

            goto label_728;
        label_481:
            int v9_1 = zzeh.zzi(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_614:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_616;
        label_487:
            v9_1 = zzeh.zzh(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_620:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v9 = v2.getLong(v1, v9);
            goto label_623;
        label_493:
            v9_1 = zzeh.zzd(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_625:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v4 = v2.getInt(v1, v9);
            goto label_628;
        label_499:
            v9_1 = zzeh.zzf(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_630:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_632;
        label_505:
            v9_1 = zzeh.zzj(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_635:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_637;
        label_511:
            v9_1 = zzeh.zzh(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_641:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v4 = v2.getInt(v1, v9);
            goto label_644;
        label_385:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

        label_616:
            v4 = zzbn.zzc(v14, v2.getObject(v1, v9), v0.zzad(v3));
            goto label_608;
        label_388:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v9 = zzds.zzh(v1, v9);
        label_623:
            v4 = zzbn.zzf(v14, v9);
            goto label_608;
        label_517:
            v9_1 = zzeh.zzi(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_646:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v4 = v2.getInt(v1, v9);
            goto label_649;
        label_392:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzds.zzg(v1, v9);
        label_628:
            v4 = zzbn.zzi(v14, v4);
            goto label_608;
        label_651:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_653;
        label_523:
            v9_1 = zzeh.zze(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_396:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

        label_632:
            v4 = zzbn.zzh(v14, 0);
            goto label_608;
        label_399:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

        label_637:
            v9_1 = zzbn.zzk(v14, 0);
            goto label_639;
        label_656:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_658;
        label_529:
            v9_1 = zzeh.zzb(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_402:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzds.zzg(v1, v9);
        label_644:
            v4 = zzbn.zzl(v14, v4);
            goto label_608;
        label_662:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            Object v4_1 = v2.getObject(v1, v9);
            if(!(v4_1 instanceof zzbb)) {
                goto label_668;
            }

            goto label_654;
        label_406:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzds.zzg(v1, v9);
        label_649:
            v4 = zzbn.zzh(v14, v4);
            goto label_608;
        label_535:
            v9_1 = zzeh.zza(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_410:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

        label_653:
            v4_1 = v2.getObject(v1, v9);
            goto label_654;
        label_541:
            v9_1 = zzeh.zzh(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_413:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

        label_658:
            v4 = zzeh.zzc(v14, v2.getObject(v1, v9), v0.zzad(v3));
            goto label_608;
        label_670:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_672;
        label_416:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4_1 = v2.getObject(v1, v9);
            if((v4_1 instanceof zzbb)) {
            label_654:
                v4 = zzbn.zzc(v14, ((zzbb)v4_1));
                goto label_608;
            }

        label_668:
            v4 = zzbn.zzb(v14, ((String)v4_1));
            goto label_608;
        label_674:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzbn.zzj(v14, 0);
            goto label_728;
        label_547:
            v9_1 = zzeh.zzi(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_422:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

        label_672:
            v4 = zzbn.zzc(v14, true);
            goto label_608;
        label_680:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzbn.zzg(v14, 0);
            goto label_728;
        label_425:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v9_1 = zzbn.zzj(v14, 0);
            goto label_639;
        label_430:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzbn.zzg(v14, 0);
            goto label_608;
        label_559:
            v4 = zzeh.zzq(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_688:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v9_1 = zzbn.zzg(v14, v2.getInt(v1, v9));
            goto label_708;
        label_563:
            v4 = zzeh.zzu(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_435:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzbn.zzg(v14, zzds.zzg(v1, v9));
            goto label_608;
        label_695:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v9_1 = zzbn.zze(v14, v2.getLong(v1, v9));
            goto label_708;
        label_567:
            v4 = zzeh.zzr(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_440:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzbn.zze(v14, zzds.zzh(v1, v9));
            goto label_608;
        label_571:
            v4 = zzeh.zzt(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_445:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzbn.zzd(v14, zzds.zzh(v1, v9));
            goto label_608;
        label_702:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v9_1 = zzbn.zzd(v14, v2.getLong(v1, v9));
        label_708:
            v5_1 += v9_1;
            goto label_728;
        label_575:
            v4 = zzeh.zzd(v14, v2.getObject(v1, v9));
            goto label_608;
        label_578:
            v4 = zzeh.zzc(v14, v2.getObject(v1, v9), v0.zzad(v3));
            goto label_608;
        label_450:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v9_1 = zzbn.zzb(v14, 0f);
        label_639:
            v5_1 += v9_1;
            goto label_728;
        label_582:
            v4 = zzeh.zzc(v14, v2.getObject(v1, v9));
            goto label_608;
        label_711:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzbn.zzb(v14, 0f);
            goto label_728;
        label_455:
            if(!v0.zza(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzbn.zzb(v14, 0);
            goto label_608;
        label_585:
            v4 = zzeh.zzx(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_460:
            v4 = v0.zzmz.zzb(v14, v2.getObject(v1, v9), v0.zzae(v3));
            goto label_608;
        label_589:
            v4 = zzeh.zzs(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_720:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzbn.zzb(v14, 0);
            goto label_728;
        label_593:
            v4 = zzeh.zzp(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_465:
            v4 = zzeh.zzd(v14, v2.getObject(v1, v9), v0.zzad(v3));
            goto label_608;
        label_597:
            v4 = zzeh.zzo(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_469:
            v9_1 = zzeh.zzc(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzmr) {
                goto label_554;
            }

            goto label_552;
        label_601:
            v4 = zzeh.zzv(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_475:
            v9_1 = zzeh.zzg(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(v0.zzmr) {
            label_552:
                v2.putInt(v1, ((long)v4), v9_1);
            }

        label_554:
            v4 = zzbn.zzr(v14) + zzbn.zzt(v9_1) + v9_1;
            goto label_608;
        label_605:
            v4 = zzeh.zzw(v14, v2.getObject(v1, v9), false);
        label_608:
            v5_1 += v4;
        label_728:
            v3 += 4;
        }

        v5_1 += zzds.zza(v0.zzmx, v1);
        if(v0.zzmo) {
            v5_1 += v0.zzmy.zza(v1).zzas();
        }

        return v5_1;
    }

    private static zzey zzn(Object arg2) {
        zzey v0 = ((zzcg)arg2).zzjp;
        if(v0 == zzey.zzea()) {
            v0 = zzey.zzeb();
            ((zzcg)arg2).zzjp = v0;
        }

        return v0;
    }

    public final boolean zzo(Object arg17) {
        zzef v4_1;
        int[] v15;
        int v12;
        zzds v0 = this;
        Object v1 = arg17;
        int v3 = 1;
        if(v0.zzms != null) {
            if(v0.zzms.length == 0) {
            }
            else {
                int[] v4 = v0.zzms;
                int v5 = v4.length;
                int v2 = 0;
                int v7 = -1;
                int v8 = 0;
                while(v2 < v5) {
                    int v9 = v4[v2];
                    int v10 = v0.zzai(v9);
                    int v11 = v0.zzag(v10);
                    int v13 = 1048575;
                    if(!v0.zzmq) {
                        int v14 = v0.zzmi[v10 + 2] & v13;
                        v12 = v3 << (v0.zzmi[v10 + 2] >>> 20);
                        if(v14 != v7) {
                            v15 = v4;
                            v8 = zzds.zzmh.getInt(v1, ((long)v14));
                            v7 = v14;
                        }
                        else {
                            v15 = v4;
                        }
                    }
                    else {
                        v15 = v4;
                        v12 = 0;
                    }

                    v3 = (268435456 & v11) != 0 ? 1 : 0;
                    if(v3 != 0 && !v0.zza(v1, v10, v8, v12)) {
                        return 0;
                    }

                    v3 = (267386880 & v11) >>> 20;
                    if(v3 == 9 || v3 == 17) {
                        if(!v0.zza(v1, v10, v8, v12)) {
                            goto label_128;
                        }

                        if(zzds.zza(v1, v11, v0.zzad(v10))) {
                            goto label_128;
                        }

                        return 0;
                    }
                    else {
                        if(v3 != 27) {
                            if(v3 != 60 && v3 != 68) {
                                switch(v3) {
                                    case 49: {
                                        goto label_103;
                                    }
                                    case 50: {
                                        goto label_66;
                                    }
                                }

                                goto label_128;
                            label_66:
                                Map v3_1 = v0.zzmz.zzh(zzfd.zzo(v1, ((long)(v11 & v13))));
                                if((v3_1.isEmpty()) || v0.zzmz.zzl(v0.zzae(v10)).zzmd.zzek() != zzfq.zzrf) {
                                label_94:
                                    v3 = 1;
                                }
                                else {
                                    v4_1 = null;
                                    Iterator v3_2 = v3_1.values().iterator();
                                    do {
                                        if(v3_2.hasNext()) {
                                            Object v9_1 = v3_2.next();
                                            if(v4_1 == null) {
                                                v4_1 = zzea.zzcm().zze(v9_1.getClass());
                                            }

                                            if(v4_1.zzo(v9_1)) {
                                                continue;
                                            }

                                            break;
                                        }
                                        else {
                                            goto label_94;
                                        }
                                    }
                                    while(true);

                                    v3 = 0;
                                }

                                if(v3 != 0) {
                                    goto label_128;
                                }

                                return 0;
                            }

                            if(!v0.zza(v1, v9, v10)) {
                                goto label_128;
                            }

                            if(zzds.zza(v1, v11, v0.zzad(v10))) {
                                goto label_128;
                            }

                            return 0;
                        }

                    label_103:
                        Object v3_3 = zzfd.zzo(v1, ((long)(v11 & v13)));
                        if(!((List)v3_3).isEmpty()) {
                            v4_1 = v0.zzad(v10);
                            v9 = 0;
                            while(true) {
                                if(v9 >= ((List)v3_3).size()) {
                                    goto label_119;
                                }
                                else if(!v4_1.zzo(((List)v3_3).get(v9))) {
                                    v3 = 0;
                                }
                                else {
                                    ++v9;
                                    continue;
                                }

                                break;
                            }
                        }
                        else {
                        label_119:
                            v3 = 1;
                        }

                        if(v3 != 0) {
                            goto label_128;
                        }

                        return 0;
                    }

                label_128:
                    ++v2;
                    v4 = v15;
                    v3 = 1;
                }

                if((v0.zzmo) && !v0.zzmy.zza(v1).isInitialized()) {
                    return 0;
                }

                return 1;
            }
        }

        return 1;
    }
}

