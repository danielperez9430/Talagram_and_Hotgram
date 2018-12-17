package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zze extends zzc {
    private final RegisterListenerMethod zzdw;
    private final UnregisterListenerMethod zzdx;

    public zze(zzbv arg2, TaskCompletionSource arg3) {
        super(3, arg3);
        this.zzdw = arg2.zzlt;
        this.zzdx = arg2.zzlu;
    }

    public final void zza(Status arg1) {
        super.zza(arg1);
    }

    public final void zza(zzaa arg1, boolean arg2) {
    }

    public final void zza(RuntimeException arg1) {
        super.zza(arg1);
    }

    public final void zzb(zza arg5) {
        this.zzdw.registerListener(arg5.zzae(), this.zzdu);
        if(this.zzdw.getListenerKey() != null) {
            arg5.zzbn().put(this.zzdw.getListenerKey(), new zzbv(this.zzdw, this.zzdx));
        }
    }
}

