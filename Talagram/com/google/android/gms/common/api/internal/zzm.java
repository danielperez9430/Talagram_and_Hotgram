package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzm implements Runnable {
    private final zzl zzev;

    zzm(zzk arg1, zzl arg2) {
        this.zzew = arg1;
        super();
        this.zzev = arg2;
    }

    public final void run() {
        if(!this.zzew.mStarted) {
            return;
        }

        ConnectionResult v0 = this.zzev.getConnectionResult();
        if(v0.hasResolution()) {
            this.zzew.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zza(this.zzew.getActivity(), v0.getResolution(), this.zzev.zzu(), false), 1);
            return;
        }

        if(this.zzew.zzdg.isUserResolvableError(v0.getErrorCode())) {
            this.zzew.zzdg.showErrorDialogFragment(this.zzew.getActivity(), this.zzew.mLifecycleFragment, v0.getErrorCode(), 2, this.zzew);
            return;
        }

        if(v0.getErrorCode() == 18) {
            this.zzew.zzdg.registerCallbackOnUpdate(this.zzew.getActivity().getApplicationContext(), new zzn(this, this.zzew.zzdg.showUpdatingDialog(this.zzew.getActivity(), this.zzew)));
            return;
        }

        this.zzew.zza(v0, this.zzev.zzu());
    }
}

