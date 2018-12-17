package com.google.android.gms.internal.places;

public class zzho {
    private static final zzgl zznj;
    private zzfr zzuf;
    private volatile zzih zzug;
    private volatile zzfr zzuh;

    static {
        zzho.zznj = zzgl.zzda();
    }

    public zzho() {
        super();
    }

    public boolean equals(Object arg3) {
        if(this == (((zzho)arg3))) {
            return 1;
        }

        if(!(arg3 instanceof zzho)) {
            return 0;
        }

        zzih v0 = this.zzug;
        zzih v1 = ((zzho)arg3).zzug;
        if(v0 == null && v1 == null) {
            return this.zzax().equals(((zzho)arg3).zzax());
        }

        if(v0 != null && v1 != null) {
            return v0.equals(v1);
        }

        if(v0 != null) {
            return v0.equals(((zzho)arg3).zzi(v0.zzds()));
        }

        return this.zzi(v1.zzds()).equals(v1);
    }

    public int hashCode() {
        return 1;
    }

    public final zzfr zzax() {
        if(this.zzuh != null) {
            return this.zzuh;
        }

        __monitor_enter(this);
        try {
            if(this.zzuh != null) {
                __monitor_exit(this);
                return this.zzuh;
            }

            zzfr v0_1 = this.zzug == null ? zzfr.zznt : this.zzug.zzax();
            this.zzuh = v0_1;
            __monitor_exit(this);
            return this.zzuh;
        label_22:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_22;
        }

        throw v0;
    }

    public final int zzdg() {
        if(this.zzuh != null) {
            return this.zzuh.size();
        }

        if(this.zzug != null) {
            return this.zzug.zzdg();
        }

        return 0;
    }

    private final zzih zzi(zzih arg2) {
        if(this.zzug == null) {
            __monitor_enter(this);
            try {
                if(this.zzug == null) {
                    try {
                        this.zzug = arg2;
                        this.zzuh = zzfr.zznt;
                    }
                    catch(zzhh ) {
                        this.zzug = arg2;
                        this.zzuh = zzfr.zznt;
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
        return this.zzug;
    }

    public final zzih zzj(zzih arg3) {
        zzih v0 = this.zzug;
        this.zzuf = null;
        this.zzuh = null;
        this.zzug = arg3;
        return v0;
    }
}

