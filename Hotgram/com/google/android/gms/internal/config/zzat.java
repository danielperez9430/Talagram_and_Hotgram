package com.google.android.gms.internal.config;

import java.util.Arrays;

public final class zzat extends zzbb {
    private static volatile zzat[] zzbh;
    public String zzbi;
    public byte[] zzbj;

    public zzat() {
        super();
        this.zzbi = "";
        this.zzbj = zzbk.zzdd;
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzat)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzat)) {
            return 0;
        }

        if(this.zzbi == null) {
            if(((zzat)arg5).zzbi != null) {
                return 0;
            }
        }
        else if(!this.zzbi.equals(((zzat)arg5).zzbi)) {
            return 0;
        }

        if(!Arrays.equals(this.zzbj, ((zzat)arg5).zzbj)) {
            return 0;
        }

        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                return this.zzch.equals(((zzat)arg5).zzch);
            }
        }

        if(((zzat)arg5).zzch != null) {
            if(((zzat)arg5).zzch.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = (this.getClass().getName().hashCode() + 527) * 31;
        int v2 = 0;
        int v1 = this.zzbi == null ? 0 : this.zzbi.hashCode();
        v0 = ((v0 + v1) * 31 + Arrays.hashCode(this.zzbj)) * 31;
        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                v2 = this.zzch.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzbh zza(zzay arg3) {
        while(true) {
            int v0 = arg3.zzx();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 10) {
                if(v0 != 18) {
                    if(super.zza(arg3, v0)) {
                        continue;
                    }

                    return this;
                }

                this.zzbj = arg3.readBytes();
                continue;
            }

            this.zzbi = arg3.readString();
        }

        return this;
    }

    public final void zza(zzaz arg3) {
        if(this.zzbi != null && !this.zzbi.equals("")) {
            arg3.zza(1, this.zzbi);
        }

        if(!Arrays.equals(this.zzbj, zzbk.zzdd)) {
            arg3.zza(2, this.zzbj);
        }

        super.zza(arg3);
    }

    protected final int zzt() {
        int v0 = super.zzt();
        if(this.zzbi != null && !this.zzbi.equals("")) {
            v0 += zzaz.zzb(1, this.zzbi);
        }

        if(!Arrays.equals(this.zzbj, zzbk.zzdd)) {
            v0 += zzaz.zzl(2) + zzaz.zzb(this.zzbj);
        }

        return v0;
    }

    public static zzat[] zzu() {
        if(zzat.zzbh == null) {
            Object v0 = zzbf.zzcp;
            __monitor_enter(v0);
            try {
                if(zzat.zzbh == null) {
                    zzat.zzbh = new zzat[0];
                }

                __monitor_exit(v0);
                goto label_14;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_12;
            }

            throw v1;
        }

    label_14:
        return zzat.zzbh;
    }
}

