package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IDynamiteLoaderV2 extends IInterface {
    public abstract class Stub extends zzb implements IDynamiteLoaderV2 {
        public class Proxy extends zza implements IDynamiteLoaderV2 {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
            }

            public IObjectWrapper loadModule(IObjectWrapper arg2, String arg3, byte[] arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                v0.writeString(arg3);
                v0.writeByteArray(arg4);
                Parcel v2 = ((zza)this).transactAndReadException(1, v0);
                IObjectWrapper v3 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v2.readStrongBinder());
                v2.recycle();
                return v3;
            }

            public IObjectWrapper loadModule2(IObjectWrapper arg2, String arg3, int arg4, IObjectWrapper arg5) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                v0.writeString(arg3);
                v0.writeInt(arg4);
                zzc.zza(v0, ((IInterface)arg5));
                Parcel v2 = ((zza)this).transactAndReadException(2, v0);
                IObjectWrapper v3 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v2.readStrongBinder());
                v2.recycle();
                return v3;
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamite.IDynamiteLoaderV2");
        }

        public static IDynamiteLoaderV2 asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
            if((v0 instanceof IDynamiteLoaderV2)) {
                return ((IDynamiteLoaderV2)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg2, Parcel arg3, Parcel arg4, int arg5) {
            switch(arg2) {
                case 1: {
                    goto label_11;
                }
                case 2: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            IObjectWrapper v2 = this.loadModule2(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), arg3.readString(), arg3.readInt(), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()));
            goto label_16;
        label_11:
            v2 = this.loadModule(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg3.readStrongBinder()), arg3.readString(), arg3.createByteArray());
        label_16:
            arg4.writeNoException();
            zzc.zza(arg4, ((IInterface)v2));
            return 1;
        }
    }

    IObjectWrapper loadModule(IObjectWrapper arg1, String arg2, byte[] arg3);

    IObjectWrapper loadModule2(IObjectWrapper arg1, String arg2, int arg3, IObjectWrapper arg4);
}

