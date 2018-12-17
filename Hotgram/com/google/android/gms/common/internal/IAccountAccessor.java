package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IAccountAccessor extends IInterface {
    public abstract class Stub extends zzb implements IAccountAccessor {
        public class Proxy extends zza implements IAccountAccessor {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.IAccountAccessor");
            }

            public Account getAccount() {
                Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
                Parcelable v1 = zzc.zza(v0, Account.CREATOR);
                v0.recycle();
                return ((Account)v1);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }

        public static IAccountAccessor asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            if((v0 instanceof IAccountAccessor)) {
                return ((IAccountAccessor)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            if(arg1 == 2) {
                Account v1 = this.getAccount();
                arg3.writeNoException();
                zzc.zzb(arg3, ((Parcelable)v1));
                return 1;
            }

            return 0;
        }
    }

    Account getAccount();
}

