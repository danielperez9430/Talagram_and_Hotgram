package com.google.android.gms.internal.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentDataRequest;

public final class zzaf extends GmsClient {
    private final int environment;
    private final int theme;
    private final String zzcj;
    private final boolean zzet;
    private final Context zzgj;

    public zzaf(Context arg8, Looper arg9, ClientSettings arg10, ConnectionCallbacks arg11, OnConnectionFailedListener arg12, int arg13, int arg14, boolean arg15) {
        super(arg8, arg9, 4, arg10, arg11, arg12);
        this.zzgj = arg8;
        this.environment = arg13;
        this.zzcj = arg10.getAccountName();
        this.theme = arg14;
        this.zzet = arg15;
    }

    protected final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        if((v0 instanceof zzs)) {
            return v0;
        }

        return new zzt(arg3);
    }

    public final int getMinApkVersion() {
        return 12600000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    public final boolean requiresAccount() {
        return 1;
    }

    public final void zza(IsReadyToPayRequest arg3, TaskCompletionSource arg4) {
        zzai v0 = new zzai(arg4);
        try {
            this.getService().zza(arg3, this.zzd(), ((zzw)v0));
            return;
        }
        catch(RemoteException v3) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", ((Throwable)v3));
            ((zzah)v0).zza(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
            return;
        }
    }

    public final void zza(PaymentDataRequest arg4, TaskCompletionSource arg5) {
        Bundle v0 = this.zzd();
        v0.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzal v1 = new zzal(arg5);
        try {
            this.getService().zza(arg4, v0, ((zzw)v1));
            return;
        }
        catch(RemoteException v4) {
            Log.e("WalletClientImpl", "RemoteException getting payment data", ((Throwable)v4));
            ((zzah)v1).zza(Status.RESULT_INTERNAL_ERROR, null, Bundle.EMPTY);
            return;
        }
    }

    public final void zza(CreateWalletObjectsRequest arg4, TaskCompletionSource arg5) {
        Bundle v0 = this.zzd();
        v0.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzaj v1 = new zzaj(arg5);
        try {
            this.getService().zza(arg4, v0, ((zzw)v1));
            return;
        }
        catch(RemoteException v4) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", ((Throwable)v4));
            ((zzah)v1).zza(8, Bundle.EMPTY);
            return;
        }
    }

    public final void zza(CreateWalletObjectsRequest arg3, int arg4) {
        zzag v0 = new zzag(this.zzgj, arg4);
        Bundle v4 = this.zzd();
        try {
            this.getService().zza(arg3, v4, ((zzw)v0));
            return;
        }
        catch(RemoteException v3) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", ((Throwable)v3));
            ((zzah)v0).zza(8, Bundle.EMPTY);
            return;
        }
    }

    public final void zza(FullWalletRequest arg3, int arg4) {
        zzag v1 = new zzag(this.zzgj, arg4);
        Bundle v4 = this.zzd();
        try {
            this.getService().zza(arg3, v4, ((zzw)v1));
            return;
        }
        catch(RemoteException v3) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", ((Throwable)v3));
            ((zzah)v1).zza(8, null, Bundle.EMPTY);
            return;
        }
    }

    public final void zza(IsReadyToPayRequest arg3, ResultHolder arg4) {
        zzak v0 = new zzak(arg4);
        Bundle v4 = this.zzd();
        try {
            this.getService().zza(arg3, v4, ((zzw)v0));
            return;
        }
        catch(RemoteException v3) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", ((Throwable)v3));
            ((zzah)v0).zza(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
            return;
        }
    }

    public final void zza(MaskedWalletRequest arg4, int arg5) {
        Context v0 = this.zzgj;
        Bundle v1 = this.zzd();
        zzag v2 = new zzag(((Activity)v0), arg5);
        try {
            this.getService().zza(arg4, v1, ((zzw)v2));
            return;
        }
        catch(RemoteException v4) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", ((Throwable)v4));
            ((zzah)v2).zza(8, null, Bundle.EMPTY);
            return;
        }
    }

    public final void zza(String arg4, String arg5, int arg6) {
        Context v0 = this.zzgj;
        Bundle v1 = this.zzd();
        zzag v2 = new zzag(((Activity)v0), arg6);
        try {
            this.getService().zza(arg4, arg5, v1, ((zzw)v2));
            return;
        }
        catch(RemoteException v4) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", ((Throwable)v4));
            ((zzah)v2).zza(8, null, Bundle.EMPTY);
            return;
        }
    }

    public final void zzb(int arg4) {
        Bundle v0 = this.zzd();
        zzag v1 = new zzag(this.zzgj, arg4);
        try {
            this.getService().zza(v0, ((zzw)v1));
            return;
        }
        catch(RemoteException v4) {
            Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", ((Throwable)v4));
            ((zzah)v1).zza(8, false, Bundle.EMPTY);
            return;
        }
    }

    private final Bundle zzd() {
        int v0 = this.environment;
        String v1 = this.zzgj.getPackageName();
        String v2 = this.zzcj;
        int v3 = this.theme;
        boolean v4 = this.zzet;
        Bundle v5 = new Bundle();
        v5.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", v0);
        v5.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", v4);
        v5.putString("androidPackageName", v1);
        if(!TextUtils.isEmpty(((CharSequence)v2))) {
            v5.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(v2, "com.google"));
        }

        v5.putInt("com.google.android.gms.wallet.EXTRA_THEME", v3);
        return v5;
    }
}

