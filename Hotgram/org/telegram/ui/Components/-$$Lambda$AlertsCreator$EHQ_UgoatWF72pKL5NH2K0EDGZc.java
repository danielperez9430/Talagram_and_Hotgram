package org.telegram.ui.Components;

import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$AlertsCreator$EHQ_UgoatWF72pKL5NH2K0EDGZc implements DialogInterface$OnClickListener {
    public -$$Lambda$AlertsCreator$EHQ_UgoatWF72pKL5NH2K0EDGZc(long arg1, int arg3, BaseFragment arg4, Context arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg3;
        this.f$2 = arg4;
        this.f$3 = arg5;
    }

    public final void onClick(DialogInterface arg8, int arg9) {
        AlertsCreator.lambda$createReportAlert$12(this.f$0, this.f$1, this.f$2, this.f$3, arg8, arg9);
    }
}

