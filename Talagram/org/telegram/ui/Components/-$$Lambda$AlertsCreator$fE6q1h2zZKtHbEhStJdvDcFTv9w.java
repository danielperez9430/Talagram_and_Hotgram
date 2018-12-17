package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.MessagesStorage$IntCallback;

public final class -$$Lambda$AlertsCreator$fE6q1h2zZKtHbEhStJdvDcFTv9w implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$fE6q1h2zZKtHbEhStJdvDcFTv9w(int[] arg1, IntCallback arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        AlertsCreator.lambda$createLocationUpdateDialog$20(this.f$0, this.f$1, arg3, arg4);
    }
}

