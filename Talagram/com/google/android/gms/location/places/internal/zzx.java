package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import com.google.android.gms.internal.places.zzc;
import com.google.android.gms.internal.places.zzd;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

public abstract class zzx extends zzc implements zzw {
    public zzx() {
        super("com.google.android.gms.location.places.internal.IPhotosCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 2: {
                goto label_7;
            }
            case 3: {
                goto label_3;
            }
        }

        return 0;
    label_3:
        this.zzb(zzd.zzb(arg2, PlacePhotoMetadataResult.CREATOR));
        return 1;
    label_7:
        this.zzb(zzd.zzb(arg2, PlacePhotoResult.CREATOR));
        return 1;
    }
}

