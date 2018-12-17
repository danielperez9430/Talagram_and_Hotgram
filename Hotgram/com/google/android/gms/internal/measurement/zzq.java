package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class zzq implements IInterface {
    private final IBinder zzqt;
    private final String zzqu;

    protected zzq(IBinder arg1, String arg2) {
        super();
        this.zzqt = arg1;
        this.zzqu = arg2;
    }

    public IBinder asBinder() {
        return this.zzqt;
    }

    protected final Parcel obtainAndWriteInterfaceToken() {
        Parcel v0 = Parcel.obtain();
        v0.writeInterfaceToken(this.zzqu);
        return v0;
    }

    protected final Parcel transactAndReadException(int arg4, Parcel arg5) {
        Parcel v0 = Parcel.obtain();
        try {
            this.zzqt.transact(arg4, arg5, v0, 0);
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
            this.zzqt.transact(arg4, arg5, v0, 0);
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

    protected final void transactOneway(int arg3, Parcel arg4) {
        try {
            this.zzqt.transact(1, arg4, null, 1);
        }
        catch(Throwable v3) {
            arg4.recycle();
            throw v3;
        }

        arg4.recycle();
    }
}

