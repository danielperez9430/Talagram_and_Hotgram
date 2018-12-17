package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$AlertsCreator$lpO1-8CQqnFnUzHMuq860437l60 implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$lpO1-8CQqnFnUzHMuq860437l60(Runnable arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void onClick(DialogInterface arg2, int arg3) {
        AlertsCreator.lambda$showSecretLocationAlert$2(this.f$0, arg2, arg3);
    }
}

