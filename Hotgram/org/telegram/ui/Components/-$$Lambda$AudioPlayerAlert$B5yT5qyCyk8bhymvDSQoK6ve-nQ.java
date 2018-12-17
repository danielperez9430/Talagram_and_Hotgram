package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.MessageObject;

public final class -$$Lambda$AudioPlayerAlert$B5yT5qyCyk8bhymvDSQoK6ve-nQ implements DialogInterface$OnClickListener {
    public -$$Lambda$AudioPlayerAlert$B5yT5qyCyk8bhymvDSQoK6ve-nQ(AudioPlayerAlert arg1, MessageObject arg2, boolean[] arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        AudioPlayerAlert.lambda$onSubItemClick$12(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

