package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi$OpenChannelResult;
import javax.annotation.Nullable;

final class zzam implements OpenChannelResult {
    private final Channel zzcd;
    private final Status zzp;

    zzam(Status arg1, @Nullable Channel arg2) {
        super();
        this.zzp = Preconditions.checkNotNull(arg1);
        this.zzcd = arg2;
    }

    @Nullable public final Channel getChannel() {
        return this.zzcd;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

