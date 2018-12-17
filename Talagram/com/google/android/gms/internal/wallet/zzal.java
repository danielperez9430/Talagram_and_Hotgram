package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;

final class zzal extends zzah {
    private final TaskCompletionSource zzgl;

    public zzal(TaskCompletionSource arg1) {
        super();
        this.zzgl = arg1;
    }

    public final void zza(Status arg1, PaymentData arg2, Bundle arg3) {
        AutoResolveHelper.zza(arg1, arg2, this.zzgl);
    }
}

