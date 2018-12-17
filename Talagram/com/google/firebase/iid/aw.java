package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;

public final class aw implements av {
    private final IBinder a;

    aw(IBinder arg1) {
        super();
        this.a = arg1;
    }

    public final void a(Message arg4) {
        Parcel v0 = Parcel.obtain();
        v0.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
        v0.writeInt(1);
        arg4.writeToParcel(v0, 0);
        try {
            this.a.transact(1, v0, null, 1);
        }
        catch(Throwable v4) {
            v0.recycle();
            throw v4;
        }

        v0.recycle();
    }

    public final IBinder asBinder() {
        return this.a;
    }
}

