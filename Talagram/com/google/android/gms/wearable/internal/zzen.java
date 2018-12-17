package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.wearable.zzb;
import com.google.android.gms.internal.wearable.zzc;

public abstract class zzen extends zzb implements zzem {
    public zzen() {
        super("com.google.android.gms.wearable.internal.IWearableListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_35;
            }
            case 2: {
                goto label_31;
            }
            case 3: {
                goto label_27;
            }
            case 4: {
                goto label_23;
            }
            case 5: {
                goto label_19;
            }
            case 6: {
                goto label_15;
            }
            case 7: {
                goto label_11;
            }
            case 8: {
                goto label_7;
            }
            case 9: {
                goto label_3;
            }
        }

        return 0;
    label_35:
        this.zza(zzc.zza(arg2, DataHolder.CREATOR));
        return 1;
    label_19:
        this.onConnectedNodes(arg2.createTypedArrayList(zzfo.CREATOR));
        return 1;
    label_3:
        this.zza(zzc.zza(arg2, zzi.CREATOR));
        return 1;
    label_23:
        this.zzb(zzc.zza(arg2, zzfo.CREATOR));
        return 1;
    label_7:
        this.zza(zzc.zza(arg2, zzah.CREATOR));
        return 1;
    label_27:
        this.zza(zzc.zza(arg2, zzfo.CREATOR));
        return 1;
    label_11:
        this.zza(zzc.zza(arg2, zzaw.CREATOR));
        return 1;
    label_31:
        this.zza(zzc.zza(arg2, zzfe.CREATOR));
        return 1;
    label_15:
        this.zza(zzc.zza(arg2, zzl.CREATOR));
        return 1;
    }
}

