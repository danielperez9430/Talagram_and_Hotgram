package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$AlertsCreator$ekXUEjitbQ6cLCKK4eWI_cJwDyg implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$ekXUEjitbQ6cLCKK4eWI_cJwDyg(boolean arg1, NumberPicker arg2, NumberPicker arg3, NumberPicker arg4, DatePickerDelegate arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void onClick(DialogInterface arg8, int arg9) {
        AlertsCreator.lambda$createDatePickerDialog$9(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

