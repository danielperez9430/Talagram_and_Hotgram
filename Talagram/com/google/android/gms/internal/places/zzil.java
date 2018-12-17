package com.google.android.gms.internal.places;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import sun.misc.Unsafe;

final class zzil implements zziy {
    private static final Unsafe zzuz;
    private final int[] zzva;
    private final Object[] zzvb;
    private final int zzvc;
    private final int zzvd;
    private final int zzve;
    private final zzih zzvf;
    private final boolean zzvg;
    private final boolean zzvh;
    private final boolean zzvi;
    private final boolean zzvj;
    private final int[] zzvk;
    private final int[] zzvl;
    private final int[] zzvm;
    private final zzio zzvn;
    private final zzhr zzvo;
    private final zzjq zzvp;
    private final zzgm zzvq;
    private final zzic zzvr;

    static {
        zzil.zzuz = zzjw.zzgu();
    }

    private zzil(int[] arg6, Object[] arg7, int arg8, int arg9, int arg10, zzih arg11, boolean arg12, boolean arg13, int[] arg14, int[] arg15, int[] arg16, zzio arg17, zzhr arg18, zzjq arg19, zzgm arg20, zzic arg21) {
        zzil v0 = this;
        zzih v1 = arg11;
        zzgm v2 = arg20;
        super();
        v0.zzva = arg6;
        v0.zzvb = arg7;
        v0.zzvc = arg8;
        v0.zzvd = arg9;
        v0.zzve = arg10;
        v0.zzvh = v1 instanceof zzgz;
        v0.zzvi = arg12;
        boolean v4 = v2 == null || !v2.zzf(arg11) ? false : true;
        v0.zzvg = v4;
        v0.zzvj = false;
        v0.zzvk = arg14;
        v0.zzvl = arg15;
        v0.zzvm = arg16;
        v0.zzvn = arg17;
        v0.zzvo = arg18;
        v0.zzvp = arg19;
        v0.zzvq = v2;
        v0.zzvf = v1;
        v0.zzvr = arg21;
    }

