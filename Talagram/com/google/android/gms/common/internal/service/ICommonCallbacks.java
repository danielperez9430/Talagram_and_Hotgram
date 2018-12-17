package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface ICommonCallbacks extends IInterface {
    public abstract class Stub extends zzb implements ICommonCallbacks {
        public class Proxy extends zza implements ICommonCallbacks {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.service.ICommonCallbacks");
            }

            public void onDefaultAccountCleared(int arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeInt(arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(1, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.service.ICommonCallbacks");
        }

        public static ICommonCallbacks asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
            if((v0 instanceof ICommonCallbacks)) {
                return ((ICommonCallbacks)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            if(arg1 == 1) {
                this.onDefaultAccountCleared(arg2.readInt());
                arg3.writeNoException();
                return 1;
            }

            return 0;
        }
    }

    void onDefaultAccountCleared(int arg1);
}

