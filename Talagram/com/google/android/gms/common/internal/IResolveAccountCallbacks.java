package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IResolveAccountCallbacks extends IInterface {
    public abstract class Stub extends zzb implements IResolveAccountCallbacks {
        public class Proxy extends zza implements IResolveAccountCallbacks {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.IResolveAccountCallbacks");
            }

            public void onAccountResolutionComplete(ResolveAccountResponse arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IResolveAccountCallbacks");
        }

        public static IResolveAccountCallbacks asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
            if((v0 instanceof IResolveAccountCallbacks)) {
                return ((IResolveAccountCallbacks)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            if(arg1 == 2) {
                this.onAccountResolutionComplete(zzc.zza(arg2, ResolveAccountResponse.CREATOR));
                arg3.writeNoException();
                return 1;
            }

            return 0;
        }
    }

    void onAccountResolutionComplete(ResolveAccountResponse arg1);
}

