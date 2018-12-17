package org.telegram.ui.Components;

import android.view.View$OnClickListener;
import android.view.View;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$AlertsCreator$bLmDqrwlye6kRN6M9YsVd__VSdM implements View$OnClickListener {
    public -$$Lambda$AlertsCreator$bLmDqrwlye6kRN6M9YsVd__VSdM(AlertDialog[] arg1, Runnable arg2, AccountSelectDelegate arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(View arg4) {
        AlertsCreator.lambda$createAccountSelectDialog$31(this.f$0, this.f$1, this.f$2, arg4);
    }
}

