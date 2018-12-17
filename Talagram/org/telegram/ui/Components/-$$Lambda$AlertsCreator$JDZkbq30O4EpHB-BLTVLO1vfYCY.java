package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.MessagesStorage$IntCallback;

public final class -$$Lambda$AlertsCreator$JDZkbq30O4EpHB-BLTVLO1vfYCY implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$JDZkbq30O4EpHB-BLTVLO1vfYCY(IntCallback arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void onClick(DialogInterface arg2, int arg3) {
        AlertsCreator.lambda$createContactsPermissionDialog$21(this.f$0, arg2, arg3);
    }
}

