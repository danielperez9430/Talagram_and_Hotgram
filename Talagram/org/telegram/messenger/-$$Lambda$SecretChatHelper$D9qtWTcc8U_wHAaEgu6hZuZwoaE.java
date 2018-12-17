package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_encryptedChatDiscarded;

public final class -$$Lambda$SecretChatHelper$D9qtWTcc8U_wHAaEgu6hZuZwoaE implements Runnable {
    public -$$Lambda$SecretChatHelper$D9qtWTcc8U_wHAaEgu6hZuZwoaE(SecretChatHelper arg1, TL_encryptedChatDiscarded arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SecretChatHelper.lambda$processAcceptedSecretChat$18(this.f$0, this.f$1);
    }
}

