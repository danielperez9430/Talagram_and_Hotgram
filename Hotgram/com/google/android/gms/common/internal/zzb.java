package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

final class zzb extends DialogRedirect {
    zzb(Intent arg1, Activity arg2, int arg3) {
        this.zzsh = arg1;
        this.val$activity = arg2;
        this.val$requestCode = arg3;
        super();
    }

    public final void redirect() {
        if(this.zzsh != null) {
            this.val$activity.startActivityForResult(this.zzsh, this.val$requestCode);
        }
    }
}

