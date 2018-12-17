package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

final class k {
    private final Messenger a;
    private final zzk b;

    k(IBinder arg4) {
        super();
        String v0 = arg4.getInterfaceDescriptor();
        zzk v2 = null;
        if("android.os.IMessenger".equals(v0)) {
            this.a = new Messenger(arg4);
            this.b = v2;
            return;
        }

        if("com.google.android.gms.iid.IMessengerCompat".equals(v0)) {
            this.b = new zzk(arg4);
            this.a = ((Messenger)v2);
            return;
        }

        String v4 = "Invalid interface descriptor: ";
        v0 = String.valueOf(v0);
        v4 = v0.length() != 0 ? v4.concat(v0) : new String(v4);
        Log.w("MessengerIpcClient", v4);
        throw new RemoteException();
    }

    final void a(Message arg2) {
        if(this.a != null) {
            this.a.send(arg2);
            return;
        }

        if(this.b != null) {
            this.b.a(arg2);
            return;
        }

        throw new IllegalStateException("Both messengers are null");
    }
}

