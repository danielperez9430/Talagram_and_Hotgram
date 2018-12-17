package com.google.android.gms.internal.clearcut;

public class zzcv {
    private static final zzbt zzez;
    private zzbb zzln;
    private volatile zzdo zzlo;
    private volatile zzbb zzlp;

    static {
        zzcv.zzez = zzbt.zzan();
    }

    public zzcv() {
        super();
    }

    public boolean equals(Object arg3) {
        if(this == (((zzcv)arg3))) {
            return 1;
        }

        if(!(arg3 instanceof zzcv)) {
            return 0;
        }

        zzdo v0 = this.zzlo;
        zzdo v1 = ((zzcv)arg3).zzlo;
        if(v0 == null && v1 == null) {
            return this.zzr().equals(((zzcv)arg3).zzr());
        }

        if(v0 != null && v1 != null) {
            return v0.equals(v1);
        }

        if(v0 != null) {
            return v0.equals(((zzcv)arg3).zzh(v0.zzbe()));
        }

        return this.zzh(v1.zzbe()).equals(v1);
    }

    public int hashCode() {
        return 1;
    }

    public final int zzas() {
        if(this.zzlp != null) {
            return this.zzlp.size();
        }

        if(this.zzlo != null) {
            return this.zzlo.zzas();
        }

        return 0;
    }

    private final zzdo zzh(zzdo arg2) {
        if(this.zzlo == null) {
            __monitor_enter(this);
            try {
                if(this.zzlo == null) {
                    try {
                        this.zzlo = arg2;
                        this.zzlp = zzbb.zzfi;
                    }
                    catch(zzco ) {
                        this.zzlo = arg2;
                        this.zzlp = zzbb.zzfi;
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
        return this.zzlo;
    }

    public final zzdo zzi(zzdo arg3) {
        zzdo v0 = this.zzlo;
        this.zzln = null;
        this.zzlp = null;
        this.zzlo = arg3;
        return v0;
    }

    public final zzbb zzr() {
        if(this.zzlp != null) {
            return this.zzlp;
        }

        __monitor_enter(this);
        try {
            if(this.zzlp != null) {
                __monitor_exit(this);
                return this.zzlp;
            }

            zzbb v0_1 = this.zzlo == null ? zzbb.zzfi : this.zzlo.zzr();
            this.zzlp = v0_1;
            __monitor_exit(this);
            return this.zzlp;
        label_22:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_22;
        }

        throw v0;
    }
}

