package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$SecretChatHelper$tDKre2aQQBiVO0S8VAHIlXCNFCM implements Runnable {
    public -$$Lambda$SecretChatHelper$tDKre2aQQBiVO0S8VAHIlXCNFCM(SecretChatHelper arg1, EncryptedChat arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SecretChatHelper.lambda$processAcceptedSecretChat$17(this.f$0, this.f$1);
    }
}

