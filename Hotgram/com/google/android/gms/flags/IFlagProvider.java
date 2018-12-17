package com.google.android.gms.flags;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IFlagProvider extends IInterface {
    public abstract class Stub extends zzb implements IFlagProvider {
        public class Proxy extends zza implements IFlagProvider {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.flags.IFlagProvider");
            }

            public boolean getBooleanFlagValue(String arg2, boolean arg3, int arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                zzc.zza(v0, arg3);
                v0.writeInt(arg4);
                Parcel v2 = ((zza)this).transactAndReadException(2, v0);
                arg3 = zzc.zza(v2);
                v2.recycle();
                return arg3;
            }

            public int getIntFlagValue(String arg2, int arg3, int arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                v0.writeInt(arg3);
                v0.writeInt(arg4);
                Parcel v2 = ((zza)this).transactAndReadException(3, v0);
                arg3 = v2.readInt();
                v2.recycle();
                return arg3;
            }

            public long getLongFlagValue(String arg2, long arg3, int arg5) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                v0.writeLong(arg3);
                v0.writeInt(arg5);
                Parcel v2 = ((zza)this).transactAndReadException(4, v0);
                arg3 = v2.readLong();
                v2.recycle();
                return arg3;
            }

            public String getStringFlagValue(String arg2, String arg3, int arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                v0.writeString(arg3);
                v0.writeInt(arg4);
                Parcel v2 = ((zza)this).transactAndReadException(5, v0);
                arg3 = v2.readString();
                v2.recycle();
                return arg3;
            }

            public void init(IObjectWrapper arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(1, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.flags.IFlagProvider");
        }

        public static IFlagProvider asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.flags.IFlagProvider");
            if((v0 instanceof IFlagProvider)) {
                return ((IFlagProvider)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg3, Parcel arg4, Parcel arg5, int arg6) {
            switch(arg3) {
                case 1: {
                    goto label_31;
                }
                case 2: {
                    goto label_24;
                }
                case 3: {
                    goto label_17;
                }
                case 4: {
                    goto label_10;
                }
                case 5: {
                    goto label_3;
                }
            }

            return 0;
        label_17:
            arg3 = this.getIntFlagValue(arg4.readString(), arg4.readInt(), arg4.readInt());
            arg5.writeNoException();
            arg5.writeInt(arg3);
            return 1;
        label_3:
            String v3 = this.getStringFlagValue(arg4.readString(), arg4.readString(), arg4.readInt());
            arg5.writeNoException();
            arg5.writeString(v3);
            return 1;
        label_24:
            boolean v3_1 = this.getBooleanFlagValue(arg4.readString(), zzc.zza(arg4), arg4.readInt());
            arg5.writeNoException();
            zzc.zza(arg5, v3_1);
            return 1;
        label_10:
            long v3_2 = this.getLongFlagValue(arg4.readString(), arg4.readLong(), arg4.readInt());
            arg5.writeNoException();
            arg5.writeLong(v3_2);
            return 1;
        label_31:
            this.init(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg4.readStrongBinder()));
            arg5.writeNoException();
            return 1;
        }
    }

    boolean getBooleanFlagValue(String arg1, boolean arg2, int arg3);

    int getIntFlagValue(String arg1, int arg2, int arg3);

    long getLongFlagValue(String arg1, long arg2, int arg3);

    String getStringFlagValue(String arg1, String arg2, int arg3);

    void init(IObjectWrapper arg1);
}

