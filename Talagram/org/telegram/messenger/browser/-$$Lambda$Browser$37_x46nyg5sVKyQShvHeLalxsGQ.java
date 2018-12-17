package org.telegram.messenger.browser;

import android.content.Context;
import android.net.Uri;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$Browser$37_x46nyg5sVKyQShvHeLalxsGQ implements Runnable {
    public -$$Lambda$Browser$37_x46nyg5sVKyQShvHeLalxsGQ(AlertDialog[] arg1, TLObject arg2, int arg3, Uri arg4, Context arg5, boolean arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        Browser.lambda$null$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

