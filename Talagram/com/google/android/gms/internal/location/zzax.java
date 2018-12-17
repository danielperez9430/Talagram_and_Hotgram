package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.zzy;

final class zzax extends zzy {
    private final ListenerHolder zzda;

    zzax(ListenerHolder arg1) {
        super();
        this.zzda = arg1;
    }

    public final void onLocationChanged(Location arg3) {
        __monitor_enter(this);
        try {
            this.zzda.notifyListener(new zzay(this, arg3));
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public final void release() {
        __monitor_enter(this);
        try {
            this.zzda.clear();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

