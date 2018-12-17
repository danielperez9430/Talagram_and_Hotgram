package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.places.zzc;
import com.google.android.gms.internal.places.zzd;

public abstract class zzz extends zzc implements zzy {
    public zzz() {
        super("com.google.android.gms.location.places.internal.IPlacesCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_19;
            }
            case 2: {
                goto label_15;
            }
            case 3: {
                goto label_11;
            }
            case 4: {
                goto label_7;
            }
            case 5: {
                goto label_3;
            }
        }

        return 0;
    label_19:
        this.zzb(zzd.zzb(arg2, DataHolder.CREATOR));
        return 1;
    label_3:
        this.zze(zzd.zzb(arg2, DataHolder.CREATOR));
        return 1;
    label_7:
        this.zze(zzd.zzb(arg2, Status.CREATOR));
        return 1;
    label_11:
        this.zzd(zzd.zzb(arg2, DataHolder.CREATOR));
        return 1;
    label_15:
        this.zzc(zzd.zzb(arg2, DataHolder.CREATOR));
        return 1;
    }
}

