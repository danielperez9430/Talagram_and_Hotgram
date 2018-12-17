package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$AlertsCreator$dTydlE5-bK3tgSspcU2cHxFstKA implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$dTydlE5-bK3tgSspcU2cHxFstKA(EncryptedChat arg1, NumberPicker arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        AlertsCreator.lambda$createTTLAlert$30(this.f$0, this.f$1, arg3, arg4);
    }
}

