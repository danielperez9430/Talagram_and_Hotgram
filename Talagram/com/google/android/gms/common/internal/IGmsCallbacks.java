package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IGmsCallbacks extends IInterface {
    public abstract class Stub extends zzb implements IGmsCallbacks {
        public class Proxy extends zza implements IGmsCallbacks {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.IGmsCallbacks");
            }

            public void onAccountValidationComplete(int arg2, Bundle arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeInt(arg2);
                zzc.zza(v0, ((Parcelable)arg3));
                ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
            }

            public void onPostInitComplete(int arg2, IBinder arg3, Bundle arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeInt(arg2);
                v0.writeStrongBinder(arg3);
                zzc.zza(v0, ((Parcelable)arg4));
                ((zza)this).transactAndReadExceptionReturnVoid(1, v0);
            }

            public void onPostInitCompleteWithConnectionInfo(int arg2, IBinder arg3, ConnectionInfo arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeInt(arg2);
                v0.writeStrongBinder(arg3);
                zzc.zza(v0, ((Parcelable)arg4));
                ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }

        public static IGmsCallbacks asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
            if((v0 instanceof IGmsCallbacks)) {
                return ((IGmsCallbacks)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg2, Parcel arg3, Parcel arg4, int arg5) {
            switch(arg2) {
                case 1: {
                    goto label_14;
                }
                case 2: {
                    goto label_9;
                }
                case 3: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            this.onPostInitCompleteWithConnectionInfo(arg3.readInt(), arg3.readStrongBinder(), zzc.zza(arg3, ConnectionInfo.CREATOR));
            goto label_19;
        label_9:
            this.onAccountValidationComplete(arg3.readInt(), zzc.zza(arg3, Bundle.CREATOR));
            goto label_19;
        label_14:
            this.onPostInitComplete(arg3.readInt(), arg3.readStrongBinder(), zzc.zza(arg3, Bundle.CREATOR));
        label_19:
            arg4.writeNoException();
            return 1;
        }
    }

    void onAccountValidationComplete(int arg1, Bundle arg2);

    void onPostInitComplete(int arg1, IBinder arg2, Bundle arg3);

    void onPostInitCompleteWithConnectionInfo(int arg1, IBinder arg2, ConnectionInfo arg3);
}

