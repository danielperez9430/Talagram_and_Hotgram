package com.google.android.gms.common.internal;

import android.content.Intent;
import android.support.v4.app.Fragment;

final class zzc extends DialogRedirect {
    zzc(Intent arg1, Fragment arg2, int arg3) {
        this.zzsh = arg1;
        this.val$fragment = arg2;
        this.val$requestCode = arg3;
        super();
    }

    public final void redirect() {
        if(this.zzsh != null) {
            this.val$fragment.startActivityForResult(this.zzsh, this.val$requestCode);
        }
    }
}

