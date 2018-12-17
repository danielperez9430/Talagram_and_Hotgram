package com.google.android.gms.internal.firebase_abt;

public final class zzn extends zzd {
    private static volatile zzn[] zzap;
    public String zzaq;

    public zzn() {
        super();
        this.zzaq = "";
        this.zzs = null;
        this.zzab = -1;
    }

    public final zzj zza(zza arg3) {
        while(true) {
            int v0 = arg3.zzd();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 10) {
                if(super.zza(arg3, v0)) {
                    continue;
                }

                return this;
            }

            this.zzaq = arg3.readString();
        }

        return this;
    }

    public static zzn[] zzo() {
        if(zzn.zzap == null) {
            Object v0 = zzh.zzaa;
            __monitor_enter(v0);
            try {
                if(zzn.zzap == null) {
                    zzn.zzap = new zzn[0];
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
        return zzn.zzap;
    }
}

