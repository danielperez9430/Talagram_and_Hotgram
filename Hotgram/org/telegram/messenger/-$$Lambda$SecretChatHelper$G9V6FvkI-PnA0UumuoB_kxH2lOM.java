package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$SecretChatHelper$G9V6FvkI-PnA0UumuoB_kxH2lOM implements Runnable {
    public -$$Lambda$SecretChatHelper$G9V6FvkI-PnA0UumuoB_kxH2lOM(SecretChatHelper arg1, EncryptedChat arg2, EncryptedChat arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        SecretChatHelper.lambda$processUpdateEncryption$2(this.f$0, this.f$1, this.f$2);
    }
}

