package org.telegram.ui.Adapters;

import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;

public final class -$$Lambda$MentionsAdapter$MgskdrDXUdlxFwtdPn3PQRymvWA implements DialogInterface$OnDismissListener {
    public -$$Lambda$MentionsAdapter$MgskdrDXUdlxFwtdPn3PQRymvWA(MentionsAdapter arg1, boolean[] arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onDismiss(DialogInterface arg3) {
        MentionsAdapter.lambda$processFoundUser$2(this.f$0, this.f$1, arg3);
    }
}

