package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_dialog;

public final class -$$Lambda$SecretChatHelper$vOnfmIg19WlGNOBT5lCtBdPmXEA implements Runnable {
    public -$$Lambda$SecretChatHelper$vOnfmIg19WlGNOBT5lCtBdPmXEA(SecretChatHelper arg1, TL_dialog arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SecretChatHelper.lambda$processUpdateEncryption$1(this.f$0, this.f$1);
    }
}

