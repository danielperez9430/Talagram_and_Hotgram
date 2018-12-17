package com.google.android.gms.internal.clearcut;

import java.util.Map$Entry;

final class zzbv extends zzbu {
    zzbv() {
        super();
    }

    final int zza(Map$Entry arg1) {
        return arg1.getKey().number;
    }

    final zzby zza(Object arg1) {
        return ((zzd)arg1).zzjv;
    }

    final void zza(zzfr arg4, Map$Entry arg5) {
        Object v0 = arg5.getKey();
        switch(zzbw.zzgq[((zze)v0).zzjx.ordinal()]) {
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
        arg4.zzf(((zze)v0).number, arg5.getValue().intValue());
        return;
    label_36:
        arg4.zzb(((zze)v0).number, arg5.getValue().longValue());
        return;
    label_71:
        arg4.zzc(((zze)v0).number, arg5.getValue().longValue());
        return;
    label_7:
        arg4.zza(((zze)v0).number, arg5.getValue(), zzea.zzcm().zze(arg5.getValue().getClass()));
        return;
    label_41:
        arg4.zze(((zze)v0).number, arg5.getValue().intValue());
        return;
    label_76:
        arg4.zzc(((zze)v0).number, arg5.getValue().intValue());
        return;
    label_46:
        arg4.zzj(((zze)v0).number, arg5.getValue().longValue());
        return;
    label_15:
        arg4.zzb(((zze)v0).number, arg5.getValue(), zzea.zzcm().zze(arg5.getValue().getClass()));
        return;
    label_81:
        arg4.zza(((zze)v0).number, arg5.getValue().longValue());
        return;
    label_51:
        arg4.zzm(((zze)v0).number, arg5.getValue().intValue());
        return;
    label_86:
        arg4.zzi(((zze)v0).number, arg5.getValue().longValue());
        return;
    label_23:
        arg4.zza(((zze)v0).number, arg5.getValue());
        return;
    label_56:
        arg4.zzd(((zze)v0).number, arg5.getValue().intValue());
        return;
    label_91:
        arg4.zza(((zze)v0).number, arg5.getValue().floatValue());
        return;
    label_27:
        arg4.zza(((zze)v0).number, arg5.getValue());
        return;
    label_61:
        arg4.zzb(((zze)v0).number, arg5.getValue().booleanValue());
        return;
    label_31:
        arg4.zzc(((zze)v0).number, arg5.getValue().intValue());
        return;
    label_96:
        arg4.zza(((zze)v0).number, arg5.getValue().doubleValue());
    }

    final void zza(Object arg1, zzby arg2) {
        ((zzd)arg1).zzjv = arg2;
    }

    final zzby zzb(Object arg3) {
        zzby v0 = ((zzbu)this).zza(arg3);
        if(v0.isImmutable()) {
            Object v0_1 = v0.clone();
            ((zzbu)this).zza(arg3, ((zzby)v0_1));
        }

        return v0;
    }

    final void zzc(Object arg1) {
        ((zzbu)this).zza(arg1).zzv();
    }

    final boolean zze(zzdo arg1) {
        return arg1 instanceof zzd;
    }
}

