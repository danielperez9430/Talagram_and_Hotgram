package com.google.android.gms.common.net;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISocketFactoryCreator extends IInterface {
    public abstract class Stub extends zzb implements ISocketFactoryCreator {
        public class Proxy extends zza implements ISocketFactoryCreator {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.net.ISocketFactoryCreator");
            }

            public IObjectWrapper newSocketFactory(IObjectWrapper arg2, IObjectWrapper arg3, IObjectWrapper arg4, boolean arg5) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                zzc.zza(v0, ((IInterface)arg3));
                zzc.zza(v0, ((IInterface)arg4));
                zzc.zza(v0, arg5);
                Parcel v2 = ((zza)this).transactAndReadException(1, v0);
                arg3 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v2.readStrongBinder());
                v2.recycle();
                return arg3;
            }

            public IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper arg2, IObjectWrapper arg3, IObjectWrapper arg4, String arg5) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                zzc.zza(v0, ((IInterface)arg3));
                zzc.zza(v0, ((IInterface)arg4));
                v0.writeString(arg5);
                Parcel v2 = ((zza)this).transactAndReadException(2, v0);
                arg3 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v2.readStrongBinder());
                v2.recycle();
                return arg3;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.net.ISocketFactoryCreator");
        }

        public static ISocketFactoryCreator asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.net.ISocketFactoryCreator");
            if((v0 instanceof ISocketFactoryCreator)) {
                return ((ISocketFactoryCreator)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg2, Parcel arg3, Parcel arg4, int arg5) {
            switch(arg2) {
                case 1: {
                    goto label_12;
                }
                case 2: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            IObjectWrapper v2 = this.newSocketFactoryWithCacheDir(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), arg3.readString());
            goto label_20;
        label_12:
            v2 = this.newSocketFactory(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), zzc.zza(arg3));
        label_20:
            arg4.writeNoException();
            zzc.zza(arg4, ((IInterface)v2));
            return 1;
        }
    }

    IObjectWrapper newSocketFactory(IObjectWrapper arg1, IObjectWrapper arg2, IObjectWrapper arg3, boolean arg4);

    IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper arg1, IObjectWrapper arg2, IObjectWrapper arg3, String arg4);
}

