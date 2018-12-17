package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.SharedPreferences;

public final class -$$Lambda$PrivacyControlActivity$1$OX8cNENyFoXHm_36bfshXvigfAQ implements DialogInterface$OnClickListener {
    public -$$Lambda$PrivacyControlActivity$1$OX8cNENyFoXHm_36bfshXvigfAQ(org.telegram.ui.PrivacyControlActivity$1 arg1, SharedPreferences arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        org.telegram.ui.PrivacyControlActivity$1.lambda$onItemClick$0(this.f$0, this.f$1, arg3, arg4);
    }
}

