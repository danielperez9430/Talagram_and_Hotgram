package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityClient$OnCapabilityChangedListener;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzae implements OnCapabilityChangedListener {
    private final String zzbc;
    private final OnCapabilityChangedListener zzby;

    zzae(OnCapabilityChangedListener arg1, String arg2) {
        super();
        this.zzby = arg1;
        this.zzbc = arg2;
    }

    public final boolean equals(Object arg4) {
        if(this == (((zzae)arg4))) {
            return 1;
        }

        if(arg4 != null) {
            if(this.getClass() != arg4.getClass()) {
            }
            else if(!this.zzby.equals(((zzae)arg4).zzby)) {
                return 0;
            }
            else {
                return this.zzbc.equals(((zzae)arg4).zzbc);
            }
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzby.hashCode() * 31 + this.zzbc.hashCode();
    }

    public final void onCapabilityChanged(CapabilityInfo arg2) {
        this.zzby.onCapabilityChanged(arg2);
    }
}

