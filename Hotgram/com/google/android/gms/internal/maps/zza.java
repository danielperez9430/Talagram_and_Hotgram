package com.google.android.gms.internal.maps;

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

    protected final Parcel transactAndReadException(int arg4, Parcel arg5) {
        Parcel v0 = Parcel.obtain();
        try {
            this.zza.transact(arg4, arg5, v0, 0);
            v0.readException();
        }
        catch(Throwable v4) {
        }
        catch(RuntimeException v4_1) {
            try {
                v0.recycle();
                throw v4_1;
            }
            catch(Throwable v4) {
                arg5.recycle();
                throw v4;
            }
        }

        arg5.recycle();
        return v0;
    }

    protected final void transactAndReadExceptionReturnVoid(int arg4, Parcel arg5) {
        Parcel v0 = Parcel.obtain();
        try {
            this.zza.transact(arg4, arg5, v0, 0);
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

