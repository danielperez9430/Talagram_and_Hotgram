package com.google.android.gms.common.api.internal;

final class zzbh implements BackgroundStateChangeListener {
    zzbh(GoogleApiManager arg1) {
        this.zzjy = arg1;
        super();
    }

    public final void onBackgroundStateChanged(boolean arg4) {
        GoogleApiManager.zza(this.zzjy).sendMessage(GoogleApiManager.zza(this.zzjy).obtainMessage(1, Boolean.valueOf(arg4)));
    }
}

