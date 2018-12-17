package com.google.android.gms.internal.identity;

import android.app.Activity;
import android.app.PendingIntent$CanceledException;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

public final class zzf extends zzh {
    private Activity mActivity;
    private final int zzj;

    public zzf(int arg1, Activity arg2) {
        super();
        this.zzj = arg1;
        this.mActivity = arg2;
    }

    private final void setActivity(Activity arg1) {
        this.mActivity = null;
    }

    static void zza(zzf arg0, Activity arg1) {
        arg0.setActivity(null);
    }

    public final void zza(int arg4, Bundle arg5) {
        String v0_1;
        String v5;
        PendingIntent v4_1;
        int v0 = 1073741824;
        if(arg4 == 1) {
            Intent v4 = new Intent();
            v4.putExtras(arg5);
            v4_1 = this.mActivity.createPendingResult(this.zzj, v4, v0);
            if(v4_1 == null) {
                return;
            }

            try {
                v4_1.send(1);
                return;
            }
            catch(PendingIntent$CanceledException v4_2) {
                v5 = "AddressClientImpl";
                v0_1 = "Exception settng pending result";
                goto label_16;
            }
        }

        PendingIntent v2 = null;
        if(arg5 != null) {
            Parcelable v2_1 = arg5.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
        }

        ConnectionResult v5_1 = new ConnectionResult(arg4, v2);
        if(!v5_1.hasResolution()) {
            goto label_35;
        }

        try {
            v5_1.startResolutionForResult(this.mActivity, this.zzj);
            return;
        }
        catch(IntentSender$SendIntentException v4_3) {
            v5 = "AddressClientImpl";
            v0_1 = "Exception starting pending intent";
            goto label_16;
        }

        try {
        label_35:
            v4_1 = this.mActivity.createPendingResult(this.zzj, new Intent(), v0);
            if(v4_1 != null) {
                v4_1.send(1);
            }

            return;
        }
        catch(PendingIntent$CanceledException v4_2) {
            v5 = "AddressClientImpl";
            v0_1 = "Exception setting pending result";
        }

    label_16:
        Log.w(v5, v0_1, ((Throwable)v4_3));
    }
}

