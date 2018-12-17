package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.f.l;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageClient$OnMessageReceivedListener;
import com.google.android.gms.wearable.MessageClient;

public final class zzez extends MessageClient {
    @VisibleForTesting private final MessageApi zzei;

    public zzez(Activity arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzei = new zzeu();
    }

    public zzez(Context arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzei = new zzeu();
    }

    public final Task addListener(OnMessageReceivedListener arg4) {
        return this.zza(arg4, new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.MESSAGE_RECEIVED")});
    }

    public final Task addListener(OnMessageReceivedListener arg5, Uri arg6, int arg7) {
        l.a(arg6, "uri must not be null");
        boolean v2 = arg7 == 0 || arg7 == 1 ? true : false;
        Preconditions.checkArgument(v2, "invalid filter type");
        return this.zza(arg5, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.MESSAGE_RECEIVED", arg6, arg7)});
    }

    public final Task removeListener(OnMessageReceivedListener arg3) {
        return this.doUnregisterEventListener(ListenerHolders.createListenerHolder(arg3, this.getLooper(), "MessageListener").getListenerKey());
    }

    public final Task sendMessage(String arg3, String arg4, byte[] arg5) {
        return PendingResultUtil.toTask(this.zzei.sendMessage(this.asGoogleApiClient(), arg3, arg4, arg5), zzfa.zzbx);
    }

    private final Task zza(OnMessageReceivedListener arg4, IntentFilter[] arg5) {
        ListenerHolder v0 = ListenerHolders.createListenerHolder(arg4, this.getLooper(), "MessageListener");
        return this.doRegisterEventListener(new zzfc(arg4, arg5, v0, null), new zzfd(arg4, v0.getListenerKey(), null));
    }
}

