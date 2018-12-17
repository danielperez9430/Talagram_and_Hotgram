package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

public class ErrorDialogFragment extends DialogFragment {
    private Dialog mDialog;
    private DialogInterface$OnCancelListener zzap;

    public ErrorDialogFragment() {
        super();
        this.mDialog = null;
        this.zzap = null;
    }

    public static ErrorDialogFragment newInstance(Dialog arg1) {
        return ErrorDialogFragment.newInstance(arg1, null);
    }

    public static ErrorDialogFragment newInstance(Dialog arg2, DialogInterface$OnCancelListener arg3) {
        ErrorDialogFragment v0 = new ErrorDialogFragment();
        Object v2 = Preconditions.checkNotNull(arg2, "Cannot display null dialog");
        ((Dialog)v2).setOnCancelListener(null);
        ((Dialog)v2).setOnDismissListener(null);
        v0.mDialog = ((Dialog)v2);
        if(arg3 != null) {
            v0.zzap = arg3;
        }

        return v0;
    }

    public void onCancel(DialogInterface arg2) {
        if(this.zzap != null) {
            this.zzap.onCancel(arg2);
        }
    }

    public Dialog onCreateDialog(Bundle arg1) {
        if(this.mDialog == null) {
            this.setShowsDialog(false);
        }

        return this.mDialog;
    }

    public void show(FragmentManager arg1, String arg2) {
        super.show(arg1, arg2);
    }
}

