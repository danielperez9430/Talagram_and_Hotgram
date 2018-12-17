package com.google.android.gms.internal.clearcut;

final class zzec implements zzdm {
    private final String info;
    private final zzdo zzmn;
    private final zzed zzng;

    zzec(zzdo arg2, String arg3, Object[] arg4) {
        super();
        this.zzmn = arg2;
        this.info = arg3;
        this.zzng = new zzed(arg2.getClass(), arg3, arg4);
    }

    public final int getFieldCount() {
        return zzed.zzd(this.zzng);
    }

    public final int zzcf() {
        if((zzed.zza(this.zzng) & 1) == 1) {
            return zzg.zzkl;
        }

        return zzg.zzkm;
    }

    public final boolean zzcg() {
        if((zzed.zza(this.zzng) & 2) == 2) {
            return 1;
        }

        return 0;
    }

    public final zzdo zzch() {
        return this.zzmn;
    }

    final zzed zzco() {
        return this.zzng;
    }

    public final int zzcp() {
        return zzed.zzb(this.zzng);
    }

    public final int zzcq() {
        return zzed.zzc(this.zzng);
    }

    public final int zzcr() {
        return zzed.zze(this.zzng);
    }

    public final int zzcs() {
        return zzed.zzf(this.zzng);
    }

    final int[] zzct() {
        return zzed.zzg(this.zzng);
    }

    public final int zzcu() {
        return zzed.zzh(this.zzng);
    }

    public final int zzcv() {
        return zzed.zzi(this.zzng);
    }
}

