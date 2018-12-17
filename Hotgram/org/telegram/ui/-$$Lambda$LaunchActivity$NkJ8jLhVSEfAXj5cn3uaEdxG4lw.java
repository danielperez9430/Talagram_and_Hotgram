package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.LocaleController$LocaleInfo;

public final class -$$Lambda$LaunchActivity$NkJ8jLhVSEfAXj5cn3uaEdxG4lw implements DialogInterface$OnClickListener {
    public -$$Lambda$LaunchActivity$NkJ8jLhVSEfAXj5cn3uaEdxG4lw(LaunchActivity arg1, LocaleInfo[] arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        LaunchActivity.lambda$showLanguageAlertInternal$43(this.f$0, this.f$1, arg3, arg4);
    }
}

