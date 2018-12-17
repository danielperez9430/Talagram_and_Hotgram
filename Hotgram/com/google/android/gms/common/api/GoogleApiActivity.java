package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.VisibleForTesting;

public class GoogleApiActivity extends Activity implements DialogInterface$OnCancelListener {
    @VisibleForTesting private int zzct;

    public GoogleApiActivity() {
        super();
        this.zzct = 0;
    }

    protected void onActivityResult(int arg4, int arg5, Intent arg6) {
        super.onActivityResult(arg4, arg5, arg6);
        if(arg4 == 1) {
            boolean v4 = this.getIntent().getBooleanExtra("notify_manager", true);
            this.zzct = 0;
            this.setResult(arg5, arg6);
            if(v4) {
                GoogleApiManager v4_1 = GoogleApiManager.zzb(((Context)this));
                switch(arg5) {
                    case -1: {
                        goto label_23;
                    }
                    case 0: {
                        goto label_13;
                    }
                }

                goto label_29;
            label_23:
                v4_1.zzr();
                goto label_29;
            label_13:
                v4_1.zza(new ConnectionResult(13, null), this.getIntent().getIntExtra("failing_client_id", -1));
            }
        }
        else if(arg4 == 2) {
            this.zzct = 0;
            this.setResult(arg5, arg6);
        }

    label_29:
        this.finish();
    }

    public void onCancel(DialogInterface arg1) {
        this.zzct = 0;
        this.setResult(0);
        this.finish();
    }

    protected void onCreate(Bundle arg9) {
        Object v9_1;
        Object v1;
        String v0;
        String v9;
        super.onCreate(arg9);
        if(arg9 != null) {
            this.zzct = arg9.getInt("resolution");
        }

        if(this.zzct != 1) {
            arg9 = this.getIntent().getExtras();
            if(arg9 == null) {
                v9 = "GoogleApiActivity";
                v0 = "Activity started without extras";
            }
            else {
                v1 = arg9.get("pending_intent");
                v9_1 = arg9.get("error_code");
                if(v1 == null && v9_1 == null) {
                    v9 = "GoogleApiActivity";
                    v0 = "Activity started without resolution";
                    goto label_13;
                }

                goto label_25;
            }

        label_13:
            Log.e(v9, v0);
            goto label_14;
        label_25:
            if(v1 != null) {
                try {
                    this.startIntentSenderForResult(((PendingIntent)v1).getIntentSender(), 1, null, 0, 0, 0);
                    this.zzct = 1;
                    return;
                }
                catch(IntentSender$SendIntentException v9_2) {
                    Log.e("GoogleApiActivity", "Failed to launch pendingIntent", ((Throwable)v9_2));
                }

            label_14:
                this.finish();
                return;
            }

            GoogleApiAvailability.getInstance().showErrorDialogFragment(((Activity)this), ((Integer)v9_1).intValue(), 2, ((DialogInterface$OnCancelListener)this));
            this.zzct = 1;
        }
    }

    protected void onSaveInstanceState(Bundle arg3) {
        arg3.putInt("resolution", this.zzct);
        super.onSaveInstanceState(arg3);
    }

    public static PendingIntent zza(Context arg1, PendingIntent arg2, int arg3) {
        return PendingIntent.getActivity(arg1, 0, GoogleApiActivity.zza(arg1, arg2, arg3, true), 134217728);
    }

    public static Intent zza(Context arg2, PendingIntent arg3, int arg4, boolean arg5) {
        Intent v0 = new Intent(arg2, GoogleApiActivity.class);
        v0.putExtra("pending_intent", ((Parcelable)arg3));
        v0.putExtra("failing_client_id", arg4);
        v0.putExtra("notify_manager", arg5);
        return v0;
    }
}

