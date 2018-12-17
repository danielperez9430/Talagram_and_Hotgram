package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_encryptedChatDiscarded;

public final class -$$Lambda$SecretChatHelper$y_-QKcAzTtKUDu-WddTe8KbVDxY implements Runnable {
    public -$$Lambda$SecretChatHelper$y_-QKcAzTtKUDu-WddTe8KbVDxY(SecretChatHelper arg1, TL_encryptedChatDiscarded arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SecretChatHelper.lambda$decryptMessage$16(this.f$0, this.f$1);
    }
}