    public final boolean equals(Object arg10, Object arg11) {
        int v0 = this.zzva.length;
        int v2;
        for(v2 = 0; true; v2 += 4) {
            boolean v3 = true;
            if(v2 >= v0) {
                break;
            }

            int v4 = this.zzbi(v2);
            int v5 = 1048575;
            long v6 = ((long)(v4 & v5));
            switch((v4 & 267386880) >>> 20) {
                case 0: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzm(arg10, v6) == zzjw.zzm(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 1: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzl(arg10, v6) == zzjw.zzl(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 2: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzm(arg10, v6) == zzjw.zzm(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 3: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzm(arg10, v6) == zzjw.zzm(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 4: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzl(arg10, v6) == zzjw.zzl(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 5: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzm(arg10, v6) == zzjw.zzm(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 6: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzl(arg10, v6) == zzjw.zzl(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 7: {
                    if((this.zzd(arg10, arg11, v2)) && zzjw.zzn(arg10, v6) == zzjw.zzn(arg11, v6)) {
                        goto label_142;
                    }

                label_141:
                    v3 = false;
                    break;
                }
                case 8: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzja.zze(zzjw.zzq(arg10, v6), zzjw.zzq(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 9: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzja.zze(zzjw.zzq(arg10, v6), zzjw.zzq(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 10: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzja.zze(zzjw.zzq(arg10, v6), zzjw.zzq(arg11, v6))) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 11: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzl(arg10, v6) == zzjw.zzl(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 12: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzl(arg10, v6) == zzjw.zzl(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 13: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzl(arg10, v6) == zzjw.zzl(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 14: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzm(arg10, v6) == zzjw.zzm(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 15: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzl(arg10, v6) == zzjw.zzl(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 16: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzjw.zzm(arg10, v6) == zzjw.zzm(arg11, v6)) {
                        goto label_142;
                    }

                    goto label_141;
                }
                case 17: {
                    if(!this.zzd(arg10, arg11, v2)) {
                        goto label_141;
                    }

                    if(zzja.zze(zzjw.zzq(arg10, v6), zzjw.zzq(arg11, v6))) {
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
                    v3 = zzja.zze(zzjw.zzq(arg10, v6), zzjw.zzq(arg11, v6));
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
                    long v4_1 = ((long)(this.zzbj(v2) & v5));
                    if(zzjw.zzl(arg10, v4_1) != zzjw.zzl(arg11, v4_1)) {
                        goto label_141;
                    }

                    if(zzja.zze(zzjw.zzq(arg10, v6), zzjw.zzq(arg11, v6))) {
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

        if(!this.zzvp.zzq(arg10).equals(this.zzvp.zzq(arg11))) {
            return 0;
        }

        if(this.zzvg) {
            return this.zzvq.zzb(arg10).equals(this.zzvq.zzb(arg11));
        }

        return 1;
    }

    public final int hashCode(Object arg9) {
        int v0 = this.zzva.length;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            int v3 = this.zzbi(v1);
            int v4 = this.zzva[v1];
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
            boolean v3_1 = zzjw.zzn(arg9, v5);
            goto label_102;
        label_104:
            v2 *= 53;
            v3 = zzjw.zzl(arg9, v5);
            goto label_118;
        label_107:
            v2 *= 53;
            long v3_2 = zzjw.zzm(arg9, v5);
            goto label_117;
        label_110:
            v2 *= 53;
            float v3_3 = zzjw.zzo(arg9, v5);
            goto label_112;
        label_114:
            v2 *= 53;
            double v3_4 = zzjw.zzp(arg9, v5);
            goto label_116;
        label_17:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_43;
        label_20:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_23:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_26:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_29:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_32:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_35:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_38:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_86;
        label_41:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

        label_43:
            Object v3_5 = zzjw.zzq(arg9, v5);
            v2 *= 53;
            goto label_88;
        label_46:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_96;
        label_49:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            v2 *= 53;
            v3_1 = zzil.zzk(arg9, v5);
        label_102:
            v3 = zzhb.zzf(v3_1);
            goto label_118;
        label_54:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_62;
        label_57:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_60:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

        label_62:
            v2 *= 53;
            v3 = zzil.zzi(arg9, v5);
            goto label_118;
        label_65:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            goto label_70;
        label_68:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

        label_70:
            v2 *= 53;
            v3_2 = zzil.zzj(arg9, v5);
            goto label_117;
        label_73:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            v2 *= 53;
            v3_3 = zzil.zzh(arg9, v5);
        label_112:
            v3 = Float.floatToIntBits(v3_3);
            goto label_118;
        label_78:
            if(!this.zzb(arg9, v4, v1)) {
                goto label_119;
            }

            v2 *= 53;
            v3_4 = zzil.zzg(arg9, v5);
        label_116:
            v3_2 = Double.doubleToLongBits(v3_4);
        label_117:
            v3 = zzhb.zzo(v3_2);
            goto label_118;
        label_83:
            v3_5 = zzjw.zzq(arg9, v5);
            if(v3_5 == null) {
                goto label_93;
            }

            goto label_92;
        label_86:
            v2 *= 53;
            v3_5 = zzjw.zzq(arg9, v5);
        label_88:
            v3 = v3_5.hashCode();
            goto label_118;
        label_90:
            v3_5 = zzjw.zzq(arg9, v5);
            if(v3_5 != null) {
            label_92:
                v7 = v3_5.hashCode();
            }

        label_93:
            v2 = v2 * 53 + v7;
            goto label_119;
        label_96:
            v2 *= 53;
            v3 = zzjw.zzq(arg9, v5).hashCode();
        label_118:
            v2 += v3;
        label_119:
            v1 += 4;
        }

        v2 = v2 * 53 + this.zzvp.zzq(arg9).hashCode();
        if(this.zzvg) {
            v2 = v2 * 53 + this.zzvq.zzb(arg9).hashCode();
        }

        return v2;
    }

    public final Object newInstance() {
        return this.zzvn.newInstance(this.zzvf);
    }

    static zzil zzb(Class arg22, zzif arg23, zzio arg24, zzhr arg25, zzjq arg26, zzgm arg27, zzic arg28) {
        int v14;
        int v2;
        int v9;
        int v8;
        int v5;
        zzif v0 = arg23;
        if((v0 instanceof zziu)) {
            boolean v12 = ((zziu)v0).zzev() == zzh.zzte ? true : false;
            if(((zziu)v0).getFieldCount() == 0) {
                v5 = 0;
                v8 = 0;
                v9 = 0;
            }
            else {
                int v1 = ((zziu)v0).zzff();
                v2 = ((zziu)v0).zzfg();
                v5 = ((zziu)v0).zzfk();
                v8 = v1;
                v9 = v2;
            }

            int[] v6 = new int[v5 << 2];
            Object[] v7 = new Object[v5 << 1];
            int[] v2_1 = null;
            int[] v15 = ((zziu)v0).zzfh() > 0 ? new int[((zziu)v0).zzfh()] : v2_1;
            int[] v16 = ((zziu)v0).zzfi() > 0 ? new int[((zziu)v0).zzfi()] : v2_1;
            zziv v1_1 = ((zziu)v0).zzfe();
            if(v1_1.next()) {
                v2 = v1_1.zzbg();
                v5 = 0;
                int v10 = 0;
                int v11 = 0;
                while(true) {
                    if(v2 >= ((zziu)v0).zzfl() || v5 >= v2 - v8 << 2) {
                        if(v1_1.zzfp()) {
                            v2 = ((int)zzjw.zzb(v1_1.zzfq()));
                            v13 = ((int)zzjw.zzb(v1_1.zzfr()));
                            goto label_69;
                        }
                        else {
                            v2 = ((int)zzjw.zzb(v1_1.zzfs()));
                            if(v1_1.zzft()) {
                                v13 = ((int)zzjw.zzb(v1_1.zzfu()));
                                v14 = v1_1.zzfv();
                            }
                            else {
                                v13 = 0;
                            label_69:
                                v14 = 0;
                            }
                        }

                        v6[v5] = v1_1.zzbg();
                        int v17 = v5 + 1;
                        int v18 = v1_1.zzfx() ? 536870912 : 0;
                        int v19 = v1_1.zzfw() ? 268435456 : 0;
                        v6[v17] = v18 | v19 | v1_1.zzfn() << 20 | v2;
                        v6[v5 + 2] = v13 | v14 << 20;
                        if(v1_1.zzga() != null) {
                            v2 = v5 / 4 << 1;
                            v7[v2] = v1_1.zzga();
                            if(v1_1.zzfy() != null) {
                                v7[v2 + 1] = v1_1.zzfy();
                            }
                            else if(v1_1.zzfz() != null) {
                                v7[v2 + 1] = v1_1.zzfz();
                            }
                        }
                        else if(v1_1.zzfy() != null) {
                            v7[(v5 / 4 << 1) + 1] = v1_1.zzfy();
                        }
                        else if(v1_1.zzfz() != null) {
                            v7[(v5 / 4 << 1) + 1] = v1_1.zzfz();
                        }

                        v2 = v1_1.zzfn();
                        if(v2 == zzgt.zzrm.ordinal()) {
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

                        v2 = v1_1.zzbg();
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

            return new zzil(v6, v7, v8, v9, ((zziu)v0).zzfl(), ((zziu)v0).zzex(), v12, false, ((zziu)v0).zzfj(), v15, v16, arg24, arg25, arg26, arg27, arg28);
        }

        ((zzjl)v0).zzev();
        throw new NoSuchMethodError();
    }

    private static int zzb(zzjq arg0, Object arg1) {
        return arg0.zzn(arg0.zzq(arg1));
    }

    private final Object zzb(int arg5, int arg6, Map arg7, zzhd arg8, Object arg9, zzjq arg10) {
        zzia v5 = this.zzvr.zzm(this.zzbg(arg5));
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            if(arg8.zzi(((Map$Entry)v0).getValue().intValue()) != null) {
                continue;
            }

            if(arg9 == null) {
                arg9 = arg10.zzgo();
            }

            zzfw v1 = zzfr.zzag(zzhz.zzb(v5, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue()));
            zzgf v2 = v1.zzci();
            try {
                zzhz.zzb(v2, v5, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
            }
            catch(IOException v5_1) {
                throw new RuntimeException(((Throwable)v5_1));
            }

            arg10.zzb(arg9, arg6, v1.zzch());
            v7.remove();
        }

        return arg9;
    }

    private final Object zzb(Object arg9, int arg10, Object arg11, zzjq arg12) {
        int v3 = this.zzva[arg10];
        arg9 = zzjw.zzq(arg9, ((long)(this.zzbi(arg10) & 1048575)));
        if(arg9 == null) {
            return arg11;
        }

        zzhd v5 = this.zzbh(arg10);
        if(v5 == null) {
            return arg11;
        }

        return this.zzb(arg10, v3, this.zzvr.zzh(arg9), v5, arg11, arg12);
    }

    private static void zzb(int arg1, Object arg2, zzkk arg3) {
        if((arg2 instanceof String)) {
            arg3.zzb(arg1, ((String)arg2));
            return;
        }

        arg3.zzb(arg1, ((zzfr)arg2));
    }

    private static void zzb(zzjq arg0, Object arg1, zzkk arg2) {
        arg0.zzb(arg0.zzq(arg1), arg2);
    }

    private final void zzb(zzkk arg2, int arg3, Object arg4, int arg5) {
        if(arg4 != null) {
            arg2.zzb(arg3, this.zzvr.zzm(this.zzbg(arg5)), this.zzvr.zzi(arg4));
        }
    }

    private final void zzb(Object arg3, int arg4, zzix arg5) {
        zzfr v4_1;
        String v4;
        long v0;
        int v1 = 1048575;
        if(zzil.zzbk(arg4)) {
            v0 = ((long)(arg4 & v1));
            v4 = arg5.zzbo();
        }
        else if(this.zzvh) {
            v0 = ((long)(arg4 & v1));
            v4 = arg5.readString();
        }
        else {
            v0 = ((long)(arg4 & v1));
            v4_1 = arg5.zzbp();
        }

        zzjw.zzb(arg3, v0, v4_1);
    }

    private final void zzb(Object arg4, Object arg5, int arg6) {
        long v0 = ((long)(this.zzbi(arg6) & 1048575));
        if(!this.zzb(arg5, arg6)) {
            return;
        }

        Object v2 = zzjw.zzq(arg4, v0);
        arg5 = zzjw.zzq(arg5, v0);
        if(v2 != null && arg5 != null) {
            zzjw.zzb(arg4, v0, zzhb.zzb(v2, arg5));
            this.zzc(arg4, arg6);
            return;
        }

        if(arg5 != null) {
            zzjw.zzb(arg4, v0, arg5);
            this.zzc(arg4, arg6);
        }
    }

    private final boolean zzb(Object arg7, int arg8) {
        int v1 = 1048575;
        if(!this.zzvi) {
            goto label_103;
        }

        arg8 = this.zzbi(arg8);
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
        if(zzjw.zzp(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_36:
        if(zzjw.zzl(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_71:
        return zzjw.zzn(arg7, v0);
    label_40:
        if(zzjw.zzl(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_73:
        if(zzjw.zzl(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_44:
        if(!zzfr.zznt.equals(zzjw.zzq(arg7, v0))) {
            return 1;
        }

        return 0;
    label_77:
        if(zzjw.zzm(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_16:
        if(zzjw.zzq(arg7, v0) != null) {
            return 1;
        }

        return 0;
    label_81:
        if(zzjw.zzl(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_50:
        if(zzjw.zzq(arg7, v0) != null) {
            return 1;
        }

        return 0;
    label_20:
        if(zzjw.zzm(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_85:
        if(zzjw.zzm(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_54:
        arg7 = zzjw.zzq(arg7, v0);
        if((arg7 instanceof String)) {
            if(!((String)arg7).isEmpty()) {
                return 1;
            }

            return 0;
        }

        if((arg7 instanceof zzfr)) {
            if(!zzfr.zznt.equals(arg7)) {
                return 1;
            }

            return 0;
        }

        throw new IllegalArgumentException();
    label_24:
        if(zzjw.zzl(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_89:
        if(zzjw.zzm(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_28:
        if(zzjw.zzm(arg7, v0) != v4) {
            return 1;
        }

        return 0;
    label_93:
        if(zzjw.zzo(arg7, v0) != 0f) {
            return 1;
        }

        return 0;
    label_32:
        if(zzjw.zzl(arg7, v0) != 0) {
            return 1;
        }

        return 0;
    label_103:
        arg8 = this.zzbj(arg8);
        if((zzjw.zzl(arg7, ((long)(arg8 & v1))) & 1 << (arg8 >>> 20)) != 0) {
            return 1;
        }

        return 0;
    }

    private final boolean zzb(Object arg3, int arg4, int arg5) {
        if(zzjw.zzl(arg3, ((long)(this.zzbj(arg5) & 1048575))) == arg4) {
            return 1;
        }

        return 0;
    }

    private final boolean zzb(Object arg2, int arg3, int arg4, int arg5) {
        if(this.zzvi) {
            return this.zzb(arg2, arg3);
        }

        if((arg4 & arg5) != 0) {
            return 1;
        }

        return 0;
    }

    private static boolean zzb(Object arg2, int arg3, zziy arg4) {
        return arg4.zzo(zzjw.zzq(arg2, ((long)(arg3 & 1048575))));
    }

    public final void zzb(Object arg18, zzix arg19, zzgl arg20) {
        long v6_2;
        Object v4_1;
        int v8;
        int v6;
        int[] v0_2;
        int v5;
        int v4;
        int v15;
        zzil v1 = this;
        Object v2 = arg18;
        zzix v0 = arg19;
        zzgl v10 = arg20;
        if(v10 == null) {
            goto label_575;
        }

        zzjq v11 = v1.zzvp;
        zzgm v12 = v1.zzvq;
        zzgq v13 = null;
        zzgq v3 = v13;
        Object v14 = v3;
        do {
            v15 = 0;
            try {
                v4 = arg19.zzbg();
                v5 = v1.zzbl(v4);
                if(v5 >= 0) {
                    goto label_67;
                }
            }
            catch(Throwable v0_1) {
                goto label_564;
            }

            if(v4 != 2147483647) {
                goto label_28;
            }

            if(v1.zzvl != null) {
                v0_2 = v1.zzvl;
                int v3_1 = v0_2.length;
                goto label_20;
                try {
                label_28:
                    Object v5_1 = !v1.zzvg ? v13 : v12.zzb(v10, v1.zzvf, v4);
                    if(v5_1 != null) {
                        if(v3 == null) {
                            v3 = v12.zzc(v2);
                        }

                        v14 = v12.zzb(arg19, v5_1, arg20, v3, v14, v11);
                        v3 = v3;
                        continue;
                    }

                    v11.zzb(v0);
                    if(v14 == null) {
                        v14 = v11.zzr(v2);
                    }

                    if(v11.zzb(v14, v0)) {
                        continue;
                    }
                }
                catch(Throwable v0_1) {
                    goto label_564;
                }

                if(v1.zzvl != null) {
                    v0_2 = v1.zzvl;
                    v3_1 = v0_2.length;
                    goto label_59;
                    try {
                    label_67:
                        v6 = v1.zzbi(v5);
                        v8 = 1048575;
                        switch((267386880 & v6) >>> 20) {
                            case 0: {
                                goto label_524;
                            }
                            case 1: {
                                goto label_519;
                            }
                            case 2: {
                                goto label_514;
                            }
                            case 3: {
                                goto label_509;
                            }
                            case 4: {
                                goto label_504;
                            }
                            case 5: {
                                goto label_499;
                            }
                            case 6: {
                                goto label_494;
                            }
                            case 7: {
                                goto label_489;
                            }
                            case 8: {
                                goto label_487;
                            }
                            case 9: {
                                goto label_472;
                            }
                            case 10: {
                                goto label_467;
                            }
                            case 11: {
                                goto label_462;
                            }
                            case 12: {
                                goto label_453;
                            }
                            case 13: {
                                goto label_448;
                            }
                            case 14: {
                                goto label_443;
                            }
                            case 15: {
                                goto label_438;
                            }
                            case 16: {
                                goto label_433;
                            }
                            case 17: {
                                goto label_416;
                            }
                            case 18: {
                                goto label_411;
                            }
                            case 19: {
                                goto label_406;
                            }
                            case 20: {
                                goto label_401;
                            }
                            case 21: {
                                goto label_396;
                            }
                            case 22: {
                                goto label_391;
                            }
                            case 23: {
                                goto label_386;
                            }
                            case 24: {
                                goto label_381;
                            }
                            case 25: {
                                goto label_376;
                            }
                            case 26: {
                                goto label_362;
                            }
                            case 27: {
                                goto label_355;
                            }
                            case 28: {
                                goto label_349;
                            }
                            case 29: {
                                goto label_344;
                            }
                            case 30: {
                                goto label_335;
                            }
                            case 31: {
                                goto label_330;
                            }
                            case 32: {
                                goto label_325;
                            }
                            case 33: {
                                goto label_320;
                            }
                            case 34: {
                                goto label_315;
                            }
                            case 35: {
                                goto label_309;
                            }
                            case 36: {
                                goto label_303;
                            }
                            case 37: {
                                goto label_297;
                            }
                            case 38: {
                                goto label_291;
                            }
                            case 39: {
                                goto label_285;
                            }
                            case 40: {
                                goto label_279;
                            }
                            case 41: {
                                goto label_273;
                            }
                            case 42: {
                                goto label_267;
                            }
                            case 43: {
                                goto label_261;
                            }
                            case 44: {
                                goto label_254;
                            }
                            case 45: {
                                goto label_248;
                            }
                            case 46: {
                                goto label_242;
                            }
                            case 47: {
                                goto label_236;
                            }
                            case 48: {
                                goto label_230;
                            }
                            case 49: {
                                goto label_223;
                            }
                            case 50: {
                                goto label_198;
                            }
                            case 51: {
                                goto label_192;
                            }
                            case 52: {
                                goto label_186;
                            }
                            case 53: {
                                goto label_180;
                            }
                            case 54: {
                                goto label_174;
                            }
                            case 55: {
                                goto label_168;
                            }
                            case 56: {
                                goto label_162;
                            }
                            case 57: {
                                goto label_156;
                            }
                            case 58: {
                                goto label_150;
                            }
                            case 59: {
                                goto label_148;
                            }
                            case 60: {
                                goto label_131;
                            }
                            case 61: {
                                goto label_126;
                            }
                            case 62: {
                                goto label_120;
                            }
                            case 63: {
                                goto label_107;
                            }
                            case 64: {
                                goto label_101;
                            }
                            case 65: {
                                goto label_95;
                            }
                            case 66: {
                                goto label_89;
                            }
                            case 67: {
                                goto label_83;
                            }
                            case 68: {
                                goto label_76;
                            }
                        }
                    }
                    catch(Throwable v0_1) {
                        goto label_564;
                    }

                    if(v14 == null) {
                        try {
                            v4_1 = v11.zzgo();
                            v14 = v4_1;
                        label_530:
                            if(v11.zzb(v14, v0)) {
                                continue;
                            }

                            goto label_532;
                        }
                        catch(zzhi ) {
                            goto label_544;
                        }
                        catch(Throwable v0_1) {
                            goto label_564;
                        }
                    }

                    goto label_530;
                label_532:
                    if(v1.zzvl != null) {
                        v0_2 = v1.zzvl;
                        v3_1 = v0_2.length;
                        goto label_536;
                        try {
                        label_355:
                            v0.zzb(v1.zzvo.zzb(v2, ((long)(v6 & v8))), v1.zzbf(v5), v10);
                            continue;
                        label_101:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Integer.valueOf(arg19.zzbs()));
                            goto label_81;
                        label_230:
                            List v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_234;
                        label_487:
                            v1.zzb(v2, v6, v0);
                            goto label_431;
                        label_489:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbn());
                            goto label_431;
                        label_362:
                            if(zzil.zzbk(v6)) {
                                v0.zzm(v1.zzvo.zzb(v2, ((long)(v6 & v8))));
                                continue;
                            }

                            v0.readStringList(v1.zzvo.zzb(v2, ((long)(v6 & v8))));
                            continue;
                        label_107:
                            int v7 = arg19.zzbr();
                            zzhd v9 = v1.zzbh(v5);
                            if(v9 != null) {
                                if(v9.zzi(v7) != null) {
                                }
                                else {
                                    goto label_113;
                                }
                            }

                            zzjw.zzb(v2, ((long)(v6 & v8)), Integer.valueOf(v7));
                            goto label_81;
                        label_236:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_240;
                        label_494:
                            zzjw.zzc(v2, ((long)(v6 & v8)), arg19.zzbm());
                            goto label_431;
                        label_242:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_246;
                        label_499:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbl());
                            goto label_431;
                        label_504:
                            zzjw.zzc(v2, ((long)(v6 & v8)), arg19.zzbk());
                            goto label_431;
                        label_376:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_271;
                        label_248:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_252;
                        label_120:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Integer.valueOf(arg19.zzbq()));
                            goto label_81;
                        label_509:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbi());
                            goto label_431;
                        label_381:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_277;
                        label_254:
                            List v6_1 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            v0.zzp(v6_1);
                            zzhd v5_2 = v1.zzbh(v5);
                            goto label_341;
                        label_126:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbp());
                            goto label_81;
                        label_514:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbj());
                            goto label_431;
                        label_386:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_283;
                        label_131:
                            if(v1.zzb(v2, v4, v5)) {
                                v6_2 = ((long)(v6 & v8));
                                zzjw.zzb(v2, v6_2, zzhb.zzb(zzjw.zzq(v2, v6_2), v0.zzb(v1.zzbf(v5), v10)));
                                goto label_81;
                            }

                            zzjw.zzb(v2, ((long)(v6 & v8)), v0.zzb(v1.zzbf(v5), v10));
                            v1.zzc(v2, v5);
                            goto label_81;
                        label_261:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_265;
                        label_519:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.readFloat());
                            goto label_431;
                        label_391:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_289;
                        label_267:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_271:
                            v0.zzl(v4_2);
                            continue;
                        label_524:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.readDouble());
                            goto label_431;
                        label_396:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_295;
                        label_401:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_301;
                        label_273:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_277:
                            v0.zzk(v4_2);
                            continue;
                        label_148:
                            v1.zzb(v2, v6, v0);
                            goto label_81;
                        label_406:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_307;
                        label_150:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Boolean.valueOf(arg19.zzbn()));
                            goto label_81;
                        label_279:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_283:
                            v0.zzj(v4_2);
                            continue;
                        label_411:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            goto label_313;
                        label_156:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Integer.valueOf(arg19.zzbm()));
                            goto label_81;
                        label_285:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_289:
                            v0.zzi(v4_2);
                            continue;
                        label_416:
                            if(v1.zzb(v2, v5)) {
                                v6_2 = ((long)(v6 & v8));
                                v4_1 = zzhb.zzb(zzjw.zzq(v2, v6_2), v0.zzd(v1.zzbf(v5), v10));
                                goto label_424;
                            }

                            zzjw.zzb(v2, ((long)(v6 & v8)), v0.zzd(v1.zzbf(v5), v10));
                            goto label_431;
                        label_162:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Long.valueOf(arg19.zzbl()));
                            goto label_81;
                        label_291:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_295:
                            v0.zzg(v4_2);
                            continue;
                        label_168:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Integer.valueOf(arg19.zzbk()));
                            goto label_81;
                        label_297:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_301:
                            v0.zzh(v4_2);
                            continue;
                        label_174:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Long.valueOf(arg19.zzbi()));
                            goto label_81;
                        label_303:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_307:
                            v0.zzf(v4_2);
                            continue;
                        label_433:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbv());
                            goto label_431;
                        label_180:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Long.valueOf(arg19.zzbj()));
                            goto label_81;
                        label_309:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_313:
                            v0.zze(v4_2);
                            continue;
                        label_438:
                            zzjw.zzc(v2, ((long)(v6 & v8)), arg19.zzbu());
                            goto label_431;
                        label_186:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Float.valueOf(arg19.readFloat()));
                            goto label_81;
                        label_443:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbt());
                            goto label_431;
                        label_315:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_234:
                            v0.zzt(v4_2);
                            continue;
                        label_448:
                            zzjw.zzc(v2, ((long)(v6 & v8)), arg19.zzbs());
                            goto label_431;
                        label_320:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_240:
                            v0.zzs(v4_2);
                            continue;
                        label_192:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Double.valueOf(arg19.readDouble()));
                            goto label_81;
                        label_453:
                            v7 = arg19.zzbr();
                            v9 = v1.zzbh(v5);
                            if(v9 != null && v9.zzi(v7) == null) {
                            label_113:
                                v4_1 = zzja.zzb(v4, v7, v14, v11);
                                goto label_342;
                            }

                            zzjw.zzc(v2, ((long)(v6 & v8)), v7);
                            goto label_431;
                        label_325:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_246:
                            v0.zzr(v4_2);
                            continue;
                        label_198:
                            v4_1 = v1.zzbg(v5);
                            long v5_3 = ((long)(v1.zzbi(v5) & v8));
                            Object v7_1 = zzjw.zzq(v2, v5_3);
                            if(v7_1 == null) {
                                v7_1 = v1.zzvr.zzl(v4_1);
                                zzjw.zzb(v2, v5_3, v7_1);
                            }
                            else if(v1.zzvr.zzj(v7_1)) {
                                Object v8_1 = v1.zzvr.zzl(v4_1);
                                v1.zzvr.zzc(v8_1, v7_1);
                                zzjw.zzb(v2, v5_3, v8_1);
                                v7_1 = v8_1;
                            }

                            v0.zzb(v1.zzvr.zzh(v7_1), v1.zzvr.zzm(v4_1), v10);
                            continue;
                        label_330:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_252:
                            v0.zzq(v4_2);
                            continue;
                        label_76:
                            zzjw.zzb(v2, ((long)(v6 & v8)), v0.zzd(v1.zzbf(v5), v10));
                            goto label_81;
                        label_462:
                            zzjw.zzc(v2, ((long)(v6 & v8)), arg19.zzbq());
                            goto label_431;
                        label_335:
                            v6_1 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                            v0.zzp(v6_1);
                            v5_2 = v1.zzbh(v5);
                        label_341:
                            v4_1 = zzja.zzb(v4, v6_1, v5_2, v14, v11);
                        label_342:
                            v14 = v4_1;
                            continue;
                        label_467:
                            zzjw.zzb(v2, ((long)(v6 & v8)), arg19.zzbp());
                            goto label_431;
                        label_83:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Long.valueOf(arg19.zzbv()));
                            goto label_81;
                        label_472:
                            if(v1.zzb(v2, v5)) {
                                v6_2 = ((long)(v6 & v8));
                                v4_1 = zzhb.zzb(zzjw.zzq(v2, v6_2), v0.zzb(v1.zzbf(v5), v10));
                            label_424:
                                zzjw.zzb(v2, v6_2, v4_1);
                                continue;
                            }

                            zzjw.zzb(v2, ((long)(v6 & v8)), v0.zzb(v1.zzbf(v5), v10));
                        label_431:
                            v1.zzc(v2, v5);
                            continue;
                        label_344:
                            v4_2 = v1.zzvo.zzb(v2, ((long)(v6 & v8)));
                        label_265:
                            v0.zzo(v4_2);
                            continue;
                        label_89:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Integer.valueOf(arg19.zzbu()));
                            goto label_81;
                        label_349:
                            v0.zzn(v1.zzvo.zzb(v2, ((long)(v6 & v8))));
                            continue;
                        label_223:
                            v0.zzc(v1.zzvo.zzb(v2, ((long)(v6 & v8))), v1.zzbf(v5), v10);
                            continue;
                        label_95:
                            zzjw.zzb(v2, ((long)(v6 & v8)), Long.valueOf(arg19.zzbt()));
                        label_81:
                            v1.zzc(v2, v4, v5);
                            continue;
                        }
                        catch(Throwable v0_1) {
                        label_564:
                            if(v1.zzvl != null) {
                                int[] v3_2 = v1.zzvl;
                                v4 = v3_2.length;
                                while(v15 < v4) {
                                    v14 = v1.zzb(v2, v3_2[v15], v14, v11);
                                    ++v15;
                                }
                            }

                            if(v14 != null) {
                                v11.zzg(v2, v14);
                            }

                            throw v0_1;
                        }
                        catch(zzhi ) {
                            try {
                            label_544:
                                v11.zzb(v0);
                                if(v14 == null) {
                                    v14 = v11.zzr(v2);
                                }

                                if(v11.zzb(v14, v0)) {
                                    continue;
                                }
                            }
                            catch(Throwable v0_1) {
                                goto label_564;
                            }

                            if(v1.zzvl != null) {
                                v0_2 = v1.zzvl;
                                v3_1 = v0_2.length;
                                break;
                            }

                            goto label_559;
                        }
                    }

                    goto label_541;
                }

                goto label_64;
            }

            goto label_25;
        }
        while(true);

        while(v15 < v3_1) {
            v14 = v1.zzb(v2, v0_2[v15], v14, v11);
            ++v15;
        }

    label_559:
        if(v14 != null) {
            v11.zzg(v2, v14);
        }

        return;
    label_536:
        while(v15 < v3_1) {
            v14 = v1.zzb(v2, v0_2[v15], v14, v11);
            ++v15;
        }

    label_541:
        if(v14 != null) {
            v11.zzg(v2, v14);
        }

        return;
    label_59:
        while(v15 < v3_1) {
            v14 = v1.zzb(v2, v0_2[v15], v14, v11);
            ++v15;
        }

    label_64:
        if(v14 != null) {
            v11.zzg(v2, v14);
        }

        return;
    label_20:
        while(v15 < v3_1) {
            v14 = v1.zzb(v2, v0_2[v15], v14, v11);
            ++v15;
        }

    label_25:
        if(v14 != null) {
            v11.zzg(v2, v14);
        }

        return;
    label_575:
        throw new NullPointerException();
    }

    public final void zzb(Object arg14, zzkk arg15) {
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
        zzgq v0;
        int v2 = 267386880;
        Object v3 = null;
        int v6 = 1048575;
        if(arg15.zzcv() == zzh.zzth) {
            zzil.zzb(this.zzvp, arg14, arg15);
            if(this.zzvg) {
                v0 = this.zzvq.zzb(arg14);
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
            for(v7 = this.zzva.length - 4; v7 >= 0; v7 += -4) {
                int v8 = this.zzbi(v7);
                v9 = this.zzva[v7];
                while(v1 != null) {
                    if(this.zzvq.zzb(((Map$Entry)v1)) <= v9) {
                        break;
                    }

                    this.zzvq.zzb(arg15, ((Map$Entry)v1));
                    v1 = v0_1.hasNext() ? v0_1.next() : v3;
                }

                switch((v8 & v2) >>> 20) {
                    case 0: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v10_1 = zzjw.zzp(arg14, ((long)(v8 & v6)));
                        goto label_497;
                    }
                    case 1: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8_1 = zzjw.zzo(arg14, ((long)(v8 & v6)));
                        goto label_490;
                    }
                    case 2: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzjw.zzm(arg14, ((long)(v8 & v6)));
                    label_483:
                        arg15.zzj(v9, v10);
                        break;
                    }
                    case 3: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzjw.zzm(arg14, ((long)(v8 & v6)));
                    label_476:
                        arg15.zzb(v9, v10);
                        break;
                    }
                    case 4: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzjw.zzl(arg14, ((long)(v8 & v6)));
                    label_469:
                        arg15.zze(v9, v8);
                        break;
                    }
                    case 5: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzjw.zzm(arg14, ((long)(v8 & v6)));
                    label_462:
                        arg15.zzd(v9, v10);
                        break;
                    }
                    case 6: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzjw.zzl(arg14, ((long)(v8 & v6)));
                    label_455:
                        arg15.zzh(v9, v8);
                        break;
                    }
                    case 7: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8_2 = zzjw.zzn(arg14, ((long)(v8 & v6)));
                        goto label_448;
                    }
                    case 8: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_438;
                    }
                    case 9: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_430;
                    }
                    case 10: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_423;
                    }
                    case 11: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzjw.zzl(arg14, ((long)(v8 & v6)));
                        goto label_419;
                    }
                    case 12: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzjw.zzl(arg14, ((long)(v8 & v6)));
                        goto label_412;
                    }
                    case 13: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzjw.zzl(arg14, ((long)(v8 & v6)));
                        goto label_405;
                    }
                    case 14: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzjw.zzm(arg14, ((long)(v8 & v6)));
                        goto label_398;
                    }
                    case 15: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v8 = zzjw.zzl(arg14, ((long)(v8 & v6)));
                        goto label_391;
                    }
                    case 16: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        v10 = zzjw.zzm(arg14, ((long)(v8 & v6)));
                        goto label_384;
                    }
                    case 17: {
                        if(!this.zzb(arg14, v7)) {
                            goto label_498;
                        }

                        goto label_373;
                    }
                    case 18: {
                        zzja.zzb(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 19: {
                        zzja.zzc(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 20: {
                        zzja.zzd(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 21: {
                        zzja.zze(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 22: {
                        zzja.zzi(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 23: {
                        zzja.zzg(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 24: {
                        zzja.zzl(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 25: {
                        zzja.zzo(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 26: {
                        zzja.zzb(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15);
                        break;
                    }
                    case 27: {
                        zzja.zzb(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, this.zzbf(v7));
                        break;
                    }
                    case 28: {
                        zzja.zzc(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15);
                        break;
                    }
                    case 29: {
                        zzja.zzj(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 30: {
                        zzja.zzn(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 31: {
                        zzja.zzm(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 32: {
                        zzja.zzh(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 33: {
                        zzja.zzk(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 34: {
                        zzja.zzf(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, false);
                        break;
                    }
                    case 35: {
                        zzja.zzb(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 36: {
                        zzja.zzc(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 37: {
                        zzja.zzd(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 38: {
                        zzja.zze(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 39: {
                        zzja.zzi(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 40: {
                        zzja.zzg(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 41: {
                        zzja.zzl(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 42: {
                        zzja.zzo(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 43: {
                        zzja.zzj(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 44: {
                        zzja.zzn(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 45: {
                        zzja.zzm(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 46: {
                        zzja.zzh(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 47: {
                        zzja.zzk(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 48: {
                        zzja.zzf(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, true);
                        break;
                    }
                    case 49: {
                        zzja.zzc(this.zzva[v7], zzjw.zzq(arg14, ((long)(v8 & v6))), arg15, this.zzbf(v7));
                        break;
                    }
                    case 50: {
                        this.zzb(arg15, v9, zzjw.zzq(arg14, ((long)(v8 & v6))), v7);
                        break;
                    }
                    case 51: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10_1 = zzil.zzg(arg14, ((long)(v8 & v6)));
                    label_497:
                        arg15.zzb(v9, v10_1);
                        break;
                    }
                    case 52: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8_1 = zzil.zzh(arg14, ((long)(v8 & v6)));
                    label_490:
                        arg15.zzc(v9, v8_1);
                        break;
                    }
                    case 53: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzil.zzj(arg14, ((long)(v8 & v6)));
                        goto label_483;
                    }
                    case 54: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzil.zzj(arg14, ((long)(v8 & v6)));
                        goto label_476;
                    }
                    case 55: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzil.zzi(arg14, ((long)(v8 & v6)));
                        goto label_469;
                    }
                    case 56: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzil.zzj(arg14, ((long)(v8 & v6)));
                        goto label_462;
                    }
                    case 57: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzil.zzi(arg14, ((long)(v8 & v6)));
                        goto label_455;
                    }
                    case 58: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8_2 = zzil.zzk(arg14, ((long)(v8 & v6)));
                    label_448:
                        arg15.zzc(v9, v8_2);
                        break;
                    }
                    case 59: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_438:
                        zzil.zzb(v9, zzjw.zzq(arg14, ((long)(v8 & v6))), arg15);
                        break;
                    }
                    case 60: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_430:
                        arg15.zzb(v9, zzjw.zzq(arg14, ((long)(v8 & v6))), this.zzbf(v7));
                        break;
                    }
                    case 61: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_423:
                        arg15.zzb(v9, zzjw.zzq(arg14, ((long)(v8 & v6))));
                        break;
                    }
                    case 62: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzil.zzi(arg14, ((long)(v8 & v6)));
                    label_419:
                        arg15.zzf(v9, v8);
                        break;
                    }
                    case 63: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzil.zzi(arg14, ((long)(v8 & v6)));
                    label_412:
                        arg15.zzp(v9, v8);
                        break;
                    }
                    case 64: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzil.zzi(arg14, ((long)(v8 & v6)));
                    label_405:
                        arg15.zzo(v9, v8);
                        break;
                    }
                    case 65: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzil.zzj(arg14, ((long)(v8 & v6)));
                    label_398:
                        arg15.zzk(v9, v10);
                        break;
                    }
                    case 66: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v8 = zzil.zzi(arg14, ((long)(v8 & v6)));
                    label_391:
                        arg15.zzg(v9, v8);
                        break;
                    }
                    case 67: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                        v10 = zzil.zzj(arg14, ((long)(v8 & v6)));
                    label_384:
                        arg15.zzc(v9, v10);
                        break;
                    }
                    case 68: {
                        if(!this.zzb(arg14, v9, v7)) {
                            goto label_498;
                        }

                    label_373:
                        arg15.zzc(v9, zzjw.zzq(arg14, ((long)(v8 & v6))), this.zzbf(v7));
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_498:
            }

            while(v1 != null) {
                this.zzvq.zzb(arg15, ((Map$Entry)v1));
                if(v0_1.hasNext()) {
                    v1 = v0_1.next();
                    continue;
                }

                v1 = v3;
            }

            return;
        }

        if(this.zzvi) {
            if(this.zzvg) {
                v0 = this.zzvq.zzb(arg14);
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

            v7 = this.zzva.length;
            Object v8_3 = v1;
            int v1_1;
            for(v1_1 = 0; v1_1 < v7; v1_1 += 4) {
                v9 = this.zzbi(v1_1);
                int v10_2 = this.zzva[v1_1];
                while(v8_3 != null) {
                    if(this.zzvq.zzb(((Map$Entry)v8_3)) > v10_2) {
                        break;
                    }

                    this.zzvq.zzb(arg15, ((Map$Entry)v8_3));
                    v8_3 = v0_1.hasNext() ? v0_1.next() : v3;
                }

                switch((v9 & v2) >>> 20) {
                    case 0: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11 = zzjw.zzp(arg14, ((long)(v9 & v6)));
                        goto label_1001;
                    }
                    case 1: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9_1 = zzjw.zzo(arg14, ((long)(v9 & v6)));
                    label_994:
                        arg15.zzc(v10_2, v9_1);
                        break;
                    }
                    case 2: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzjw.zzm(arg14, ((long)(v9 & v6)));
                    label_987:
                        arg15.zzj(v10_2, v11_1);
                        break;
                    }
                    case 3: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzjw.zzm(arg14, ((long)(v9 & v6)));
                    label_980:
                        arg15.zzb(v10_2, v11_1);
                        break;
                    }
                    case 4: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzjw.zzl(arg14, ((long)(v9 & v6)));
                    label_973:
                        arg15.zze(v10_2, v9);
                        break;
                    }
                    case 5: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzjw.zzm(arg14, ((long)(v9 & v6)));
                        goto label_966;
                    }
                    case 6: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzjw.zzl(arg14, ((long)(v9 & v6)));
                        goto label_959;
                    }
                    case 7: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9_2 = zzjw.zzn(arg14, ((long)(v9 & v6)));
                        goto label_952;
                    }
                    case 8: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_942;
                    }
                    case 9: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_934;
                    }
                    case 10: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_927;
                    }
                    case 11: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzjw.zzl(arg14, ((long)(v9 & v6)));
                        goto label_923;
                    }
                    case 12: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzjw.zzl(arg14, ((long)(v9 & v6)));
                        goto label_916;
                    }
                    case 13: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzjw.zzl(arg14, ((long)(v9 & v6)));
                        goto label_909;
                    }
                    case 14: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzjw.zzm(arg14, ((long)(v9 & v6)));
                        goto label_902;
                    }
                    case 15: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzjw.zzl(arg14, ((long)(v9 & v6)));
                        goto label_895;
                    }
                    case 16: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzjw.zzm(arg14, ((long)(v9 & v6)));
                        goto label_888;
                    }
                    case 17: {
                        if(!this.zzb(arg14, v1_1)) {
                            goto label_1002;
                        }

                        goto label_877;
                    }
                    case 18: {
                        zzja.zzb(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 19: {
                        zzja.zzc(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 20: {
                        zzja.zzd(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 21: {
                        zzja.zze(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 22: {
                        zzja.zzi(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 23: {
                        zzja.zzg(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 24: {
                        zzja.zzl(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 25: {
                        zzja.zzo(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 26: {
                        zzja.zzb(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15);
                        break;
                    }
                    case 27: {
                        zzja.zzb(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, this.zzbf(v1_1));
                        break;
                    }
                    case 28: {
                        zzja.zzc(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15);
                        break;
                    }
                    case 29: {
                        zzja.zzj(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 30: {
                        zzja.zzn(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 31: {
                        zzja.zzm(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 32: {
                        zzja.zzh(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 33: {
                        zzja.zzk(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 34: {
                        zzja.zzf(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, false);
                        break;
                    }
                    case 35: {
                        zzja.zzb(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 36: {
                        zzja.zzc(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 37: {
                        zzja.zzd(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 38: {
                        zzja.zze(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 39: {
                        zzja.zzi(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 40: {
                        zzja.zzg(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 41: {
                        zzja.zzl(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 42: {
                        zzja.zzo(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 43: {
                        zzja.zzj(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 44: {
                        zzja.zzn(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 45: {
                        zzja.zzm(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 46: {
                        zzja.zzh(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 47: {
                        zzja.zzk(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 48: {
                        zzja.zzf(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, true);
                        break;
                    }
                    case 49: {
                        zzja.zzc(this.zzva[v1_1], zzjw.zzq(arg14, ((long)(v9 & v6))), arg15, this.zzbf(v1_1));
                        break;
                    }
                    case 50: {
                        this.zzb(arg15, v10_2, zzjw.zzq(arg14, ((long)(v9 & v6))), v1_1);
                        break;
                    }
                    case 51: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11 = zzil.zzg(arg14, ((long)(v9 & v6)));
                    label_1001:
                        arg15.zzb(v10_2, v11);
                        break;
                    }
                    case 52: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9_1 = zzil.zzh(arg14, ((long)(v9 & v6)));
                        goto label_994;
                    }
                    case 53: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzil.zzj(arg14, ((long)(v9 & v6)));
                        goto label_987;
                    }
                    case 54: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzil.zzj(arg14, ((long)(v9 & v6)));
                        goto label_980;
                    }
                    case 55: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzil.zzi(arg14, ((long)(v9 & v6)));
                        goto label_973;
                    }
                    case 56: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzil.zzj(arg14, ((long)(v9 & v6)));
                    label_966:
                        arg15.zzd(v10_2, v11_1);
                        break;
                    }
                    case 57: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzil.zzi(arg14, ((long)(v9 & v6)));
                    label_959:
                        arg15.zzh(v10_2, v9);
                        break;
                    }
                    case 58: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9_2 = zzil.zzk(arg14, ((long)(v9 & v6)));
                    label_952:
                        arg15.zzc(v10_2, v9_2);
                        break;
                    }
                    case 59: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_942:
                        zzil.zzb(v10_2, zzjw.zzq(arg14, ((long)(v9 & v6))), arg15);
                        break;
                    }
                    case 60: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_934:
                        arg15.zzb(v10_2, zzjw.zzq(arg14, ((long)(v9 & v6))), this.zzbf(v1_1));
                        break;
                    }
                    case 61: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_927:
                        arg15.zzb(v10_2, zzjw.zzq(arg14, ((long)(v9 & v6))));
                        break;
                    }
                    case 62: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzil.zzi(arg14, ((long)(v9 & v6)));
                    label_923:
                        arg15.zzf(v10_2, v9);
                        break;
                    }
                    case 63: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzil.zzi(arg14, ((long)(v9 & v6)));
                    label_916:
                        arg15.zzp(v10_2, v9);
                        break;
                    }
                    case 64: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzil.zzi(arg14, ((long)(v9 & v6)));
                    label_909:
                        arg15.zzo(v10_2, v9);
                        break;
                    }
                    case 65: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzil.zzj(arg14, ((long)(v9 & v6)));
                    label_902:
                        arg15.zzk(v10_2, v11_1);
                        break;
                    }
                    case 66: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v9 = zzil.zzi(arg14, ((long)(v9 & v6)));
                    label_895:
                        arg15.zzg(v10_2, v9);
                        break;
                    }
                    case 67: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                        v11_1 = zzil.zzj(arg14, ((long)(v9 & v6)));
                    label_888:
                        arg15.zzc(v10_2, v11_1);
                        break;
                    }
                    case 68: {
                        if(!this.zzb(arg14, v10_2, v1_1)) {
                            goto label_1002;
                        }

                    label_877:
                        arg15.zzc(v10_2, zzjw.zzq(arg14, ((long)(v9 & v6))), this.zzbf(v1_1));
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_1002:
            }

            while(v8_3 != null) {
                this.zzvq.zzb(arg15, ((Map$Entry)v8_3));
                if(v0_1.hasNext()) {
                    v8_3 = v0_1.next();
                    continue;
                }

                v8_3 = v3;
            }

            zzil.zzb(this.zzvp, arg14, arg15);
            return;
        }

        this.zzc(arg14, arg15);
    }

    private final zziy zzbf(int arg4) {
        arg4 = arg4 / 4 << 1;
        Object v0 = this.zzvb[arg4];
        if(v0 != null) {
            return ((zziy)v0);
        }

        zziy v0_1 = zzis.zzfc().zzg(this.zzvb[arg4 + 1]);
        this.zzvb[arg4] = v0_1;
        return v0_1;
    }

    private final Object zzbg(int arg2) {
        return this.zzvb[arg2 / 4 << 1];
    }

    private final zzhd zzbh(int arg2) {
        return this.zzvb[(arg2 / 4 << 1) + 1];
    }

    private final int zzbi(int arg2) {
        return this.zzva[arg2 + 1];
    }

    private final int zzbj(int arg2) {
        return this.zzva[arg2 + 2];
    }

    private static boolean zzbk(int arg1) {
        if((arg1 & 536870912) != 0) {
            return 1;
        }

        return 0;
    }

    private final int zzbl(int arg7) {
        int v0;
        int v1 = -1;
        if(arg7 >= this.zzvc) {
            if(arg7 < this.zzve) {
                v0 = arg7 - this.zzvc << 2;
                if(this.zzva[v0] == arg7) {
                    return v0;
                }
                else {
                    return v1;
                }
            }
            else if(arg7 <= this.zzvd) {
                v0 = this.zzve - this.zzvc;
                int v2 = this.zzva.length / 4 - 1;
                while(v0 <= v2) {
                    int v3 = v2 + v0 >>> 1;
                    int v4 = v3 << 2;
                    int v5 = this.zzva[v4];
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

    private final void zzc(Object arg4, int arg5) {
        if(this.zzvi) {
            return;
        }

        arg5 = this.zzbj(arg5);
        long v1 = ((long)(arg5 & 1048575));
        zzjw.zzc(arg4, v1, zzjw.zzl(arg4, v1) | 1 << (arg5 >>> 20));
    }

    private final void zzc(Object arg3, int arg4, int arg5) {
        zzjw.zzc(arg3, ((long)(this.zzbj(arg5) & 1048575)), arg4);
    }

    private final void zzc(Object arg20, zzkk arg21) {
        boolean v13_1;
        Object v4_1;
        int v18;
        int v9;
        Object v5;
        Iterator v3_1;
        zzil v0 = this;
        Object v1 = arg20;
        zzkk v2 = arg21;
        if(v0.zzvg) {
            zzgq v3 = v0.zzvq.zzb(v1);
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
        int v7 = v0.zzva.length;
        Unsafe v8 = zzil.zzuz;
        Object v10 = v5;
        int v5_1 = 0;
        int v11 = 0;
        while(v5_1 < v7) {
            int v12 = v0.zzbi(v5_1);
            int v13 = v0.zzva[v5_1];
            int v14 = (267386880 & v12) >>> 20;
            int v16 = 1048575;
            if((v0.zzvi) || v14 > 17) {
                v18 = v5_1;
                v9 = 0;
            }
            else {
                int v15 = v0.zzva[v5_1 + 2];
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
                if(v0.zzvq.zzb(((Map$Entry)v10)) > v13) {
                    break;
                }

                v0.zzvq.zzb(v2, ((Map$Entry)v10));
                v10 = v3_1.hasNext() ? v3_1.next() : null;
            }

            long v4 = ((long)(v12 & v16));
            switch(v14) {
                case 0: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzb(v13, zzjw.zzp(v1, v4));
                    break;
                }
                case 1: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzc(v13, zzjw.zzo(v1, v4));
                    break;
                }
                case 2: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzj(v13, v8.getLong(v1, v4));
                    break;
                }
                case 3: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getLong(v1, v4));
                    break;
                }
                case 4: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zze(v13, v8.getInt(v1, v4));
                    break;
                }
                case 5: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzd(v13, v8.getLong(v1, v4));
                    break;
                }
                case 6: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzh(v13, v8.getInt(v1, v4));
                    break;
                }
                case 7: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzc(v13, zzjw.zzn(v1, v4));
                    break;
                }
                case 8: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    zzil.zzb(v13, v8.getObject(v1, v4), v2);
                    break;
                }
                case 9: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getObject(v1, v4), v0.zzbf(v12));
                    break;
                }
                case 10: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getObject(v1, v4));
                    break;
                }
                case 11: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzf(v13, v8.getInt(v1, v4));
                    break;
                }
                case 12: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzp(v13, v8.getInt(v1, v4));
                    break;
                }
                case 13: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzo(v13, v8.getInt(v1, v4));
                    break;
                }
                case 14: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzk(v13, v8.getLong(v1, v4));
                    break;
                }
                case 15: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzg(v13, v8.getInt(v1, v4));
                    break;
                }
                case 16: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzc(v13, v8.getLong(v1, v4));
                    break;
                }
                case 17: {
                    v12 = v18;
                    if((v9 & v11) == 0) {
                        goto label_521;
                    }

                    v2.zzc(v13, v8.getObject(v1, v4), v0.zzbf(v12));
                    break;
                }
                case 18: {
                    v12 = v18;
                    zzja.zzb(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 19: {
                    v12 = v18;
                    zzja.zzc(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 20: {
                    v12 = v18;
                    zzja.zzd(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 21: {
                    v12 = v18;
                    zzja.zze(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 22: {
                    v12 = v18;
                    zzja.zzi(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 23: {
                    v12 = v18;
                    zzja.zzg(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 24: {
                    v12 = v18;
                    zzja.zzl(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 25: {
                    v12 = v18;
                    zzja.zzo(v0.zzva[v12], v8.getObject(v1, v4), v2, false);
                    break;
                }
                case 26: {
                    v12 = v18;
                    zzja.zzb(v0.zzva[v12], v8.getObject(v1, v4), v2);
                    break;
                }
                case 27: {
                    v12 = v18;
                    zzja.zzb(v0.zzva[v12], v8.getObject(v1, v4), v2, v0.zzbf(v12));
                    break;
                }
                case 28: {
                    v12 = v18;
                    zzja.zzc(v0.zzva[v12], v8.getObject(v1, v4), v2);
                    break;
                }
                case 29: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzva[v12];
                    goto label_316;
                }
                case 30: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzva[v12];
                    goto label_309;
                }
                case 31: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzva[v12];
                    goto label_302;
                }
                case 32: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzva[v12];
                    goto label_295;
                }
                case 33: {
                    v12 = v18;
                    v13_1 = false;
                    v9 = v0.zzva[v12];
                    goto label_288;
                }
                case 34: {
                    v12 = v18;
                    v9 = v0.zzva[v12];
                    v4_1 = v8.getObject(v1, v4);
                    v13_1 = false;
                    goto label_282;
                }
                case 35: {
                    v12 = v18;
                    zzja.zzb(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 36: {
                    v12 = v18;
                    zzja.zzc(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 37: {
                    v12 = v18;
                    zzja.zzd(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 38: {
                    v12 = v18;
                    zzja.zze(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 39: {
                    v12 = v18;
                    zzja.zzi(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 40: {
                    v12 = v18;
                    zzja.zzg(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 41: {
                    v12 = v18;
                    zzja.zzl(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 42: {
                    v12 = v18;
                    zzja.zzo(v0.zzva[v12], v8.getObject(v1, v4), v2, true);
                    break;
                }
                case 43: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzva[v12];
                label_316:
                    zzja.zzj(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 44: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzva[v12];
                label_309:
                    zzja.zzn(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 45: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzva[v12];
                label_302:
                    zzja.zzm(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 46: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzva[v12];
                label_295:
                    zzja.zzh(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 47: {
                    v12 = v18;
                    v13_1 = true;
                    v9 = v0.zzva[v12];
                label_288:
                    zzja.zzk(v9, v8.getObject(v1, v4), v2, v13_1);
                    break;
                }
                case 48: {
                    v12 = v18;
                    v9 = v0.zzva[v12];
                    v4_1 = v8.getObject(v1, v4);
                    v13_1 = true;
                label_282:
                    zzja.zzf(v9, ((List)v4_1), v2, v13_1);
                    break;
                }
                case 49: {
                    v12 = v18;
                    zzja.zzc(v0.zzva[v12], v8.getObject(v1, v4), v2, v0.zzbf(v12));
                    break;
                }
                case 50: {
                    v12 = v18;
                    v0.zzb(v2, v13, v8.getObject(v1, v4), v12);
                    break;
                }
                case 51: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzb(v13, zzil.zzg(v1, v4));
                    break;
                }
                case 52: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzc(v13, zzil.zzh(v1, v4));
                    break;
                }
                case 53: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzj(v13, zzil.zzj(v1, v4));
                    break;
                }
                case 54: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzb(v13, zzil.zzj(v1, v4));
                    break;
                }
                case 55: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zze(v13, zzil.zzi(v1, v4));
                    break;
                }
                case 56: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzd(v13, zzil.zzj(v1, v4));
                    break;
                }
                case 57: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzh(v13, zzil.zzi(v1, v4));
                    break;
                }
                case 58: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzc(v13, zzil.zzk(v1, v4));
                    break;
                }
                case 59: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    zzil.zzb(v13, v8.getObject(v1, v4), v2);
                    break;
                }
                case 60: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getObject(v1, v4), v0.zzbf(v12));
                    break;
                }
                case 61: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzb(v13, v8.getObject(v1, v4));
                    break;
                }
                case 62: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzf(v13, zzil.zzi(v1, v4));
                    break;
                }
                case 63: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzp(v13, zzil.zzi(v1, v4));
                    break;
                }
                case 64: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzo(v13, zzil.zzi(v1, v4));
                    break;
                }
                case 65: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzk(v13, zzil.zzj(v1, v4));
                    break;
                }
                case 66: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzg(v13, zzil.zzi(v1, v4));
                    break;
                }
                case 67: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzc(v13, zzil.zzj(v1, v4));
                    break;
                }
                case 68: {
                    v12 = v18;
                    if(!v0.zzb(v1, v13, v12)) {
                        goto label_521;
                    }

                    v2.zzc(v13, v8.getObject(v1, v4), v0.zzbf(v12));
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
            v0.zzvq.zzb(v2, ((Map$Entry)v10));
            if(v3_1.hasNext()) {
                v10 = v3_1.next();
                continue;
            }

            v10 = null;
        }

        zzil.zzb(v0.zzvp, v1, v2);
    }

    private final void zzc(Object arg5, Object arg6, int arg7) {
        int v0 = this.zzbi(arg7);
        int v1 = this.zzva[arg7];
        long v2 = ((long)(v0 & 1048575));
        if(!this.zzb(arg6, v1, arg7)) {
            return;
        }

        Object v0_1 = zzjw.zzq(arg5, v2);
        arg6 = zzjw.zzq(arg6, v2);
        if(v0_1 != null && arg6 != null) {
            zzjw.zzb(arg5, v2, zzhb.zzb(v0_1, arg6));
            this.zzc(arg5, v1, arg7);
            return;
        }

        if(arg6 != null) {
            zzjw.zzb(arg5, v2, arg6);
            this.zzc(arg5, v1, arg7);
        }
    }

    private final boolean zzd(Object arg1, Object arg2, int arg3) {
        if(this.zzb(arg1, arg3) == this.zzb(arg2, arg3)) {
            return 1;
        }

        return 0;
    }

    public final void zzd(Object arg9) {
        int v2;
        int[] v0;
        int v1 = 0;
        if(this.zzvl != null) {
            v0 = this.zzvl;
            v2 = v0.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                long v4 = ((long)(this.zzbi(v0[v3]) & 1048575));
                Object v6 = zzjw.zzq(arg9, v4);
                if(v6 != null) {
                    zzjw.zzb(arg9, v4, this.zzvr.zzk(v6));
                }
            }
        }

        if(this.zzvm != null) {
            v0 = this.zzvm;
            v2 = v0.length;
            while(v1 < v2) {
                this.zzvo.zzc(arg9, ((long)v0[v1]));
                ++v1;
            }
        }

        this.zzvp.zzd(arg9);
        if(this.zzvg) {
            this.zzvq.zzd(arg9);
        }
    }

    public final void zzd(Object arg7, Object arg8) {
        if(arg8 != null) {
            int v0;
            for(v0 = 0; v0 < this.zzva.length; v0 += 4) {
                int v1 = this.zzbi(v0);
                long v2 = ((long)(1048575 & v1));
                int v4 = this.zzva[v0];
                switch((v1 & 267386880) >>> 20) {
                    case 0: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        zzjw.zzb(arg7, v2, zzjw.zzp(arg8, v2));
                    label_94:
                        this.zzc(arg7, v0);
                        break;
                    }
                    case 1: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        zzjw.zzb(arg7, v2, zzjw.zzo(arg8, v2));
                        goto label_94;
                    }
                    case 2: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                    label_82:
                        zzjw.zzb(arg7, v2, zzjw.zzm(arg8, v2));
                        goto label_94;
                    }
                    case 3: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 4: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                    label_74:
                        zzjw.zzc(arg7, v2, zzjw.zzl(arg8, v2));
                        goto label_94;
                    }
                    case 5: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 6: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 7: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        zzjw.zzb(arg7, v2, zzjw.zzn(arg8, v2));
                        goto label_94;
                    }
                    case 8: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                    label_58:
                        zzjw.zzb(arg7, v2, zzjw.zzq(arg8, v2));
                        goto label_94;
                    }
                    case 10: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_58;
                    }
                    case 11: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 12: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 13: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 14: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 15: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_74;
                    }
                    case 16: {
                        if(!this.zzb(arg8, v0)) {
                            goto label_95;
                        }

                        goto label_82;
                    }
                    case 9: 
                    case 17: {
                        this.zzb(arg7, arg8, v0);
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
                        this.zzvo.zzb(arg7, arg8, v2);
                        break;
                    }
                    case 50: {
                        zzja.zzb(this.zzvr, arg7, arg8, v2);
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
                        if(!this.zzb(arg8, v4, v0)) {
                            goto label_95;
                        }

                    label_23:
                        zzjw.zzb(arg7, v2, zzjw.zzq(arg8, v2));
                        this.zzc(arg7, v4, v0);
                        break;
                    }
                    case 61: 
                    case 62: 
                    case 63: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: {
                        if(!this.zzb(arg8, v4, v0)) {
                            goto label_95;
                        }

                        goto label_23;
                    }
                    case 60: 
                    case 68: {
                        this.zzc(arg7, arg8, v0);
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_95:
            }

            if(!this.zzvi) {
                zzja.zzb(this.zzvp, arg7, arg8);
                if(this.zzvg) {
                    zzja.zzb(this.zzvq, arg7, arg8);
                }
            }

            return;
        }

        throw new NullPointerException();
    }

    private static List zzf(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1);
    }

    private static double zzg(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1).doubleValue();
    }

    private static float zzh(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1).floatValue();
    }

    private static int zzi(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1).intValue();
    }

    private static long zzj(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1).longValue();
    }

    private static boolean zzk(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1).booleanValue();
    }

    public final int zzn(Object arg21) {
        int v16;
        int v4;
        int v5_1;
        int v15;
        int v14;
        int v13;
        int v12;
        Unsafe v2;
        zzil v0 = this;
        Object v1 = arg21;
        int v3 = 267386880;
        int v8 = 1048575;
        long v9 = 0;
        if(v0.zzvi) {
            v2 = zzil.zzuz;
            v12 = 0;
            v13 = 0;
            while(v12 < v0.zzva.length) {
                v14 = v0.zzbi(v12);
                v15 = (v14 & v3) >>> 20;
                v3 = v0.zzva[v12];
                long v5 = ((long)(v14 & v8));
                v14 = v15 < zzgt.zzqx.id() || v15 > zzgt.zzrk.id() ? 0 : v0.zzva[v12 + 2] & v8;
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
                v3 = zzja.zzt(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_98:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                goto label_327;
            label_228:
                v3 = zzja.zzq(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_101:
                v3 = v0.zzvr.zzc(v3, zzjw.zzq(v1, v5), v0.zzbg(v12));
                goto label_239;
            label_231:
                v3 = zzja.zzp(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_234:
                v3 = zzja.zzw(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_106:
                v3 = zzja.zze(v3, zzil.zzf(v1, v5), v0.zzbf(v12));
                goto label_239;
            label_237:
                v3 = zzja.zzx(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_110:
                v5_1 = zzja.zzw(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_241:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_243;
            label_116:
                v5_1 = zzja.zzaa(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_247:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                v5 = zzjw.zzm(v1, v5);
                goto label_250;
            label_122:
                v5_1 = zzja.zzac(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_252:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzjw.zzl(v1, v5);
                goto label_255;
            label_128:
                v5_1 = zzja.zzab(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_257:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_259;
            label_261:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_263;
            label_134:
                v5_1 = zzja.zzx(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_265:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzjw.zzl(v1, v5);
                goto label_268;
            label_140:
                v5_1 = zzja.zzz(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_270:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzjw.zzl(v1, v5);
                goto label_273;
            label_146:
                v5_1 = zzja.zzad(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_275:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_277;
            label_280:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_282;
            label_152:
                v5_1 = zzja.zzab(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_286:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                Object v5_2 = zzjw.zzq(v1, v5);
                if(!(v5_2 instanceof zzfr)) {
                    goto label_292;
                }

                goto label_278;
            label_158:
                v5_1 = zzja.zzac(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_164:
                v5_1 = zzja.zzy(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_37:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_243:
                v3 = zzgf.zzd(v3, zzjw.zzq(v1, v5), v0.zzbf(v12));
                goto label_239;
            label_294:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_296;
            label_40:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5 = zzil.zzj(v1, v5);
            label_250:
                v3 = zzgf.zzg(v3, v5);
                goto label_239;
            label_298:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_300;
            label_170:
                v5_1 = zzja.zzv(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_44:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzil.zzi(v1, v5);
            label_255:
                v3 = zzgf.zzk(v3, v5_1);
                goto label_239;
            label_302:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_304;
            label_176:
                v5_1 = zzja.zzu(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_48:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_259:
                v3 = zzgf.zzi(v3, v9);
                goto label_239;
            label_306:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                v5_1 = zzjw.zzl(v1, v5);
                goto label_309;
            label_51:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_263:
                v3 = zzgf.zzm(v3, 0);
                goto label_239;
            label_182:
                v5_1 = zzja.zzab(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(!v0.zzvj) {
                    goto label_195;
                }

                goto label_193;
            label_54:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzil.zzi(v1, v5);
            label_268:
                v3 = zzgf.zzn(v3, v5_1);
                goto label_239;
            label_311:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                v5 = zzjw.zzm(v1, v5);
                goto label_314;
            label_58:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzil.zzi(v1, v5);
            label_273:
                v3 = zzgf.zzj(v3, v5_1);
                goto label_239;
            label_316:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                v5 = zzjw.zzm(v1, v5);
                goto label_319;
            label_188:
                v5_1 = zzja.zzac(v2.getObject(v1, v5));
                if(v5_1 <= 0) {
                    goto label_330;
                }

                if(v0.zzvj) {
                label_193:
                    v2.putInt(v1, ((long)v14), v5_1);
                }

            label_195:
                v3 = zzgf.zzas(v3) + zzgf.zzau(v5_1) + v5_1;
                goto label_239;
            label_62:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_277:
                v5_2 = zzjw.zzq(v1, v5);
                goto label_278;
            label_321:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

                goto label_323;
            label_65:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_282:
                v3 = zzja.zzd(v3, zzjw.zzq(v1, v5), v0.zzbf(v12));
                goto label_239;
            label_68:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5_2 = zzjw.zzq(v1, v5);
                if((v5_2 instanceof zzfr)) {
                label_278:
                    v3 = zzgf.zzd(v3, ((zzfr)v5_2));
                    goto label_239;
                }

            label_292:
                v3 = zzgf.zzc(v3, ((String)v5_2));
                goto label_239;
            label_325:
                if(!v0.zzb(v1, v12)) {
                    goto label_330;
                }

            label_327:
                v3 = zzgf.zzc(v3, 0);
                goto label_239;
            label_200:
                v3 = zzja.zzr(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_74:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_296:
                v3 = zzgf.zzd(v3, true);
                goto label_239;
            label_203:
                v3 = zzja.zzv(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_77:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_300:
                v3 = zzgf.zzl(v3, 0);
                goto label_239;
            label_206:
                v3 = zzja.zzs(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_80:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

            label_304:
                v3 = zzgf.zzh(v3, v9);
                goto label_239;
            label_209:
                v3 = zzja.zzu(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_83:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5_1 = zzil.zzi(v1, v5);
            label_309:
                v3 = zzgf.zzi(v3, v5_1);
                goto label_239;
            label_212:
                v3 = zzja.zzf(v3, zzil.zzf(v1, v5));
                goto label_239;
            label_215:
                v3 = zzja.zzd(v3, zzil.zzf(v1, v5), v0.zzbf(v12));
                goto label_239;
            label_87:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5 = zzil.zzj(v1, v5);
            label_314:
                v3 = zzgf.zzf(v3, v5);
                goto label_239;
            label_219:
                v3 = zzja.zze(v3, zzil.zzf(v1, v5));
                goto label_239;
            label_91:
                if(!v0.zzb(v1, v3, v12)) {
                    goto label_330;
                }

                v5 = zzil.zzj(v1, v5);
            label_319:
                v3 = zzgf.zze(v3, v5);
                goto label_239;
            label_222:
                v3 = zzja.zzy(v3, zzil.zzf(v1, v5), false);
                goto label_239;
            label_95:
                if(v0.zzb(v1, v3, v12)) {
                label_323:
                    v3 = zzgf.zzd(v3, 0f);
                label_239:
                    v13 += v3;
                }

            label_330:
                v12 += 4;
                v3 = 267386880;
            }

            return v13 + zzil.zzb(v0.zzvp, v1);
        }

        v2 = zzil.zzuz;
        v3 = 0;
        v5_1 = 0;
        int v6 = -1;
        v12 = 0;
        while(v3 < v0.zzva.length) {
            v13 = v0.zzbi(v3);
            v14 = v0.zzva[v3];
            v15 = (v13 & 267386880) >>> 20;
            if(v15 <= 17) {
                v4 = v0.zzva[v3 + 2];
                int v11 = v4 & v8;
                v16 = 1 << (v4 >>> 20);
                if(v11 != v6) {
                    v12 = v2.getInt(v1, ((long)v11));
                    v6 = v11;
                }
            }
            else {
                v4 = !v0.zzvj || v15 < zzgt.zzqx.id() || v15 > zzgt.zzrk.id() ? 0 : v0.zzva[v3 + 2] & v8;
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
            int v9_1 = zzja.zzac(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_614:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_616;
        label_487:
            v9_1 = zzja.zzab(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
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
            v9_1 = zzja.zzx(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
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
            v9_1 = zzja.zzz(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_630:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_632;
        label_505:
            v9_1 = zzja.zzad(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_635:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_637;
        label_511:
            v9_1 = zzja.zzab(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
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
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

        label_616:
            v4 = zzgf.zzd(v14, v2.getObject(v1, v9), v0.zzbf(v3));
            goto label_608;
        label_388:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v9 = zzil.zzj(v1, v9);
        label_623:
            v4 = zzgf.zzg(v14, v9);
            goto label_608;
        label_517:
            v9_1 = zzja.zzac(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
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
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzil.zzi(v1, v9);
        label_628:
            v4 = zzgf.zzk(v14, v4);
            goto label_608;
        label_651:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_653;
        label_523:
            v9_1 = zzja.zzy(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_396:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

        label_632:
            v4 = zzgf.zzi(v14, 0);
            goto label_608;
        label_399:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

        label_637:
            v9_1 = zzgf.zzm(v14, 0);
            goto label_639;
        label_656:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_658;
        label_529:
            v9_1 = zzja.zzv(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_402:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzil.zzi(v1, v9);
        label_644:
            v4 = zzgf.zzn(v14, v4);
            goto label_608;
        label_662:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            Object v4_1 = v2.getObject(v1, v9);
            if(!(v4_1 instanceof zzfr)) {
                goto label_668;
            }

            goto label_654;
        label_406:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzil.zzi(v1, v9);
        label_649:
            v4 = zzgf.zzj(v14, v4);
            goto label_608;
        label_535:
            v9_1 = zzja.zzu(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_410:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

        label_653:
            v4_1 = v2.getObject(v1, v9);
            goto label_654;
        label_541:
            v9_1 = zzja.zzab(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_413:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

        label_658:
            v4 = zzja.zzd(v14, v2.getObject(v1, v9), v0.zzbf(v3));
            goto label_608;
        label_670:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            goto label_672;
        label_416:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4_1 = v2.getObject(v1, v9);
            if((v4_1 instanceof zzfr)) {
            label_654:
                v4 = zzgf.zzd(v14, ((zzfr)v4_1));
                goto label_608;
            }

        label_668:
            v4 = zzgf.zzc(v14, ((String)v4_1));
            goto label_608;
        label_674:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzgf.zzl(v14, 0);
            goto label_728;
        label_547:
            v9_1 = zzja.zzac(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_422:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

        label_672:
            v4 = zzgf.zzd(v14, true);
            goto label_608;
        label_680:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzgf.zzh(v14, 0);
            goto label_728;
        label_425:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v9_1 = zzgf.zzl(v14, 0);
            goto label_639;
        label_430:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzgf.zzh(v14, 0);
            goto label_608;
        label_559:
            v4 = zzja.zzr(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_688:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v9_1 = zzgf.zzi(v14, v2.getInt(v1, v9));
            goto label_708;
        label_563:
            v4 = zzja.zzv(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_435:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzgf.zzi(v14, zzil.zzi(v1, v9));
            goto label_608;
        label_695:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v9_1 = zzgf.zzf(v14, v2.getLong(v1, v9));
            goto label_708;
        label_567:
            v4 = zzja.zzs(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_440:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzgf.zzf(v14, zzil.zzj(v1, v9));
            goto label_608;
        label_571:
            v4 = zzja.zzu(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_445:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzgf.zze(v14, zzil.zzj(v1, v9));
            goto label_608;
        label_702:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v9_1 = zzgf.zze(v14, v2.getLong(v1, v9));
        label_708:
            v5_1 += v9_1;
            goto label_728;
        label_575:
            v4 = zzja.zzf(v14, v2.getObject(v1, v9));
            goto label_608;
        label_578:
            v4 = zzja.zzd(v14, v2.getObject(v1, v9), v0.zzbf(v3));
            goto label_608;
        label_450:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v9_1 = zzgf.zzd(v14, 0f);
        label_639:
            v5_1 += v9_1;
            goto label_728;
        label_582:
            v4 = zzja.zze(v14, v2.getObject(v1, v9));
            goto label_608;
        label_711:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzgf.zzd(v14, 0f);
            goto label_728;
        label_455:
            if(!v0.zzb(v1, v14, v3)) {
                goto label_728;
            }

            v4 = zzgf.zzc(v14, 0);
            goto label_608;
        label_585:
            v4 = zzja.zzy(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_460:
            v4 = v0.zzvr.zzc(v14, v2.getObject(v1, v9), v0.zzbg(v3));
            goto label_608;
        label_589:
            v4 = zzja.zzt(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_720:
            if((v12 & v16) == 0) {
                goto label_728;
            }

            v5_1 += zzgf.zzc(v14, 0);
            goto label_728;
        label_593:
            v4 = zzja.zzq(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_465:
            v4 = zzja.zze(v14, v2.getObject(v1, v9), v0.zzbf(v3));
            goto label_608;
        label_597:
            v4 = zzja.zzp(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_469:
            v9_1 = zzja.zzw(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(!v0.zzvj) {
                goto label_554;
            }

            goto label_552;
        label_601:
            v4 = zzja.zzw(v14, v2.getObject(v1, v9), false);
            goto label_608;
        label_475:
            v9_1 = zzja.zzaa(v2.getObject(v1, v9));
            if(v9_1 <= 0) {
                goto label_728;
            }

            if(v0.zzvj) {
            label_552:
                v2.putInt(v1, ((long)v4), v9_1);
            }

        label_554:
            v4 = zzgf.zzas(v14) + zzgf.zzau(v9_1) + v9_1;
            goto label_608;
        label_605:
            v4 = zzja.zzx(v14, v2.getObject(v1, v9), false);
        label_608:
            v5_1 += v4;
        label_728:
            v3 += 4;
        }

        v5_1 += zzil.zzb(v0.zzvp, v1);
        if(v0.zzvg) {
            v5_1 += v0.zzvq.zzb(v1).zzdg();
        }

        return v5_1;
    }

    public final boolean zzo(Object arg17) {
        zziy v4_1;
        int[] v15;
        int v12;
        zzil v0 = this;
        Object v1 = arg17;
        int v3 = 1;
        if(v0.zzvk != null) {
            if(v0.zzvk.length == 0) {
            }
            else {
                int[] v4 = v0.zzvk;
                int v5 = v4.length;
                int v2 = 0;
                int v7 = -1;
                int v8 = 0;
                while(v2 < v5) {
                    int v9 = v4[v2];
                    int v10 = v0.zzbl(v9);
                    int v11 = v0.zzbi(v10);
                    int v13 = 1048575;
                    if(!v0.zzvi) {
                        int v14 = v0.zzva[v10 + 2] & v13;
                        v12 = v3 << (v0.zzva[v10 + 2] >>> 20);
                        if(v14 != v7) {
                            v15 = v4;
                            v8 = zzil.zzuz.getInt(v1, ((long)v14));
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
                    if(v3 != 0 && !v0.zzb(v1, v10, v8, v12)) {
                        return 0;
                    }

                    v3 = (267386880 & v11) >>> 20;
                    if(v3 == 9 || v3 == 17) {
                        if(!v0.zzb(v1, v10, v8, v12)) {
                            goto label_128;
                        }

                        if(zzil.zzb(v1, v11, v0.zzbf(v10))) {
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
                                Map v3_1 = v0.zzvr.zzi(zzjw.zzq(v1, ((long)(v11 & v13))));
                                if((v3_1.isEmpty()) || v0.zzvr.zzm(v0.zzbg(v10)).zzuv.zzgz() != zzkj.zzzw) {
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
                                                v4_1 = zzis.zzfc().zzg(v9_1.getClass());
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

                            if(!v0.zzb(v1, v9, v10)) {
                                goto label_128;
                            }

                            if(zzil.zzb(v1, v11, v0.zzbf(v10))) {
                                goto label_128;
                            }

                            return 0;
                        }

                    label_103:
                        Object v3_3 = zzjw.zzq(v1, ((long)(v11 & v13)));
                        if(!((List)v3_3).isEmpty()) {
                            v4_1 = v0.zzbf(v10);
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

                if((v0.zzvg) && !v0.zzvq.zzb(v1).isInitialized()) {
                    return 0;
                }

                return 1;
            }
        }

        return 1;
    }
}

