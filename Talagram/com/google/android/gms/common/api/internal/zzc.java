package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzc extends zzb {
    protected final TaskCompletionSource zzdu;

    public zzc(int arg1, TaskCompletionSource arg2) {
        super(arg1);
        this.zzdu = arg2;
    }

    public void zza(Status arg3) {
        this.zzdu.trySetException(new ApiException(arg3));
    }

    public final void zza(zza arg2) {
        try {
            this.zzb(arg2);
            return;
        }
        catch(RuntimeException v2) {
            ((zzb)this).zza(v2);
            return;
        }
        catch(RemoteException v2_1) {
            ((zzb)this).zza(zzb.zzb(v2_1));
            return;
        }
        catch(DeadObjectException v2_2) {
            ((zzb)this).zza(zzb.zzb(((RemoteException)v2_2)));
            throw v2_2;
        }
    }

    public void zza(zzaa arg1, boolean arg2) {
    }

    public void zza(RuntimeException arg2) {
        this.zzdu.trySetException(((Exception)arg2));
    }

    protected abstract void zzb(zza arg1);
}

