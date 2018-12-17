package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$BotInlineResult;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_game;
import org.telegram.tgnet.TLRPC$TL_photo;

public final class -$$Lambda$SendMessagesHelper$fIGKzyw2eqJXTv-lIBLnX76sGYc implements Runnable {
    public -$$Lambda$SendMessagesHelper$fIGKzyw2eqJXTv-lIBLnX76sGYc(TL_document arg1, int arg2, String arg3, long arg4, MessageObject arg6, BotInlineResult arg7, HashMap arg8, TL_photo arg9, TL_game arg10) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg6;
        this.f$5 = arg7;
        this.f$6 = arg8;
        this.f$7 = arg9;
        this.f$8 = arg10;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$48(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}

