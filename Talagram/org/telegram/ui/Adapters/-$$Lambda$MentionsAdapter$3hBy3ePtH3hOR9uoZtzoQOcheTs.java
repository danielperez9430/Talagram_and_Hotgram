package org.telegram.ui.Adapters;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$MentionsAdapter$3hBy3ePtH3hOR9uoZtzoQOcheTs implements DialogInterface$OnClickListener {
    public -$$Lambda$MentionsAdapter$3hBy3ePtH3hOR9uoZtzoQOcheTs(MentionsAdapter arg1, boolean[] arg2, User arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        MentionsAdapter.lambda$processFoundUser$0(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

