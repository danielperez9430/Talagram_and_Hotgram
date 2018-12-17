package com.google.android.gms.internal.measurement;

public class zzwa {
    private static final zzuz zzbtt;
    private zzud zzcad;
    private volatile zzwt zzcae;
    private volatile zzud zzcaf;

    static {
        zzwa.zzbtt = zzuz.zzvo();
    }

    public zzwa() {
        super();
    }

    public boolean equals(Object arg3) {
        if(this == (((zzwa)arg3))) {
            return 1;
        }

        if(!(arg3 instanceof zzwa)) {
            return 0;
        }

        zzwt v0 = this.zzcae;
        zzwt v1 = ((zzwa)arg3).zzcae;
        if(v0 == null && v1 == null) {
            return this.zztt().equals(((zzwa)arg3).zztt());
        }

        if(v0 != null && v1 != null) {
            return v0.equals(v1);
        }

        if(v0 != null) {
            return v0.equals(((zzwa)arg3).zzh(v0.zzwf()));
        }

        return this.zzh(v1.zzwf()).equals(v1);
    }

    public int hashCode() {
        return 1;
    }

    private final zzwt zzh(zzwt arg2) {
        if(this.zzcae == null) {
            __monitor_enter(this);
            try {
                if(this.zzcae == null) {
                    try {
                        this.zzcae = arg2;
                        this.zzcaf = zzud.zzbtz;
                    }
                    catch(zzvt ) {
                        this.zzcae = arg2;
                        this.zzcaf = zzud.zzbtz;
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
        return this.zzcae;
    }

    public final zzwt zzi(zzwt arg3) {
        zzwt v0 = this.zzcae;
        this.zzcad = null;
        this.zzcaf = null;
        this.zzcae = arg3;
        return v0;
    }

    public final zzud zztt() {
        if(this.zzcaf != null) {
            return this.zzcaf;
        }

        __monitor_enter(this);
        try {
            if(this.zzcaf != null) {
                __monitor_exit(this);
                return this.zzcaf;
            }

            zzud v0_1 = this.zzcae == null ? zzud.zzbtz : this.zzcae.zztt();
            this.zzcaf = v0_1;
            __monitor_exit(this);
            return this.zzcaf;
        label_22:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_22;
        }

        throw v0;
    }

    public final int zzvu() {
        if(this.zzcaf != null) {
            return this.zzcaf.size();
        }

        if(this.zzcae != null) {
            return this.zzcae.zzvu();
        }

        return 0;
    }
}

