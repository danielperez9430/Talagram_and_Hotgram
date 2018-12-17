package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$AlertsCreator$x-ufj3OuwmPOMHnDBgVBxPWImeQ implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$x-ufj3OuwmPOMHnDBgVBxPWImeQ(long arg1, Runnable arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        AlertsCreator.lambda$createColorSelectDialog$17(this.f$0, this.f$1, arg4, arg5);
    }
}

