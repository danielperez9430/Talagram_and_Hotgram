package com.google.android.gms.internal.vision;

public class zzde {
    private static final zzcf zzgk;
    private zzbo zzmi;
    private volatile zzdx zzmj;
    private volatile zzbo zzmk;

    static {
        zzde.zzgk = zzcf.zzbg();
    }

    public zzde() {
        super();
    }

    public boolean equals(Object arg3) {
        if(this == (((zzde)arg3))) {
            return 1;
        }

        if(!(arg3 instanceof zzde)) {
            return 0;
        }

        zzdx v0 = this.zzmj;
        zzdx v1 = ((zzde)arg3).zzmj;
        if(v0 == null && v1 == null) {
            return this.zzak().equals(((zzde)arg3).zzak());
        }

        if(v0 != null && v1 != null) {
            return v0.equals(v1);
        }

        if(v0 != null) {
            return v0.equals(((zzde)arg3).zzh(v0.zzbw()));
        }

        return this.zzh(v1.zzbw()).equals(v1);
    }

    public int hashCode() {
        return 1;
    }

    public final zzbo zzak() {
        if(this.zzmk != null) {
            return this.zzmk;
        }

        __monitor_enter(this);
        try {
            if(this.zzmk != null) {
                __monitor_exit(this);
                return this.zzmk;
            }

            zzbo v0_1 = this.zzmj == null ? zzbo.zzgt : this.zzmj.zzak();
            this.zzmk = v0_1;
            __monitor_exit(this);
            return this.zzmk;
        label_22:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_22;
        }

        throw v0;
    }

    public final int zzbl() {
        if(this.zzmk != null) {
            return this.zzmk.size();
        }

        if(this.zzmj != null) {
            return this.zzmj.zzbl();
        }

        return 0;
    }

    private final zzdx zzh(zzdx arg2) {
        if(this.zzmj == null) {
            __monitor_enter(this);
            try {
                if(this.zzmj == null) {
                    try {
                        this.zzmj = arg2;
                        this.zzmk = zzbo.zzgt;
                    }
                    catch(zzcx ) {
                        this.zzmj = arg2;
                        this.zzmk = zzbo.zzgt;
                    }
                }

                __monitor_exit(this);
                goto label_18;
            label_16:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_16;
            }

            throw v2;
        }

    label_18:
        return this.zzmj;
    }

    public final zzdx zzi(zzdx arg3) {
        zzdx v0 = this.zzmj;
        this.zzmi = null;
        this.zzmk = null;
        this.zzmj = arg3;
        return v0;
    }
}

