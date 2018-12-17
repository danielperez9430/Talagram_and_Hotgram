package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ICertData extends IInterface {
    public abstract class Stub extends zzb implements ICertData {
        public class Proxy extends zza implements ICertData {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.ICertData");
            }

            public IObjectWrapper getBytesWrapped() {
                Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
                IObjectWrapper v1 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public int getHashCode() {
                Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
                int v1 = v0.readInt();
                v0.recycle();
                return v1;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.ICertData");
        }

        public static ICertData asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
            if((v0 instanceof ICertData)) {
                return ((ICertData)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 1: {
                    goto label_7;
                }
                case 2: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            arg1 = this.getHashCode();
            arg3.writeNoException();
            arg3.writeInt(arg1);
            return 1;
        label_7:
            IObjectWrapper v1 = this.getBytesWrapped();
            arg3.writeNoException();
            zzc.zza(arg3, ((IInterface)v1));
            return 1;
        }
    }

    IObjectWrapper getBytesWrapped();

    int getHashCode();
}

