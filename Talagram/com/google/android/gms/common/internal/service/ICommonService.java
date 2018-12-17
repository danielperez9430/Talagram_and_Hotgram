package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ICommonService extends IInterface {
    public abstract class Stub extends zzb implements ICommonService {
        public class Proxy extends zza implements ICommonService {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.service.ICommonService");
            }

            public void clearDefaultAccount(ICommonCallbacks arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                ((zza)this).transactOneway(1, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.service.ICommonService");
        }

        public static ICommonService asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService");
            if((v0 instanceof ICommonService)) {
                return ((ICommonService)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            if(arg1 == 1) {
                this.clearDefaultAccount(com.google.android.gms.common.internal.service.ICommonCallbacks$Stub.asInterface(arg2.readStrongBinder()));
                return 1;
            }

            return 0;
        }
    }

    void clearDefaultAccount(ICommonCallbacks arg1);
}

