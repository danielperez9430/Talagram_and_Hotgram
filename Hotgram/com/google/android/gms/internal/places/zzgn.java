package com.google.android.gms.internal.places;

import java.nio.ByteBuffer;
import java.util.Map$Entry;

final class zzgn extends zzgm {
    zzgn() {
        super();
    }

    final int zzb(Map$Entry arg1) {
        return arg1.getKey().number;
    }

    final zzgq zzb(Object arg1) {
        return ((zze)arg1).zzsm;
    }

    final Object zzb(zzgl arg1, zzih arg2, int arg3) {
        return arg1.zzb(arg2, arg3);
    }

    final Object zzb(zzix arg4, Object arg5, zzgl arg6, zzgq arg7, Object arg8, zzjq arg9) {
        zzfr v4_2;
        Object v4_1;
        int v4;
        int v0 = ((zzg)arg5).zzsu.number;
        if(((zzg)arg5).zzsu.zzso == zzke.zzzg) {
            v4 = arg4.zzbk();
            if(((zzg)arg5).zzsu.zzsn.zzi(v4) == null) {
                return zzja.zzb(v0, v4, arg8, arg9);
            }
            else {
                goto label_13;
            }
        }
        else {
            switch(zzgo.zznn[((zzg)arg5).zzsu.zzso.ordinal()]) {
                case 1: {
                    goto label_66;
                }
                case 2: {
                    goto label_63;
                }
                case 3: {
                    goto label_60;
                }
                case 4: {
                    goto label_58;
                }
                case 5: {
                    goto label_56;
                }
                case 6: {
                    goto label_54;
                }
                case 7: {
                    goto label_52;
                }
                case 8: {
                    goto label_49;
                }
                case 9: {
                    goto label_47;
                }
                case 10: {
                    goto label_45;
                }
                case 11: {
                    goto label_43;
                }
                case 12: {
                    goto label_41;
                }
                case 13: {
                    goto label_39;
                }
                case 14: {
                    goto label_35;
                }
                case 15: {
                    goto label_33;
                }
                case 16: {
                    goto label_31;
                }
                case 17: {
                    goto label_27;
                }
                case 18: {
                    goto label_23;
                }
            }

            v4_1 = null;
            goto label_68;
        label_33:
            v4_2 = arg4.zzbp();
            goto label_68;
        label_66:
            Double v4_3 = Double.valueOf(arg4.readDouble());
            goto label_68;
        label_35:
            throw new IllegalStateException("Shouldn\'t reach here.");
        label_39:
            long v0_1 = arg4.zzbv();
            goto label_61;
        label_41:
            v4 = arg4.zzbu();
            goto label_13;
        label_43:
            v0_1 = arg4.zzbt();
            goto label_61;
        label_45:
            v4 = arg4.zzbs();
            goto label_13;
        label_47:
            v4 = arg4.zzbq();
            goto label_13;
        label_49:
            Boolean v4_4 = Boolean.valueOf(arg4.zzbn());
            goto label_68;
        label_52:
            v4 = arg4.zzbm();
            goto label_13;
        label_54:
            v0_1 = arg4.zzbl();
            goto label_61;
        label_23:
            v4_1 = arg4.zzb(((zzg)arg5).zzst.getClass(), arg6);
            goto label_68;
        label_56:
            v4 = arg4.zzbk();
        label_13:
            Integer v4_5 = Integer.valueOf(v4);
            goto label_68;
        label_58:
            v0_1 = arg4.zzbi();
            goto label_61;
        label_27:
            v4_1 = arg4.zzc(((zzg)arg5).zzst.getClass(), arg6);
            goto label_68;
        label_60:
            v0_1 = arg4.zzbj();
        label_61:
            Long v4_6 = Long.valueOf(v0_1);
            goto label_68;
        label_63:
            Float v4_7 = Float.valueOf(arg4.readFloat());
            goto label_68;
        label_31:
            String v4_8 = arg4.readString();
        }

    label_68:
        switch(zzgo.zznn[((zzg)arg5).zzsu.zzso.ordinal()]) {
            case 17: 
            case 18: {
                Object v6 = arg7.zzb(((zzg)arg5).zzsu);
                if(v6 != null) {
                    v4_1 = zzhb.zzb(v6, v4_2);
                }

                break;
            }
            default: {
                break;
            }
        }

        arg7.zzb(((zzg)arg5).zzsu, v4_2);
        return arg8;
    }

