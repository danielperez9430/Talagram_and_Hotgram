package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import java.util.HashMap;

public final class -$$Lambda$LaunchActivity$BhIGZaXPJBqMgZ4UJCuTTWylnic implements DialogInterface$OnClickListener {
    public -$$Lambda$LaunchActivity$BhIGZaXPJBqMgZ4UJCuTTWylnic(int arg1, HashMap arg2, boolean arg3, boolean arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void onClick(DialogInterface arg7, int arg8) {
        LaunchActivity.lambda$didReceivedNotification$37(this.f$0, this.f$1, this.f$2, this.f$3, arg7, arg8);
    }
}

