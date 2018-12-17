package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

public class AccountAccessor extends Stub {
    private Context mContext;
    private int zzqu;
    private Account zzs;

    public AccountAccessor(Context arg2, Account arg3) {
        super();
        this.zzqu = -1;
        this.mContext = arg2.getApplicationContext();
        this.zzs = arg3;
    }

    public boolean equals(Object arg2) {
        if(this == (((AccountAccessor)arg2))) {
            return 1;
        }

        if(!(arg2 instanceof AccountAccessor)) {
            return 0;
        }

        return this.zzs.equals(((AccountAccessor)arg2).zzs);
    }

    public static AccountAccessor fromGoogleAccountName(Context arg2, String arg3) {
        Account v3 = TextUtils.isEmpty(((CharSequence)arg3)) ? null : new Account(arg3, "com.google");
        return new AccountAccessor(arg2, v3);
    }

    public Account getAccount() {
        int v0 = Binder.getCallingUid();
        if(v0 == this.zzqu) {
            return this.zzs;
        }

        if(GooglePlayServicesUtilLight.isGooglePlayServicesUid(this.mContext, v0)) {
            this.zzqu = v0;
            return this.zzs;
        }

        throw new SecurityException("Caller is not GooglePlayServices");
    }

    public static Account getAccountBinderSafe(IAccountAccessor arg3) {
        Account v3_1;
        if(arg3 != null) {
            long v0 = Binder.clearCallingIdentity();
            try {
                v3_1 = arg3.getAccount();
            }
            catch(Throwable v3) {
            label_12:
                Binder.restoreCallingIdentity(v0);
                throw v3;
            }
            catch(RemoteException ) {
                try {
                    Log.w("AccountAccessor", "Remote account accessor probably died");
                }
                catch(Throwable v3) {
                    goto label_12;
                }

                Binder.restoreCallingIdentity(v0);
                goto label_14;
            }

            Binder.restoreCallingIdentity(v0);
        }
        else {
        label_14:
            v3_1 = null;
        }

        return v3_1;
    }
}

