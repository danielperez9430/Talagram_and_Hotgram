package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.ui.Components.NumberPicker;

public final class -$$Lambda$SettingsActivity$6$Bl7vsfVyjPNClXPN9E9w-UiFvv0 implements DialogInterface$OnClickListener {
    public -$$Lambda$SettingsActivity$6$Bl7vsfVyjPNClXPN9E9w-UiFvv0(org.telegram.ui.SettingsActivity$6 arg1, NumberPicker arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        org.telegram.ui.SettingsActivity$6.lambda$onItemClick$0(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

