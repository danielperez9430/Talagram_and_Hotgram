package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$LoginActivity$ue6fWEokYAlkmz3p94N9QOm1_o4 implements DialogInterface$OnClickListener {
    public -$$Lambda$LoginActivity$ue6fWEokYAlkmz3p94N9QOm1_o4(LoginActivity arg1, boolean arg2, String arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        LoginActivity.lambda$needShowInvalidAlert$0(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

