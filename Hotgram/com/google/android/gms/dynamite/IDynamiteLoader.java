package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IDynamiteLoader extends IInterface {
    public abstract class Stub extends zzb implements IDynamiteLoader {
        public class Proxy extends zza implements IDynamiteLoader {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.dynamite.IDynamiteLoader");
            }

            public IObjectWrapper createModuleContext(IObjectWrapper arg2, String arg3, int arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                v0.writeString(arg3);
                v0.writeInt(arg4);
                Parcel v2 = ((zza)this).transactAndReadException(2, v0);
                IObjectWrapper v3 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v2.readStrongBinder());
                v2.recycle();
                return v3;
            }

            public int getModuleVersion(IObjectWrapper arg2, String arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                v0.writeString(arg3);
                Parcel v2 = ((zza)this).transactAndReadException(1, v0);
                int v3 = v2.readInt();
                v2.recycle();
                return v3;
            }

            public int getModuleVersion2(IObjectWrapper arg2, String arg3, boolean arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                v0.writeString(arg3);
                zzc.zza(v0, arg4);
                Parcel v2 = ((zza)this).transactAndReadException(3, v0);
                int v3 = v2.readInt();
                v2.recycle();
                return v3;
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamite.IDynamiteLoader");
        }

        public static IDynamiteLoader asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
            if((v0 instanceof IDynamiteLoader)) {
                return ((IDynamiteLoader)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 1: {
                    goto label_17;
                }
                case 2: {
                    goto label_9;
                }
                case 3: {
                    goto label_3;
                }
            }

            return 0;
        label_17:
            arg1 = this.getModuleVersion(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()), arg2.readString());
            goto label_21;
        label_3:
            arg1 = this.getModuleVersion2(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()), arg2.readString(), zzc.zza(arg2));
        label_21:
            arg3.writeNoException();
            arg3.writeInt(arg1);
            return 1;
        label_9:
            IObjectWrapper v1 = this.createModuleContext(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()), arg2.readString(), arg2.readInt());
            arg3.writeNoException();
            zzc.zza(arg3, ((IInterface)v1));
            return 1;
        }
    }

    IObjectWrapper createModuleContext(IObjectWrapper arg1, String arg2, int arg3);

    int getModuleVersion(IObjectWrapper arg1, String arg2);

    int getModuleVersion2(IObjectWrapper arg1, String arg2, boolean arg3);
}

