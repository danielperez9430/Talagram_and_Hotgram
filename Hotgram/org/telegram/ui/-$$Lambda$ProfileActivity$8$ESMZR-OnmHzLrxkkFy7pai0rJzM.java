package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$ProfileActivity$8$ESMZR-OnmHzLrxkkFy7pai0rJzM implements DialogInterface$OnClickListener {
    public -$$Lambda$ProfileActivity$8$ESMZR-OnmHzLrxkkFy7pai0rJzM(org.telegram.ui.ProfileActivity$8 arg1, User arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        org.telegram.ui.ProfileActivity$8.lambda$onItemClick$1(this.f$0, this.f$1, arg3, arg4);
    }
}

