package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.view.View$OnClickListener;
import android.view.View;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$AlertsCreator$rro_SvqoV1WyQ8bHeaGHjwbe79o implements View$OnClickListener {
    public -$$Lambda$AlertsCreator$rro_SvqoV1WyQ8bHeaGHjwbe79o(BaseFragment arg1, DialogInterface$OnClickListener arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(View arg3) {
        AlertsCreator.lambda$createSingleChoiceDialog$28(this.f$0, this.f$1, arg3);
    }
}

