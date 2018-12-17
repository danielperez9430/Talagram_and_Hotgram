package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;

public final class zzd extends zzb {
    private final ApiMethodImpl zzdv;

    public zzd(int arg1, ApiMethodImpl arg2) {
        super(arg1);
        this.zzdv = arg2;
    }

    public final void zza(Status arg2) {
        this.zzdv.setFailedResult(arg2);
    }

    public final void zza(zza arg2) {
        try {
            this.zzdv.run(arg2.zzae());
            return;
        }
        catch(RuntimeException v2) {
            ((zzb)this).zza(v2);
            return;
        }
    }

    public final void zza(zzaa arg2, boolean arg3) {
        arg2.zza(this.zzdv, arg3);
    }

    public final void zza(RuntimeException arg5) {
        String v1 = arg5.getClass().getSimpleName();
        String v5 = arg5.getLocalizedMessage();
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 2 + String.valueOf(v5).length());
        v3.append(v1);
        v3.append(": ");
        v3.append(v5);
        this.zzdv.setFailedResult(new Status(10, v3.toString()));
    }
}

