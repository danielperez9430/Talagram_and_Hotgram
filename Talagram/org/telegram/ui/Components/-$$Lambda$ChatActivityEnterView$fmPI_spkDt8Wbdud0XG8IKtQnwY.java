package org.telegram.ui.Components;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC$KeyboardButton;

public final class -$$Lambda$ChatActivityEnterView$fmPI_spkDt8Wbdud0XG8IKtQnwY implements DialogInterface$OnClickListener {
    public -$$Lambda$ChatActivityEnterView$fmPI_spkDt8Wbdud0XG8IKtQnwY(ChatActivityEnterView arg1, MessageObject arg2, KeyboardButton arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        ChatActivityEnterView.lambda$didPressedBotButton$16(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

