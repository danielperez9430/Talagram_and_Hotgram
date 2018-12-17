package com.google.android.gms.internal.identity;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.identity.intents.UserAddressRequest;

public final class zze extends GmsClient {
    private Activity mActivity;
    private final int mTheme;
    private zzf zzh;
    private final String zzi;

    public zze(Activity arg8, Looper arg9, ClientSettings arg10, int arg11, ConnectionCallbacks arg12, OnConnectionFailedListener arg13) {
        super(arg8, arg9, 12, arg10, arg12, arg13);
        this.zzi = arg10.getAccountName();
        this.mActivity = arg8;
        this.mTheme = arg11;
    }

    protected final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService");
        if((v0 instanceof zzi)) {
            return v0;
        }

        return new zzj(arg3);
    }

    public final void disconnect() {
        super.disconnect();
        if(this.zzh != null) {
            zzf.zza(this.zzh, null);
            this.zzh = null;
        }
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.identity.intents.internal.IAddressService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.identity.service.BIND";
    }

    public final boolean requiresAccount() {
        return 1;
    }

    public final void zza(UserAddressRequest arg5, int arg6) {
        super.checkConnected();
        this.zzh = new zzf(arg6, this.mActivity);
        try {
            Bundle v6 = new Bundle();
            v6.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", this.getContext().getPackageName());
            if(!TextUtils.isEmpty(this.zzi)) {
                v6.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", new Account(this.zzi, "com.google"));
            }

            v6.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
            super.getService().zza(this.zzh, arg5, v6);
            return;
        }
        catch(RemoteException v5) {
            Log.e("AddressClientImpl", "Exception requesting user address", ((Throwable)v5));
            Bundle v5_1 = new Bundle();
            v5_1.putInt("com.google.android.gms.identity.intents.EXTRA_ERROR_CODE", 555);
            this.zzh.zza(1, v5_1);
            return;
        }
    }
}

