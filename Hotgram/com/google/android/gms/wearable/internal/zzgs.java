package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.io.InputStream;

final class zzgs extends zzgm {
    private final zzbr zzeu;

    public zzgs(ResultHolder arg1, zzbr arg2) {
        super(arg1);
        this.zzeu = Preconditions.checkNotNull(arg2);
    }

    public final void zza(zzdm arg4) {
        zzbj v0;
        if(arg4.zzdr != null) {
            v0 = new zzbj(new ParcelFileDescriptor$AutoCloseInputStream(arg4.zzdr));
            this.zzeu.zza(new zzbk(v0));
        }
        else {
            InputStream v0_1 = null;
        }

        ((zzgm)this).zza(new zzbg(new Status(arg4.statusCode), ((InputStream)v0)));
    }
}

