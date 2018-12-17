package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IStatusCallback extends IInterface {
    public abstract class Stub extends zzb implements IStatusCallback {
        public final class zza extends com.google.android.gms.internal.stable.zza implements IStatusCallback {
            zza(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.api.internal.IStatusCallback");
            }

            public final void onResult(Status arg2) {
                Parcel v0 = ((com.google.android.gms.internal.stable.zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                ((com.google.android.gms.internal.stable.zza)this).transactOneway(1, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        public static IStatusCallback asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            if((v0 instanceof IStatusCallback)) {
                return ((IStatusCallback)v0);
            }

            return new zza(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            if(arg1 == 1) {
                this.onResult(zzc.zza(arg2, Status.CREATOR));
                return 1;
            }

            return 0;
        }
    }

    void onResult(Status arg1);
}

