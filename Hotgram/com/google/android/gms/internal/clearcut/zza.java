package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class zza implements IInterface {
    private final IBinder zza;
    private final String zzb;

    protected zza(IBinder arg1, String arg2) {
        super();
        this.zza = arg1;
        this.zzb = arg2;
    }

    public IBinder asBinder() {
        return this.zza;
    }

    protected final Parcel obtainAndWriteInterfaceToken() {
        Parcel v0 = Parcel.obtain();
        v0.writeInterfaceToken(this.zzb);
        return v0;
    }

    protected final void transactOneway(int arg3, Parcel arg4) {
        try {
            this.zza.transact(1, arg4, null, 1);
        }
        catch(Throwable v3) {
            arg4.recycle();
            throw v3;
        }

        arg4.recycle();
    }
}

