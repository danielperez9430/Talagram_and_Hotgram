package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;
import com.google.android.gms.wearable.ChannelApi;

public final class zzaj implements ChannelApi {
    public zzaj() {
        super();
    }

    public final PendingResult addListener(GoogleApiClient arg4, ChannelListener arg5) {
        Preconditions.checkNotNull(arg4, "client is null");
        Preconditions.checkNotNull(arg5, "listener is null");
        return zzb.zza(arg4, new zzal(new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.CHANNEL_EVENT")}), arg5);
    }

    public final PendingResult openChannel(GoogleApiClient arg2, String arg3, String arg4) {
        Preconditions.checkNotNull(arg2, "client is null");
        Preconditions.checkNotNull(arg3, "nodeId is null");
        Preconditions.checkNotNull(arg4, "path is null");
        return arg2.enqueue(new zzak(this, arg2, arg3, arg4));
    }

    public final PendingResult removeListener(GoogleApiClient arg3, ChannelListener arg4) {
        Preconditions.checkNotNull(arg3, "client is null");
        Preconditions.checkNotNull(arg4, "listener is null");
        return arg3.enqueue(new zzan(arg3, arg4, null));
    }
}

