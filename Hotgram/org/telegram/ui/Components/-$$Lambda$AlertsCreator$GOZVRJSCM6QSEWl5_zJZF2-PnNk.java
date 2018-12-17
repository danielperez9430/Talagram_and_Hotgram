package org.telegram.ui.Components;

import android.view.View$OnClickListener;
import android.view.View;
import org.telegram.messenger.MessagesStorage$IntCallback;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$AlertsCreator$GOZVRJSCM6QSEWl5_zJZF2-PnNk implements View$OnClickListener {
    public -$$Lambda$AlertsCreator$GOZVRJSCM6QSEWl5_zJZF2-PnNk(int arg1, long arg2, BaseFragment arg4, IntCallback arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
    }

    public final void onClick(View arg7) {
        AlertsCreator.lambda$showCustomNotificationsDialog$1(this.f$0, this.f$1, this.f$2, this.f$3, arg7);
    }
}

