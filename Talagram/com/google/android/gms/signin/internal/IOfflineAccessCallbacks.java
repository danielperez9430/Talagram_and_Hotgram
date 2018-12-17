package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;
import java.util.List;

public interface IOfflineAccessCallbacks extends IInterface {
    public abstract class Stub extends zzb implements IOfflineAccessCallbacks {
        public class Proxy extends zza implements IOfflineAccessCallbacks {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
            }

            public void checkServerAuthorization(String arg2, List arg3, ISignInService arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                v0.writeTypedList(arg3);
                zzc.zza(v0, ((IInterface)arg4));
                ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
            }

            public void uploadServerAuthCode(String arg2, String arg3, ISignInService arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                v0.writeString(arg3);
                zzc.zza(v0, ((IInterface)arg4));
                ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
        }

        public static IOfflineAccessCallbacks asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
            if((v0 instanceof IOfflineAccessCallbacks)) {
                return ((IOfflineAccessCallbacks)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 2: {
                    goto label_9;
                }
                case 3: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            this.uploadServerAuthCode(arg2.readString(), arg2.readString(), com.google.android.gms.signin.internal.ISignInService$Stub.asInterface(arg2.readStrongBinder()));
            goto label_15;
        label_9:
            this.checkServerAuthorization(arg2.readString(), arg2.createTypedArrayList(Scope.CREATOR), com.google.android.gms.signin.internal.ISignInService$Stub.asInterface(arg2.readStrongBinder()));
        label_15:
            arg3.writeNoException();
            return 1;
        }
    }

    void checkServerAuthorization(String arg1, List arg2, ISignInService arg3);

    void uploadServerAuthCode(String arg1, String arg2, ISignInService arg3);
}

