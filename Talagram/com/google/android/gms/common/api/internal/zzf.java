package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzf extends zzb {
    private final TaskCompletionSource zzdu;
    private final TaskApiCall zzdy;
    private final StatusExceptionMapper zzdz;

    public zzf(int arg1, TaskApiCall arg2, TaskCompletionSource arg3, StatusExceptionMapper arg4) {
        super(arg1);
        this.zzdu = arg3;
        this.zzdy = arg2;
        this.zzdz = arg4;
    }

    public final Feature[] getRequiredFeatures() {
        return this.zzdy.zzca();
    }

    public final boolean shouldAutoResolveMissingFeatures() {
        return this.zzdy.shouldAutoResolveMissingFeatures();
    }

    public final void zza(Status arg3) {
        this.zzdu.trySetException(this.zzdz.getException(arg3));
    }

    public final void zza(zza arg3) {
        try {
            this.zzdy.doExecute(arg3.zzae(), this.zzdu);
            return;
        }
        catch(RuntimeException v3) {
            ((zzb)this).zza(v3);
            return;
        }
        catch(RemoteException v3_1) {
            ((zzb)this).zza(zzb.zzb(v3_1));
            return;
        }
        catch(DeadObjectException v3_2) {
            throw v3_2;
        }
    }

    public final void zza(zzaa arg2, boolean arg3) {
        arg2.zza(this.zzdu, arg3);
    }

    public final void zza(RuntimeException arg2) {
        this.zzdu.trySetException(((Exception)arg2));
    }
}

