package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.CapabilityApi$CapabilityListener;
import com.google.android.gms.wearable.CapabilityApi;

public final class zzo implements CapabilityApi {
    public zzo() {
        super();
    }

    public final PendingResult addCapabilityListener(GoogleApiClient arg4, CapabilityListener arg5, String arg6) {
        Asserts.checkNotNull(arg6, "capability must not be null");
        zzv v0 = new zzv(arg5, arg6);
        IntentFilter v5 = zzgj.zzc("com.google.android.gms.wearable.CAPABILITY_CHANGED");
        if(!arg6.startsWith("/")) {
            String v1 = "/";
            arg6 = String.valueOf(arg6);
            arg6 = arg6.length() != 0 ? v1.concat(arg6) : new String(v1);
        }

        v5.addDataPath(arg6, 0);
        return zzo.zza(arg4, ((CapabilityListener)v0), new IntentFilter[]{v5});
    }

    public final PendingResult addListener(GoogleApiClient arg5, CapabilityListener arg6, Uri arg7, int arg8) {
        Asserts.checkNotNull(arg7, "uri must not be null");
        boolean v2 = arg8 == 0 || arg8 == 1 ? true : false;
        Preconditions.checkArgument(v2, "invalid filter type");
        return zzo.zza(arg5, arg6, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.CAPABILITY_CHANGED", arg7, arg8)});
    }

    public final PendingResult addLocalCapability(GoogleApiClient arg2, String arg3) {
        return arg2.enqueue(new zzr(this, arg2, arg3));
    }

    public final PendingResult getAllCapabilities(GoogleApiClient arg2, int arg3) {
        boolean v0 = true;
        if(arg3 != 0) {
            if(arg3 == 1) {
            }
            else {
                v0 = false;
            }
        }

        Preconditions.checkArgument(v0);
        return arg2.enqueue(new zzq(this, arg2, arg3));
    }

    public final PendingResult getCapability(GoogleApiClient arg2, String arg3, int arg4) {
        boolean v0 = true;
        if(arg4 != 0) {
            if(arg4 == 1) {
            }
            else {
                v0 = false;
            }
        }

        Preconditions.checkArgument(v0);
        return arg2.enqueue(new zzp(this, arg2, arg3, arg4));
    }

    public final PendingResult removeCapabilityListener(GoogleApiClient arg2, CapabilityListener arg3, String arg4) {
        return arg2.enqueue(new zzz(arg2, new zzv(arg3, arg4), null));
    }

    public final PendingResult removeListener(GoogleApiClient arg3, CapabilityListener arg4) {
        return arg3.enqueue(new zzz(arg3, arg4, null));
    }

    public final PendingResult removeLocalCapability(GoogleApiClient arg2, String arg3) {
        return arg2.enqueue(new zzs(this, arg2, arg3));
    }

    private static PendingResult zza(GoogleApiClient arg1, CapabilityListener arg2, IntentFilter[] arg3) {
        return zzb.zza(arg1, new zzt(arg3), arg2);
    }
}

