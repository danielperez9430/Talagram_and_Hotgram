package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.MessageApi$MessageListener;
import com.google.android.gms.wearable.MessageApi;

public final class zzeu implements MessageApi {
    public zzeu() {
        super();
    }

    public final PendingResult addListener(GoogleApiClient arg4, MessageListener arg5) {
        return zzeu.zza(arg4, arg5, new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.MESSAGE_RECEIVED")});
    }

    public final PendingResult addListener(GoogleApiClient arg5, MessageListener arg6, Uri arg7, int arg8) {
        Preconditions.checkNotNull(arg7, "uri must not be null");
        boolean v2 = arg8 == 0 || arg8 == 1 ? true : false;
        Preconditions.checkArgument(v2, "invalid filter type");
        return zzeu.zza(arg5, arg6, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.MESSAGE_RECEIVED", arg7, arg8)});
    }

    public final PendingResult removeListener(GoogleApiClient arg2, MessageListener arg3) {
        return arg2.enqueue(new zzew(this, arg2, arg3));
    }

    public final PendingResult sendMessage(GoogleApiClient arg8, String arg9, String arg10, byte[] arg11) {
        return arg8.enqueue(new zzev(this, arg8, arg9, arg10, arg11));
    }

    private static PendingResult zza(GoogleApiClient arg7, MessageListener arg8, IntentFilter[] arg9) {
        return arg7.enqueue(new zzex(arg7, arg8, arg7.registerListener(arg8), arg9, null));
    }
}

