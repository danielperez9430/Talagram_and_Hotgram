package com.google.android.gms.internal.places;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class zzb implements IInterface {
    private final IBinder zzb;
    private final String zzc;

    protected zzb(IBinder arg1, String arg2) {
        super();
        this.zzb = arg1;
        this.zzc = arg2;
    }

    public IBinder asBinder() {
        return this.zzb;
    }

    protected final Parcel obtainAndWriteInterfaceToken() {
        Parcel v0 = Parcel.obtain();
        v0.writeInterfaceToken(this.zzc);
        return v0;
    }

    protected final void transactAndReadExceptionReturnVoid(int arg4, Parcel arg5) {
        Parcel v0 = Parcel.obtain();
        try {
            this.zzb.transact(arg4, arg5, v0, 0);
            v0.readException();
        }
        catch(Throwable v4) {
            arg5.recycle();
            v0.recycle();
            throw v4;
        }

        arg5.recycle();
        v0.recycle();
    }
}

