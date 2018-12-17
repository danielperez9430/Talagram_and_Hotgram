package com.google.android.gms.internal.config;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.android.gms.common.data.DataHolder$Builder;
import com.google.android.gms.common.data.DataHolder;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;

final class zzp extends zzs {
    zzp(zzo arg1, GoogleApiClient arg2, zzi arg3) {
        this.zzn = arg3;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzu(arg3, new HashMap());
    }

    protected final void zza(Context arg17, zzah arg18) {
        String v10;
        String v4;
        zzp v1 = this;
        Builder v0 = DataBufferSafeParcelable.buildDataHolder();
        Iterator v2 = v1.zzn.zzb().entrySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            DataBufferSafeParcelable.addValue(v0, new zzz(((Map$Entry)v3).getKey(), ((Map$Entry)v3).getValue()));
        }

        DataHolder v2_1 = v0.build(0);
        String v3_1 = null;
        try {
            v4 = FirebaseInstanceId.a().c();
        }
        catch(IllegalStateException v0_1) {
            v4 = v3_1;
            goto label_28;
        }

        try {
            v10 = FirebaseInstanceId.a().d();
            goto label_36;
        }
        catch(IllegalStateException v0_1) {
        }

    label_28:
        if(Log.isLoggable("ConfigApiImpl", 3)) {
            Log.d("ConfigApiImpl", "Cannot retrieve instanceId or instanceIdToken.", ((Throwable)v0_1));
        }

        v10 = v3_1;
    label_36:
        zzab v0_2 = new zzab(arg17.getPackageName(), v1.zzn.zza(), v2_1, v1.zzn.getGmpAppId(), v4, v10, null, v1.zzn.zzc(), zzn.zzb(arg17), v1.zzn.zzd(), v1.zzn.zze());
        try {
            arg18.zza(v1.zzo, v0_2);
        }
        catch(Throwable v0_3) {
            v2_1.close();
            throw v0_3;
        }

        v2_1.close();
    }
}

