package com.google.android.gms.internal.wallet;

import android.app.Activity;
import android.app.PendingIntent$CanceledException;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import java.lang.ref.WeakReference;

final class zzag extends zzah {
    private final int zzab;
    private final WeakReference zzgk;

    public zzag(Activity arg2, int arg3) {
        super();
        this.zzgk = new WeakReference(arg2);
        this.zzab = arg3;
    }

    public final void zza(int arg4, Bundle arg5) {
        Preconditions.checkNotNull(arg5, "Bundle should not be null");
        Object v0 = this.zzgk.get();
        if(v0 == null) {
            Log.d("WalletClientImpl", "Ignoring onWalletObjectsCreated, Activity has gone");
            return;
        }

        ConnectionResult v1 = new ConnectionResult(arg4, arg5.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
        if(v1.hasResolution()) {
            try {
                v1.startResolutionForResult(((Activity)v0), this.zzab);
                return;
            }
            catch(IntentSender$SendIntentException v4) {
                Log.w("WalletClientImpl", "Exception starting pending intent", ((Throwable)v4));
                return;
            }
        }

        String v5 = String.valueOf(v1);
        StringBuilder v2 = new StringBuilder(String.valueOf(v5).length() + 75);
        v2.append("Create Wallet Objects confirmation UI will not be shown connection result: ");
        v2.append(v5);
        Log.e("WalletClientImpl", v2.toString());
        Intent v4_1 = new Intent();
        v4_1.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
        PendingIntent v4_2 = ((Activity)v0).createPendingResult(this.zzab, v4_1, 1073741824);
        if(v4_2 == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
            return;
        }

        try {
            v4_2.send(1);
            return;
        }
        catch(PendingIntent$CanceledException v4_3) {
            Log.w("WalletClientImpl", "Exception setting pending result", ((Throwable)v4_3));
            return;
        }
    }

    public final void zza(int arg3, FullWallet arg4, Bundle arg5) {
        Parcelable v1_1;
        Object v0 = this.zzgk.get();
        if(v0 == null) {
            Log.d("WalletClientImpl", "Ignoring onFullWalletLoaded, Activity has gone");
            return;
        }

        PendingIntent v1 = null;
        if(arg5 != null) {
            v1_1 = arg5.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        }

        ConnectionResult v5 = new ConnectionResult(arg3, ((PendingIntent)v1_1));
        if(v5.hasResolution()) {
            try {
                v5.startResolutionForResult(((Activity)v0), this.zzab);
                return;
            }
            catch(IntentSender$SendIntentException v3) {
                Log.w("WalletClientImpl", "Exception starting pending intent", ((Throwable)v3));
                return;
            }
        }

        Intent v1_2 = new Intent();
        if(v5.isSuccess()) {
            arg3 = -1;
            v1_2.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", ((Parcelable)arg4));
        }
        else {
            int v4 = arg3 == 408 ? 0 : 1;
            v1_2.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", arg3);
            arg3 = v4;
        }

        PendingIntent v4_1 = ((Activity)v0).createPendingResult(this.zzab, v1_2, 1073741824);
        if(v4_1 == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
            return;
        }

        try {
            v4_1.send(arg3);
            return;
        }
        catch(PendingIntent$CanceledException v3_1) {
            Log.w("WalletClientImpl", "Exception setting pending result", ((Throwable)v3_1));
            return;
        }
    }

    public final void zza(int arg3, MaskedWallet arg4, Bundle arg5) {
        Parcelable v1_1;
        Object v0 = this.zzgk.get();
        if(v0 == null) {
            Log.d("WalletClientImpl", "Ignoring onMaskedWalletLoaded, Activity has gone");
            return;
        }

        PendingIntent v1 = null;
        if(arg5 != null) {
            v1_1 = arg5.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        }

        ConnectionResult v5 = new ConnectionResult(arg3, ((PendingIntent)v1_1));
        if(v5.hasResolution()) {
            try {
                v5.startResolutionForResult(((Activity)v0), this.zzab);
                return;
            }
            catch(IntentSender$SendIntentException v3) {
                Log.w("WalletClientImpl", "Exception starting pending intent", ((Throwable)v3));
                return;
            }
        }

        Intent v1_2 = new Intent();
        if(v5.isSuccess()) {
            arg3 = -1;
            v1_2.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", ((Parcelable)arg4));
        }
        else {
            int v4 = arg3 == 408 ? 0 : 1;
            v1_2.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", arg3);
            arg3 = v4;
        }

        PendingIntent v4_1 = ((Activity)v0).createPendingResult(this.zzab, v1_2, 1073741824);
        if(v4_1 == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
            return;
        }

        try {
            v4_1.send(arg3);
            return;
        }
        catch(PendingIntent$CanceledException v3_1) {
            Log.w("WalletClientImpl", "Exception setting pending result", ((Throwable)v3_1));
            return;
        }
    }

    public final void zza(int arg2, boolean arg3, Bundle arg4) {
        Object v2 = this.zzgk.get();
        if(v2 == null) {
            Log.d("WalletClientImpl", "Ignoring onPreAuthorizationDetermined, Activity has gone");
            return;
        }

        Intent v4 = new Intent();
        v4.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", arg3);
        PendingIntent v2_1 = ((Activity)v2).createPendingResult(this.zzab, v4, 1073741824);
        if(v2_1 == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
            return;
        }

        int v3 = -1;
        try {
            v2_1.send(v3);
            return;
        }
        catch(PendingIntent$CanceledException v2_2) {
            Log.w("WalletClientImpl", "Exception setting pending result", ((Throwable)v2_2));
            return;
        }
    }
}

