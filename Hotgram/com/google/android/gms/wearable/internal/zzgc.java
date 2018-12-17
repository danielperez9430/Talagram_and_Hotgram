package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;

final class zzgc implements ChannelListener {
    private final String zzce;
    private final ChannelListener zzeq;

    zzgc(String arg1, ChannelListener arg2) {
        super();
        this.zzce = Preconditions.checkNotNull(arg1);
        this.zzeq = Preconditions.checkNotNull(arg2);
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzgc)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof zzgc)) {
            return 0;
        }

        if((this.zzeq.equals(((zzgc)arg5).zzeq)) && (this.zzce.equals(((zzgc)arg5).zzce))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzce.hashCode() * 31 + this.zzeq.hashCode();
    }

    public final void onChannelClosed(Channel arg2, int arg3, int arg4) {
        this.zzeq.onChannelClosed(arg2, arg3, arg4);
    }

    public final void onChannelOpened(Channel arg2) {
        this.zzeq.onChannelOpened(arg2);
    }

    public final void onInputClosed(Channel arg2, int arg3, int arg4) {
        this.zzeq.onInputClosed(arg2, arg3, arg4);
    }

    public final void onOutputClosed(Channel arg2, int arg3, int arg4) {
        this.zzeq.onOutputClosed(arg2, arg3, arg4);
    }
}

