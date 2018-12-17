package org.telegram.ui.Adapters;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$MentionsAdapter$CT1DE5mOMJY5cACE3GAb-uXrprc implements DialogInterface$OnClickListener {
    public -$$Lambda$MentionsAdapter$CT1DE5mOMJY5cACE3GAb-uXrprc(MentionsAdapter arg1, boolean[] arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        MentionsAdapter.lambda$processFoundUser$1(this.f$0, this.f$1, arg3, arg4);
    }
}

