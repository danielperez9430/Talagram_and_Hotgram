package com.google.android.gms.common.api.internal;

import android.app.Dialog;

final class zzn extends Callback {
    zzn(zzm arg1, Dialog arg2) {
        this.zzey = arg1;
        this.zzex = arg2;
        super();
    }

    public final void zzv() {
        this.zzey.zzew.zzt();
        if(this.zzex.isShowing()) {
            this.zzex.dismiss();
        }
    }
}

