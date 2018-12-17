package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$AlertsCreator$7oxBdBo-z60AOCHO0SuiR8gEjxs implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$7oxBdBo-z60AOCHO0SuiR8gEjxs(BaseFragment arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void onClick(DialogInterface arg2, int arg3) {
        AlertsCreator.lambda$showAddUserAlert$13(this.f$0, arg2, arg3);
    }
}

