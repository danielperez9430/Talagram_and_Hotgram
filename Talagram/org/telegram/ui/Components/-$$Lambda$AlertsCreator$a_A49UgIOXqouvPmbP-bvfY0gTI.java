package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$AlertsCreator$a_A49UgIOXqouvPmbP-bvfY0gTI implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$a_A49UgIOXqouvPmbP-bvfY0gTI(boolean arg1, boolean arg2, long arg3, Runnable arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg5;
    }

    public final void onClick(DialogInterface arg8, int arg9) {
        AlertsCreator.lambda$createColorSelectDialog$16(this.f$0, this.f$1, this.f$2, this.f$3, arg8, arg9);
    }
}

