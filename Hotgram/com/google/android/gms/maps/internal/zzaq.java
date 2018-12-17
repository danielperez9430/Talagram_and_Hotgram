package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzaq extends zzb implements zzap {
    public zzaq() {
        super("com.google.android.gms.maps.internal.IOnMapReadyCallback");
    }

    protected final boolean dispatchTransaction(int arg2, Parcel arg3, Parcel arg4, int arg5) {
        IInterface v2_2;
        if(arg2 == 1) {
            IBinder v2 = arg3.readStrongBinder();
            if(v2 == null) {
                IGoogleMapDelegate v2_1 = null;
            }
            else {
                IInterface v3 = v2.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if((v3 instanceof IGoogleMapDelegate)) {
                    v2_2 = v3;
                }
                else {
                    zzg v2_3 = new zzg(v2);
                }
            }

            this.zza(((IGoogleMapDelegate)v2_2));
            arg4.writeNoException();
            return 1;
        }

        return 0;
    }
}

