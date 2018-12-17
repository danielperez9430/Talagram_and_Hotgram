package com.google.android.gms.internal.clearcut;

import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

final class zzh extends ApiMethodImpl {
    private final zze zzao;

    zzh(zze arg2, GoogleApiClient arg3) {
        super(ClearcutLogger.API, arg3);
        this.zzao = arg2;
    }

    protected final Result createFailedResult(Status arg1) {
        return arg1;
    }

    protected final void doExecute(AnyClient arg7) {
        zzi v0 = new zzi(this);
        try {
            zze v1 = this.zzao;
            if(v1.zzt != null && v1.zzaa.zzbjp.length == 0) {
                v1.zzaa.zzbjp = v1.zzt.zza();
            }

            if(v1.zzan != null && v1.zzaa.zzbjw.length == 0) {
                v1.zzaa.zzbjw = v1.zzan.zza();
            }

            zzha v2 = v1.zzaa;
            byte[] v3 = new byte[((zzfz)v2).zzas()];
            zzfz.zza(((zzfz)v2), v3, 0, v3.length);
            v1.zzah = v3;
        }
        catch(RuntimeException v7) {
            Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", ((Throwable)v7));
            this.setFailedResult(new Status(10, "MessageProducer"));
            return;
        }

        ((zzj)arg7).getService().zza(((zzl)v0), this.zzao);
    }
}

