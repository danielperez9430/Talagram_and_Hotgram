package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzba extends Handler {
    zzba(zzav arg1, Looper arg2) {
        this.zzit = arg1;
        super(arg2);
    }

    public final void handleMessage(Message arg4) {
        switch(arg4.what) {
            case 1: {
                goto label_16;
            }
            case 2: {
                goto label_13;
            }
        }

        int v4 = arg4.what;
        StringBuilder v2 = new StringBuilder(31);
        v2.append("Unknown message id: ");
        v2.append(v4);
        Log.w("GoogleApiClientImpl", v2.toString());
        return;
    label_13:
        zzav.zza(this.zzit);
        return;
    label_16:
        zzav.zzb(this.zzit);
    }
}

