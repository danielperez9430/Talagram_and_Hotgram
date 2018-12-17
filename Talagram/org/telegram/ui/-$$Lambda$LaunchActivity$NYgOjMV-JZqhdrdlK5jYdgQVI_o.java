package org.telegram.ui;

import android.view.View$OnClickListener;
import android.view.View;
import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.ui.Cells.LanguageCell;

public final class -$$Lambda$LaunchActivity$NYgOjMV-JZqhdrdlK5jYdgQVI_o implements View$OnClickListener {
    public -$$Lambda$LaunchActivity$NYgOjMV-JZqhdrdlK5jYdgQVI_o(LocaleInfo[] arg1, LanguageCell[] arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(View arg3) {
        LaunchActivity.lambda$showLanguageAlertInternal$41(this.f$0, this.f$1, arg3);
    }
}

