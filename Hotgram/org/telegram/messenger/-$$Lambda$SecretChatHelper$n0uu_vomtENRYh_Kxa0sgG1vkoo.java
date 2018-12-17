package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$SecretChatHelper$n0uu_vomtENRYh_Kxa0sgG1vkoo implements Runnable {
    public -$$Lambda$SecretChatHelper$n0uu_vomtENRYh_Kxa0sgG1vkoo(SecretChatHelper arg1, EncryptedChat arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SecretChatHelper.lambda$applyPeerLayer$8(this.f$0, this.f$1);
    }
}

