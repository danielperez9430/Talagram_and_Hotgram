package org.telegram.ui.ActionBar;

import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;

public final class -$$Lambda$BaseFragment$vXTvtAK8XZpLjv4Env96FSJndOM implements DialogInterface$OnDismissListener {
    public -$$Lambda$BaseFragment$vXTvtAK8XZpLjv4Env96FSJndOM(BaseFragment arg1, DialogInterface$OnDismissListener arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onDismiss(DialogInterface arg3) {
        BaseFragment.lambda$showDialog$0(this.f$0, this.f$1, arg3);
    }
}

