package org.telegram.messenger;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$ov_8cd_UQ7e-iG00crefa_YP8ps implements DialogInterface$OnClickListener {
    public -$$Lambda$MessagesController$ov_8cd_UQ7e-iG00crefa_YP8ps(MessagesController arg1, int arg2, BaseFragment arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        MessagesController.lambda$checkCanOpenChat$236(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

