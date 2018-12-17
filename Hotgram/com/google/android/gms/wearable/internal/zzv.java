package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityApi$CapabilityListener;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzv implements CapabilityListener {
    private final String zzbc;
    private final CapabilityListener zzbs;

    zzv(CapabilityListener arg1, String arg2) {
        super();
        this.zzbs = arg1;
        this.zzbc = arg2;
    }

    public final boolean equals(Object arg4) {
        if(this == (((zzv)arg4))) {
            return 1;
        }

        if(arg4 != null) {
            if(this.getClass() != arg4.getClass()) {
            }
            else if(!this.zzbs.equals(((zzv)arg4).zzbs)) {
                return 0;
            }
            else {
                return this.zzbc.equals(((zzv)arg4).zzbc);
            }
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzbs.hashCode() * 31 + this.zzbc.hashCode();
    }

    public final void onCapabilityChanged(CapabilityInfo arg2) {
        this.zzbs.onCapabilityChanged(arg2);
    }
}

