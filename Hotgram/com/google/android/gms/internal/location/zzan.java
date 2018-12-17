package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;

public abstract class zzan extends zzb implements zzam {
    public zzan() {
        super("com.google.android.gms.location.internal.IGeofencerCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_12;
            }
            case 2: {
                goto label_8;
            }
            case 3: {
                goto label_3;
            }
        }

        return 0;
    label_3:
        this.zza(arg2.readInt(), zzc.zza(arg2, PendingIntent.CREATOR));
        return 1;
    label_8:
        this.zzb(arg2.readInt(), arg2.createStringArray());
        return 1;
    label_12:
        this.zza(arg2.readInt(), arg2.createStringArray());
        return 1;
    }
}

