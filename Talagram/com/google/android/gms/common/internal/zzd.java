package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

final class zzd extends DialogRedirect {
    zzd(Intent arg1, LifecycleFragment arg2, int arg3) {
        this.zzsh = arg1;
        this.zzsi = arg2;
        this.val$requestCode = arg3;
        super();
    }

    public final void redirect() {
        if(this.zzsh != null) {
            this.zzsi.startActivityForResult(this.zzsh, this.val$requestCode);
        }
    }
}

