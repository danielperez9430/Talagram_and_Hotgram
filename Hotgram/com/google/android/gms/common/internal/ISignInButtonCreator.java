package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISignInButtonCreator extends IInterface {
    public abstract class Stub extends zzb implements ISignInButtonCreator {
        public class Proxy extends zza implements ISignInButtonCreator {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.ISignInButtonCreator");
            }

            public IObjectWrapper newSignInButton(IObjectWrapper arg2, int arg3, int arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                v0.writeInt(arg3);
                v0.writeInt(arg4);
                Parcel v2 = ((zza)this).transactAndReadException(1, v0);
                IObjectWrapper v3 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v2.readStrongBinder());
                v2.recycle();
                return v3;
            }

            public IObjectWrapper newSignInButtonFromConfig(IObjectWrapper arg2, SignInButtonConfig arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                zzc.zza(v0, ((Parcelable)arg3));
                Parcel v2 = ((zza)this).transactAndReadException(2, v0);
                IObjectWrapper v3 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v2.readStrongBinder());
                v2.recycle();
                return v3;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.ISignInButtonCreator");
        }

        public static ISignInButtonCreator asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
            if((v0 instanceof ISignInButtonCreator)) {
                return ((ISignInButtonCreator)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 1: {
                    goto label_9;
                }
                case 2: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            IObjectWrapper v1 = this.newSignInButtonFromConfig(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()), zzc.zza(arg2, SignInButtonConfig.CREATOR));
            goto label_14;
        label_9:
            v1 = this.newSignInButton(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()), arg2.readInt(), arg2.readInt());
        label_14:
            arg3.writeNoException();
            zzc.zza(arg3, ((IInterface)v1));
            return 1;
        }
    }

    IObjectWrapper newSignInButton(IObjectWrapper arg1, int arg2, int arg3);

    IObjectWrapper newSignInButtonFromConfig(IObjectWrapper arg1, SignInButtonConfig arg2);
}

