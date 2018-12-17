package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$DecryptedMessage;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$SecretChatHelper$go4ClJO8kMeuzFvRQpPn8-EmO40 implements Runnable {
    public -$$Lambda$SecretChatHelper$go4ClJO8kMeuzFvRQpPn8-EmO40(SecretChatHelper arg1, EncryptedChat arg2, DecryptedMessage arg3, Message arg4, InputEncryptedFile arg5, MessageObject arg6, String arg7) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
    }

    public final void run() {
        SecretChatHelper.lambda$performSendEncryptedRequest$7(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

