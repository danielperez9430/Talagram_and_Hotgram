package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityClient$OnCapabilityChangedListener;
import com.google.android.gms.wearable.CapabilityClient;

public final class zzaa extends CapabilityClient {
    private final CapabilityApi zzbw;

    public zzaa(Activity arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzbw = new zzo();
    }

    public zzaa(Context arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzbw = new zzo();
    }

    public final Task addListener(OnCapabilityChangedListener arg5, Uri arg6, int arg7) {
        Asserts.checkNotNull(arg5, "listener must not be null");
        Asserts.checkNotNull(arg6, "uri must not be null");
        boolean v2 = arg7 == 0 || arg7 == 1 ? true : false;
        Preconditions.checkArgument(v2, "invalid filter type");
        return this.zza(ListenerHolders.createListenerHolder(arg5, this.getLooper(), "CapabilityListener"), arg5, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.CAPABILITY_CHANGED", arg6, arg7)});
    }

    public final Task addListener(OnCapabilityChangedListener arg6, String arg7) {
        String v1;
        Asserts.checkNotNull(arg6, "listener must not be null");
        Asserts.checkNotNull(arg7, "capability must not be null");
        IntentFilter v0 = zzgj.zzc("com.google.android.gms.wearable.CAPABILITY_CHANGED");
        if(!arg7.startsWith("/")) {
            v1 = "/";
            arg7 = String.valueOf(arg7);
            arg7 = arg7.length() != 0 ? v1.concat(arg7) : new String(v1);
        }

        v0.addDataPath(arg7, 0);
        IntentFilter[] v2 = new IntentFilter[]{v0};
        Looper v0_1 = this.getLooper();
        v1 = "CapabilityListener:";
        String v3 = String.valueOf(arg7);
        v1 = v3.length() != 0 ? v1.concat(v3) : new String(v1);
        return this.zza(ListenerHolders.createListenerHolder(arg6, v0_1, v1), new zzae(arg6, arg7), v2);
    }

    public final Task addLocalCapability(String arg3) {
        Asserts.checkNotNull(arg3, "capability must not be null");
        return PendingResultUtil.toVoidTask(this.zzbw.addLocalCapability(this.asGoogleApiClient(), arg3));
    }

    public final Task getAllCapabilities(int arg3) {
        return PendingResultUtil.toTask(this.zzbw.getAllCapabilities(this.asGoogleApiClient(), arg3), zzac.zzbx);
    }

    public final Task getCapability(String arg3, int arg4) {
        Asserts.checkNotNull(arg3, "capability must not be null");
        return PendingResultUtil.toTask(this.zzbw.getCapability(this.asGoogleApiClient(), arg3, arg4), zzab.zzbx);
    }

    public final Task removeListener(OnCapabilityChangedListener arg3) {
        Asserts.checkNotNull(arg3, "listener must not be null");
        return this.doUnregisterEventListener(ListenerHolders.createListenerHolder(arg3, this.getLooper(), "CapabilityListener").getListenerKey());
    }

    public final Task removeListener(OnCapabilityChangedListener arg4, String arg5) {
        Asserts.checkNotNull(arg4, "listener must not be null");
        Asserts.checkNotNull(arg5, "capability must not be null");
        if(!arg5.startsWith("/")) {
            String v0 = "/";
            arg5 = String.valueOf(arg5);
            arg5 = arg5.length() != 0 ? v0.concat(arg5) : new String(v0);
        }

        Looper v0_1 = this.getLooper();
        String v1 = "CapabilityListener:";
        arg5 = String.valueOf(arg5);
        arg5 = arg5.length() != 0 ? v1.concat(arg5) : new String(v1);
        return this.doUnregisterEventListener(ListenerHolders.createListenerHolder(arg4, v0_1, arg5).getListenerKey());
    }

    public final Task removeLocalCapability(String arg3) {
        Asserts.checkNotNull(arg3, "capability must not be null");
        return PendingResultUtil.toVoidTask(this.zzbw.removeLocalCapability(this.asGoogleApiClient(), arg3));
    }

    private final Task zza(ListenerHolder arg3, OnCapabilityChangedListener arg4, IntentFilter[] arg5) {
        return this.doRegisterEventListener(new zzaf(arg4, arg5, arg3, null), new zzag(arg4, arg3.getListenerKey(), null));
    }
}

