package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface ICancelToken extends IInterface {
    public abstract class Stub extends zzb implements ICancelToken {
        public class Proxy extends zza implements ICancelToken {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.ICancelToken");
            }

            public void cancel() {
                ((zza)this).transactOneway(2, ((zza)this).obtainAndWriteInterfaceToken());
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }

        public static ICancelToken asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            if((v0 instanceof ICancelToken)) {
                return ((ICancelToken)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            if(arg1 == 2) {
                this.cancel();
                return 1;
            }

            return 0;
        }
    }

    void cancel();
}

