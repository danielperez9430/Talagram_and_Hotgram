package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

final class zzd implements zza {
    zzd(DeferredLifecycleHelper arg1, FrameLayout arg2, LayoutInflater arg3, ViewGroup arg4, Bundle arg5) {
        this.zzabg = arg1;
        this.zzabj = arg2;
        this.zzabk = arg3;
        this.val$container = arg4;
        this.zzabi = arg5;
        super();
    }

    public final int getState() {
        return 2;
    }

    public final void zza(LifecycleDelegate arg5) {
        this.zzabj.removeAllViews();
        this.zzabj.addView(DeferredLifecycleHelper.zzb(this.zzabg).onCreateView(this.zzabk, this.val$container, this.zzabi));
    }
}

