package com.google.android.gms.internal.stable;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class zzb extends Binder implements IInterface {
    private static zzd zzc;

    static {
    }

    protected zzb(String arg1) {
        super();
        this.attachInterface(((IInterface)this), arg1);
    }

    public IBinder asBinder() {
        return this;
    }

    protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        return 0;
    }

    public boolean onTransact(int arg2, Parcel arg3, Parcel arg4, int arg5) {
        if(this.routeToSuperOrEnforceInterface(arg2, arg3, arg4, arg5)) {
            return 1;
        }

        return this.dispatchTransaction(arg2, arg3, arg4, arg5);
    }

    protected boolean routeToSuperOrEnforceInterface(int arg2, Parcel arg3, Parcel arg4, int arg5) {
        if(arg2 > 16777215) {
            return super.onTransact(arg2, arg3, arg4, arg5);
        }

        arg3.enforceInterface(this.getInterfaceDescriptor());
        return 0;
    }
}

