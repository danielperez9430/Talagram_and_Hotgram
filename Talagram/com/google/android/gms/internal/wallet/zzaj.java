package com.google.android.gms.internal.wallet;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolvableVoidResult;
import com.google.android.gms.wallet.AutoResolveHelper;

final class zzaj extends zzah {
    private final TaskCompletionSource zzgl;

    public zzaj(TaskCompletionSource arg1) {
        super();
        this.zzgl = arg1;
    }

    public final void zza(int arg3, Bundle arg4) {
        Parcelable v4 = arg4.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        Status v0 = v4 == null || arg3 != 6 ? new Status(arg3) : new Status(arg3, "Need to resolve PendingIntent", ((PendingIntent)v4));
        AutoResolveHelper.zza(v0, new AutoResolvableVoidResult(), this.zzgl);
    }
}

