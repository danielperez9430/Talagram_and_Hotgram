package com.google.android.gms.internal.places;

final class zziu implements zzif {
    private final String info;
    private final zzih zzvf;
    private final zziv zzvy;

    zziu(zzih arg2, String arg3, Object[] arg4) {
        super();
        this.zzvf = arg2;
        this.info = arg3;
        this.zzvy = new zziv(arg2.getClass(), arg3, arg4);
    }

    public final int getFieldCount() {
        return zziv.zze(this.zzvy);
    }

    public final int zzev() {
        if((zziv.zzb(this.zzvy) & 1) == 1) {
            return zzh.zztd;
        }

        return zzh.zzte;
    }

    public final boolean zzew() {
        if((zziv.zzb(this.zzvy) & 2) == 2) {
            return 1;
        }

        return 0;
    }

    public final zzih zzex() {
        return this.zzvf;
    }

    final zziv zzfe() {
        return this.zzvy;
    }

    public final int zzff() {
        return zziv.zzc(this.zzvy);
    }

    public final int zzfg() {
        return zziv.zzd(this.zzvy);
    }

    public final int zzfh() {
        return zziv.zzf(this.zzvy);
    }

    public final int zzfi() {
        return zziv.zzg(this.zzvy);
    }

    final int[] zzfj() {
        return zziv.zzh(this.zzvy);
    }

    public final int zzfk() {
        return zziv.zzi(this.zzvy);
    }

    public final int zzfl() {
        return zziv.zzj(this.zzvy);
    }
}

