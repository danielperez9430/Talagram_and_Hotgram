package com.google.android.gms.internal.config;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;

public abstract class zzag extends zzb implements zzaf {
    public zzag() {
        super("com.google.android.gms.config.internal.IConfigCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_18;
            }
            case 2: {
                goto label_13;
            }
            case 3: {
                goto label_9;
            }
            case 4: {
                goto label_3;
            }
        }

        return 0;
    label_18:
        this.zza(zzc.zza(arg2, Status.CREATOR), arg2.createByteArray());
        return 1;
    label_3:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, zzad.CREATOR));
        return 1;
    label_9:
        this.zza(zzc.zza(arg2, Status.CREATOR));
        return 1;
    label_13:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2));
        return 1;
    }
}

