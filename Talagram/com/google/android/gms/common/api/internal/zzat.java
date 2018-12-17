package com.google.android.gms.common.api.internal;

abstract class zzat implements Runnable {
    private zzat(zzaj arg1) {
        this.zzhv = arg1;
        super();
    }

    zzat(zzaj arg1, zzak arg2) {
        this(arg1);
    }

    public void run() {
        zzaj.zzc(this.zzhv).lock();
        try {
            if(Thread.interrupted()) {
                goto label_5;
            }

            this.zzaq();
        }
        catch(Throwable v0) {
        }
        catch(RuntimeException v0_1) {
            try {
                zzaj.zzd(this.zzhv).zzb(v0_1);
            }
            catch(Throwable v0) {
                zzaj.zzc(this.zzhv).unlock();
                throw v0;
            }
        }

    label_5:
        zzaj.zzc(this.zzhv).unlock();
    }

    protected abstract void zzaq();
}

