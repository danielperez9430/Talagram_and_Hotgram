package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;
import com.google.android.gms.wearable.ChannelApi$OpenChannelResult;
import com.google.android.gms.wearable.ChannelClient$Channel;
import com.google.android.gms.wearable.ChannelClient$ChannelCallback;
import com.google.android.gms.wearable.ChannelClient;

public final class zzao extends ChannelClient {
    private final zzaj zzcg;

    public zzao(Activity arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzcg = new zzaj();
    }

    public zzao(Context arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzcg = new zzaj();
    }

    public final Task close(Channel arg2) {
        return PendingResultUtil.toVoidTask(zzao.zza(arg2).close(this.asGoogleApiClient()));
    }

    public final Task close(Channel arg2, int arg3) {
        return PendingResultUtil.toVoidTask(zzao.zza(arg2).close(this.asGoogleApiClient(), arg3));
    }

    public final Task getInputStream(Channel arg2) {
        return PendingResultUtil.toTask(zzao.zza(arg2).getInputStream(this.asGoogleApiClient()), zzaq.zzbx);
    }

    public final Task getOutputStream(Channel arg2) {
        return PendingResultUtil.toTask(zzao.zza(arg2).getOutputStream(this.asGoogleApiClient()), zzar.zzbx);
    }

    public final Task openChannel(String arg3, String arg4) {
        return PendingResultUtil.toTask(this.zzcg.openChannel(this.asGoogleApiClient(), arg3, arg4), zzap.zzbx);
    }

    public final Task receiveFile(Channel arg2, Uri arg3, boolean arg4) {
        return PendingResultUtil.toVoidTask(zzao.zza(arg2).receiveFile(this.asGoogleApiClient(), arg3, arg4));
    }

    public final Task registerChannelCallback(Channel arg9, ChannelCallback arg10) {
        String v9 = ((zzay)arg9).zzc();
        Preconditions.checkNotNull(arg10, "listener is null");
        Looper v0 = this.getLooper();
        String v1 = "ChannelListener:";
        String v2 = String.valueOf(v9);
        v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
        ListenerHolder v6 = ListenerHolders.createListenerHolder(arg10, v0, v1);
        IntentFilter[] v3 = new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.CHANNEL_EVENT")};
        zzas v7 = new zzas(arg10);
        return this.doRegisterEventListener(new zzat(v7, v9, v3, v6, ListenerHolders.createListenerHolder(v7, this.getLooper(), "ChannelListener")), new zzau(((ChannelListener)v7), v9, v6.getListenerKey()));
    }

    public final Task registerChannelCallback(ChannelCallback arg9) {
        Preconditions.checkNotNull(arg9, "listener is null");
        ListenerHolder v0 = ListenerHolders.createListenerHolder(arg9, this.getLooper(), "ChannelListener");
        IntentFilter[] v5 = new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.CHANNEL_EVENT")};
        zzas v1 = new zzas(arg9);
        return this.doRegisterEventListener(new zzat(v1, null, v5, v0, ListenerHolders.createListenerHolder(v1, this.getLooper(), "ChannelListener")), new zzau(((ChannelListener)v1), null, v0.getListenerKey()));
    }

    public final Task sendFile(Channel arg2, Uri arg3) {
        return PendingResultUtil.toVoidTask(zzao.zza(arg2).sendFile(this.asGoogleApiClient(), arg3));
    }

    public final Task sendFile(Channel arg8, Uri arg9, long arg10, long arg12) {
        return PendingResultUtil.toVoidTask(zzao.zza(arg8).sendFile(this.asGoogleApiClient(), arg9, arg10, arg12));
    }

    public final Task unregisterChannelCallback(Channel arg4, ChannelCallback arg5) {
        String v4 = zzao.zza(arg4).zzc();
        Looper v0 = this.getLooper();
        String v1 = "ChannelListener:";
        v4 = String.valueOf(v4);
        v4 = v4.length() != 0 ? v1.concat(v4) : new String(v1);
        return this.doUnregisterEventListener(ListenerHolders.createListenerHolder(arg5, v0, v4).getListenerKey());
    }

    public final Task unregisterChannelCallback(ChannelCallback arg3) {
        return this.doUnregisterEventListener(ListenerHolders.createListenerHolder(arg3, this.getLooper(), "ChannelListener").getListenerKey());
    }

    static final Channel zza(OpenChannelResult arg0) {
        return zzao.zza(arg0.getChannel());
    }

    private static zzay zza(com.google.android.gms.wearable.Channel arg1) {
        Preconditions.checkNotNull(arg1, "channel must not be null");
        return arg1;
    }

    private static zzay zza(Channel arg1) {
        Preconditions.checkNotNull(arg1, "channel must not be null");
        return arg1;
    }

    static zzay zzb(com.google.android.gms.wearable.Channel arg0) {
        return zzao.zza(arg0);
    }
}

