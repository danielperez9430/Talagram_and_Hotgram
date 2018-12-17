package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$messages_SentEncryptedMessage;

public final class -$$Lambda$SecretChatHelper$sd1MIyL1Cjxs8HzbKRfnl2G-KnY implements Runnable {
    public -$$Lambda$SecretChatHelper$sd1MIyL1Cjxs8HzbKRfnl2G-KnY(SecretChatHelper arg1, Message arg2, messages_SentEncryptedMessage arg3, String arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        SecretChatHelper.lambda$null$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

