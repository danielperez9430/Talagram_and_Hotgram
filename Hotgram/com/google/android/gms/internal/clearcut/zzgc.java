package com.google.android.gms.internal.clearcut;

public final class zzgc extends zzd implements zzdq {
    public final class zza extends zzc implements zzdq {
        zza(zzgd arg1) {
            this();
        }

        private zza() {
            super(zzgc.zzes());
        }
    }

    private static volatile zzdz zzbg;
    private byte zzsf;
    private static final zzgc zzsg;

    static {
        zzgc.zzsg = new zzgc();
        zzcg.zza(zzgc.class, zzgc.zzsg);
    }

    private zzgc() {
        super();
        this.zzsf = 2;
    }

    protected final Object zza(int arg2, Object arg3, Object arg4) {
        zzb v2_2;
        int v0 = 1;
        arg4 = null;
        switch(zzgd.zzba[arg2 - 1]) {
            case 1: {
                goto label_42;
            }
            case 2: {
                goto label_39;
            }
            case 3: {
                goto label_35;
            }
            case 4: {
                goto label_33;
            }
            case 5: {
                goto label_17;
            }
            case 6: {
                goto label_14;
            }
            case 7: {
                goto label_9;
            }
        }

        throw new UnsupportedOperationException();
    label_33:
        return zzgc.zzsg;
    label_17:
        zzdz v2 = zzgc.zzbg;
        if(v2 == null) {
            Class v3 = zzgc.class;
            __monitor_enter(v3);
            try {
                v2 = zzgc.zzbg;
                if(v2 == null) {
                    v2_2 = new zzb(zzgc.zzsg);
                    zzgc.zzbg = ((zzdz)v2_2);
                }

                __monitor_exit(v3);
                return v2_2;
            label_30:
                __monitor_exit(v3);
            }
            catch(Throwable v2_1) {
                goto label_30;
            }

            throw v2_1;
        }

        return v2_2;
    label_35:
        return zzgc.zza(zzgc.zzsg, "\u0003\u0000", ((Object[])arg4));
    label_39:
        return new zza(((zzgd)arg4));
    label_9:
        if(arg3 == null) {
            v0 = 0;
        }

        this.zzsf = ((byte)v0);
        return arg4;
    label_42:
        return new zzgc();
    label_14:
        return Byte.valueOf(this.zzsf);
    }

    public static zzgc zzer() {
        return zzgc.zzsg;
    }

    static zzgc zzes() {
        return zzgc.zzsg;
    }
}

