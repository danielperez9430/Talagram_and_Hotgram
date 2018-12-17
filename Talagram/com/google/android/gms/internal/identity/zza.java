package com.google.android.gms.internal.identity;

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

    protected final void transactAndReadExceptionReturnVoid(int arg4, Parcel arg5) {
        Parcel v4 = Parcel.obtain();
        try {
            this.zza.transact(2, arg5, v4, 0);
            v4.readException();
        }
        catch(Throwable v0) {
            arg5.recycle();
            v4.recycle();
            throw v0;
        }

        arg5.recycle();
        v4.recycle();
    }
}

