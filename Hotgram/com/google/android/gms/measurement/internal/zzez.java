package com.google.android.gms.measurement.internal;

abstract class zzez extends zzey {
    private boolean zzvz;

    zzez(zzfa arg1) {
        super(arg1);
        this.zzamz.zzb(this);
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

    protected abstract boolean zzgt();

    public final void zzq() {
        if(!this.zzvz) {
            this.zzgt();
            this.zzamz.zzma();
            this.zzvz = true;
            return;
        }

        throw new IllegalStateException("Can\'t initialize twice");
    }
}