    final void zzb(zzfr arg5, Object arg6, zzgl arg7, zzgq arg8) {
        byte[] v5;
        zzih v0 = ((zzg)arg6).zzst.zzdr().zzdw();
        int v1 = arg5.size();
        if(v1 == 0) {
            v5 = zzhb.zztl;
        }
        else {
            byte[] v2 = new byte[v1];
            arg5.zzb(v2, 0, 0, v1);
            v5 = v2;
        }

        ByteBuffer v5_1 = ByteBuffer.wrap(v5);
        if(v5_1.hasArray()) {
            zzfo v1_1 = new zzfo(v5_1, true);
            zzis.zzfc().zzp(v0).zzb(v0, ((zzix)v1_1), arg7);
            arg8.zzb(((zzg)arg6).zzsu, v0);
            if(((zzix)v1_1).zzbg() == 2147483647) {
                return;
            }

            throw zzhh.zzec();
        }

        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    final void zzb(zzix arg2, Object arg3, zzgl arg4, zzgq arg5) {
        arg5.zzb(((zzg)arg3).zzsu, arg2.zzb(((zzg)arg3).zzst.getClass(), arg4));
    }

    final void zzb(zzkk arg4, Map$Entry arg5) {
        Object v0 = arg5.getKey();
        switch(zzgo.zznn[((zzf)v0).zzso.ordinal()]) {
            case 1: {
                goto label_96;
            }
            case 2: {
                goto label_91;
            }
            case 3: {
                goto label_86;
            }
            case 4: {
                goto label_81;
            }
            case 5: {
                goto label_76;
            }
            case 6: {
                goto label_71;
            }
            case 7: {
                goto label_66;
            }
            case 8: {
                goto label_61;
            }
            case 9: {
                goto label_56;
            }
            case 10: {
                goto label_51;
            }
            case 11: {
                goto label_46;
            }
            case 12: {
                goto label_41;
            }
            case 13: {
                goto label_36;
            }
            case 14: {
                goto label_31;
            }
            case 15: {
                goto label_27;
            }
            case 16: {
                goto label_23;
            }
            case 17: {
                goto label_15;
            }
            case 18: {
                goto label_7;
            }
        }

        return;
    label_66:
        arg4.zzh(((zzf)v0).number, arg5.getValue().intValue());
        return;
    label_36:
        arg4.zzc(((zzf)v0).number, arg5.getValue().longValue());
        return;
    label_71:
        arg4.zzd(((zzf)v0).number, arg5.getValue().longValue());
        return;
    label_7:
        arg4.zzb(((zzf)v0).number, arg5.getValue(), zzis.zzfc().zzg(arg5.getValue().getClass()));
        return;
    label_41:
        arg4.zzg(((zzf)v0).number, arg5.getValue().intValue());
        return;
    label_76:
        arg4.zze(((zzf)v0).number, arg5.getValue().intValue());
        return;
    label_46:
        arg4.zzk(((zzf)v0).number, arg5.getValue().longValue());
        return;
    label_15:
        arg4.zzc(((zzf)v0).number, arg5.getValue(), zzis.zzfc().zzg(arg5.getValue().getClass()));
        return;
    label_81:
        arg4.zzb(((zzf)v0).number, arg5.getValue().longValue());
        return;
    label_51:
        arg4.zzo(((zzf)v0).number, arg5.getValue().intValue());
        return;
    label_86:
        arg4.zzj(((zzf)v0).number, arg5.getValue().longValue());
        return;
    label_23:
        arg4.zzb(((zzf)v0).number, arg5.getValue());
        return;
    label_56:
        arg4.zzf(((zzf)v0).number, arg5.getValue().intValue());
        return;
    label_91:
        arg4.zzc(((zzf)v0).number, arg5.getValue().floatValue());
        return;
    label_27:
        arg4.zzb(((zzf)v0).number, arg5.getValue());
        return;
    label_61:
        arg4.zzc(((zzf)v0).number, arg5.getValue().booleanValue());
        return;
    label_31:
        arg4.zze(((zzf)v0).number, arg5.getValue().intValue());
        return;
    label_96:
        arg4.zzb(((zzf)v0).number, arg5.getValue().doubleValue());
    }

    final void zzb(Object arg1, zzgq arg2) {
        ((zze)arg1).zzsm = arg2;
    }

    final zzgq zzc(Object arg3) {
        Object v0_1;
        zzgq v0 = ((zzgm)this).zzb(arg3);
        if(v0.isImmutable()) {
            v0_1 = v0.clone();
            ((zzgm)this).zzb(arg3, ((zzgq)v0_1));
        }

        return ((zzgq)v0_1);
    }

    final void zzd(Object arg1) {
        ((zzgm)this).zzb(arg1).zzbb();
    }

    final boolean zzf(zzih arg1) {
        return arg1 instanceof zze;
    }
}

