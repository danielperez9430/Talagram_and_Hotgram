package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzm extends zzb implements zzl {
    public zzm() {
        super("com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_43;
            }
            case 2: {
                goto label_39;
            }
            case 3: {
                goto label_34;
            }
            case 4: {
                goto label_30;
            }
            case 5: {
                goto label_25;
            }
            case 6: {
                goto label_19;
            }
            case 7: {
                goto label_15;
            }
            case 8: {
                goto label_9;
            }
            case 9: {
                goto label_3;
            }
        }

        return 0;
    label_34:
        this.zza(zzc.zza(arg2, Status.CREATOR), arg2.readLong());
        return 1;
    label_19:
        this.zza(zzc.zza(arg2, Status.CREATOR), arg2.createTypedArray(zze.CREATOR));
        return 1;
    label_3:
        this.zzb(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, com.google.android.gms.clearcut.zzc.CREATOR));
        return 1;
    label_39:
        this.zzb(zzc.zza(arg2, Status.CREATOR));
        return 1;
    label_25:
        this.zzb(zzc.zza(arg2, Status.CREATOR), arg2.readLong());
        return 1;
    label_9:
        this.zza(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, com.google.android.gms.clearcut.zzc.CREATOR));
        return 1;
    label_43:
        this.zza(zzc.zza(arg2, Status.CREATOR));
        return 1;
    label_30:
        this.zzc(zzc.zza(arg2, Status.CREATOR));
        return 1;
    label_15:
        this.zza(zzc.zza(arg2, DataHolder.CREATOR));
        return 1;
    }
}

