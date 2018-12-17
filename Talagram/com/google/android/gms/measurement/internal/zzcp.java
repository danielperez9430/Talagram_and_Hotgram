package com.google.android.gms.measurement.internal;

abstract class zzcp extends zzco {
    private boolean zzvz;

    zzcp(zzbt arg1) {
        super(arg1);
        this.zzadj.zzb(this);
    }

    final boolean isInitialized() {
        if(this.zzvz) {
            return 1;
        }

        return 0;
    }

    protected final void zzcl() {
        if(this.isInitialized()) {
            return;
        }

        throw new IllegalStateException("Not initialized");
    }

    public final void zzgs() {
        if(!this.zzvz) {
            this.zzgu();
            this.zzadj.zzkq();
            this.zzvz = true;
            return;
        }

        throw new IllegalStateException("Can\'t initialize twice");
    }

    protected abstract boolean zzgt();

    protected void zzgu() {
    }

    public final void zzq() {
        if(!this.zzvz) {
            if(!this.zzgt()) {
                this.zzadj.zzkq();
                this.zzvz = true;
            }

            return;
        }

        throw new IllegalStateException("Can\'t initialize twice");
    }
}

