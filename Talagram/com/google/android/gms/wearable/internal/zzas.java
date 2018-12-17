package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;
import com.google.android.gms.wearable.ChannelClient$ChannelCallback;

public final class zzas implements ChannelListener {
    private final ChannelCallback zzch;

    public zzas(ChannelCallback arg1) {
        super();
        this.zzch = arg1;
    }

    public final boolean equals(Object arg3) {
        if(this == (((zzas)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return this.zzch.equals(((zzas)arg3).zzch);
            }
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzch.hashCode();
    }

    public final void onChannelClosed(Channel arg2, int arg3, int arg4) {
        this.zzch.onChannelClosed(zzao.zzb(arg2), arg3, arg4);
    }

    public final void onChannelOpened(Channel arg2) {
        this.zzch.onChannelOpened(zzao.zzb(arg2));
    }

    public final void onInputClosed(Channel arg2, int arg3, int arg4) {
        this.zzch.onInputClosed(zzao.zzb(arg2), arg3, arg4);
    }

    public final void onOutputClosed(Channel arg2, int arg3, int arg4) {
        this.zzch.onOutputClosed(zzao.zzb(arg2), arg3, arg4);
    }
}

