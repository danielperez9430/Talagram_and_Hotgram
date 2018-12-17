package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$BotInlineResult;

public final class -$$Lambda$SendMessagesHelper$tnAO7DAKS1rJO7OGQCHMUmpYo8w implements Runnable {
    public -$$Lambda$SendMessagesHelper$tnAO7DAKS1rJO7OGQCHMUmpYo8w(BotInlineResult arg1, long arg2, int arg4, HashMap arg5, MessageObject arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
        this.f$4 = arg6;
    }

    public final void run() {
        SendMessagesHelper.lambda$prepareSendingBotContextResult$49(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

