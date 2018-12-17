package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$AlertsCreator$CppK7qvWPqDtEeVHx43ZUlorspQ implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$CppK7qvWPqDtEeVHx43ZUlorspQ(boolean arg1, int[] arg2, boolean arg3, long arg4, Runnable arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg6;
    }

    public final void onClick(DialogInterface arg9, int arg10) {
        AlertsCreator.lambda$createColorSelectDialog$15(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg9, arg10);
    }
}

