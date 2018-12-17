package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataClient$OnDataChangedListener;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.PutDataRequest;

public final class zzcj extends DataClient {
    private final DataApi zzdi;

    public zzcj(Activity arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzdi = new zzbw();
    }

    public zzcj(Context arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzdi = new zzbw();
    }

    public final Task addListener(OnDataChangedListener arg4) {
        return this.zza(arg4, new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.DATA_CHANGED")});
    }

    public final Task addListener(OnDataChangedListener arg5, Uri arg6, int arg7) {
        Asserts.checkNotNull(arg6, "uri must not be null");
        boolean v2 = arg7 == 0 || arg7 == 1 ? true : false;
        Preconditions.checkArgument(v2, "invalid filter type");
        return this.zza(arg5, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.DATA_CHANGED", arg6, arg7)});
    }

    public final Task deleteDataItems(Uri arg3) {
        return PendingResultUtil.toTask(this.zzdi.deleteDataItems(this.asGoogleApiClient(), arg3), zzcp.zzbx);
    }

    public final Task deleteDataItems(Uri arg3, int arg4) {
        return PendingResultUtil.toTask(this.zzdi.deleteDataItems(this.asGoogleApiClient(), arg3, arg4), zzcq.zzbx);
    }

    public final Task getDataItem(Uri arg3) {
        return PendingResultUtil.toTask(this.zzdi.getDataItem(this.asGoogleApiClient(), arg3), zzcl.zzbx);
    }

    public final Task getDataItems() {
        return PendingResultUtil.toTask(this.zzdi.getDataItems(this.asGoogleApiClient()), zzcm.zzbx);
    }

    public final Task getDataItems(Uri arg3) {
        return PendingResultUtil.toTask(this.zzdi.getDataItems(this.asGoogleApiClient(), arg3), zzcn.zzbx);
    }

    public final Task getDataItems(Uri arg3, int arg4) {
        return PendingResultUtil.toTask(this.zzdi.getDataItems(this.asGoogleApiClient(), arg3, arg4), zzco.zzbx);
    }

    public final Task getFdForAsset(Asset arg3) {
        return PendingResultUtil.toTask(this.zzdi.getFdForAsset(this.asGoogleApiClient(), arg3), zzcr.zzbx);
    }

    public final Task getFdForAsset(DataItemAsset arg3) {
        return PendingResultUtil.toTask(this.zzdi.getFdForAsset(this.asGoogleApiClient(), arg3), zzcs.zzbx);
    }

    public final Task putDataItem(PutDataRequest arg3) {
        return PendingResultUtil.toTask(this.zzdi.putDataItem(this.asGoogleApiClient(), arg3), zzck.zzbx);
    }

    public final Task removeListener(OnDataChangedListener arg3) {
        return this.doUnregisterEventListener(ListenerHolders.createListenerHolder(arg3, this.getLooper(), "DataListener").getListenerKey());
    }

    private final Task zza(OnDataChangedListener arg4, IntentFilter[] arg5) {
        ListenerHolder v0 = ListenerHolders.createListenerHolder(arg4, this.getLooper(), "DataListener");
        return this.doRegisterEventListener(new zzcv(arg4, arg5, v0, null), new zzcw(arg4, v0.getListenerKey(), null));
    }
}

