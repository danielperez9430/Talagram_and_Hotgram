package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor$AutoCloseOutputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.io.OutputStream;

final class zzgt extends zzgm {
    private final zzbr zzeu;

    public zzgt(ResultHolder arg1, zzbr arg2) {
        super(arg1);
        this.zzeu = Preconditions.checkNotNull(arg2);
    }

    public final void zza(zzdo arg4) {
        OutputStream v0_1;
        if(arg4.zzdr != null) {
            zzbl v0 = new zzbl(new ParcelFileDescriptor$AutoCloseOutputStream(arg4.zzdr));
            this.zzeu.zza(new zzbm(v0));
        }
        else {
            v0_1 = null;
        }

        ((zzgm)this).zza(new zzbh(new Status(arg4.statusCode), v0_1));
    }
}

