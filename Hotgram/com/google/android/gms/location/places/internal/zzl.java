package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zze;
import com.google.android.gms.location.places.zzf;

final class zzl extends zzf {
    zzl(zzi arg1, Api arg2, GoogleApiClient arg3, String arg4, int arg5, int arg6, int arg7) {
        this.zzfu = arg4;
        this.zzfv = arg5;
        this.zzfw = arg6;
        this.zzfx = arg7;
        super(arg2, arg3);
    }

    protected final void doExecute(AnyClient arg7) {
        arg7.zzb(new zze(((zzf)this)), this.zzfu, this.zzfv, this.zzfw, this.zzfx);
    }
}

