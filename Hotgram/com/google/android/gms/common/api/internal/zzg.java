package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzg extends zzc {
    private final ListenerKey zzea;

    public zzg(ListenerKey arg2, TaskCompletionSource arg3) {
        super(4, arg3);
        this.zzea = arg2;
    }

    public final void zza(Status arg1) {
        super.zza(arg1);
    }

    public final void zza(zzaa arg1, boolean arg2) {
    }

    public final void zza(RuntimeException arg1) {
        super.zza(arg1);
    }

    public final void zzb(zza arg4) {
        Object v0 = arg4.zzbn().remove(this.zzea);
        if(v0 != null) {
            ((zzbv)v0).zzlu.unregisterListener(arg4.zzae(), this.zzdu);
            ((zzbv)v0).zzlt.clearListener();
            return;
        }

        this.zzdu.trySetResult(Boolean.valueOf(false));
    }
}

