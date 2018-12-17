package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;

public final class -$$Lambda$ImageLoader$2$-V5jPiyC2kg0zX7Y5l0pTebxVX4 implements Runnable {
    public -$$Lambda$ImageLoader$2$-V5jPiyC2kg0zX7Y5l0pTebxVX4(int arg1, String arg2, InputFile arg3, InputEncryptedFile arg4, byte[] arg5, byte[] arg6, long arg7) {
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
        org.telegram.messenger.ImageLoader$2.lambda$null$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

