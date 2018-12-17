package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzbg extends zzb implements zzbf {
    public zzbg() {
        super("com.google.android.gms.maps.internal.IOnPolylineClickListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.zza(zzaa.zzi(arg2.readStrongBinder()));
            arg3.writeNoException();
            return 1;
        }

        return 0;
    }
}

