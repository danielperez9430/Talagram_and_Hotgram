package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import java.util.HashMap;

public final class -$$Lambda$LaunchActivity$6Qg6eLr-hiBMoDGQdJTSC_w7HGU implements DialogInterface$OnClickListener {
    public -$$Lambda$LaunchActivity$6Qg6eLr-hiBMoDGQdJTSC_w7HGU(LaunchActivity arg1, HashMap arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        LaunchActivity.lambda$didReceivedNotification$35(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

