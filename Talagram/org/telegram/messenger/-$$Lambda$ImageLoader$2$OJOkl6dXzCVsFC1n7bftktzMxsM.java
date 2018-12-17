package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;

public final class -$$Lambda$ImageLoader$2$OJOkl6dXzCVsFC1n7bftktzMxsM implements Runnable {
    public -$$Lambda$ImageLoader$2$OJOkl6dXzCVsFC1n7bftktzMxsM(org.telegram.messenger.ImageLoader$2 arg1, int arg2, String arg3, InputFile arg4, InputEncryptedFile arg5, byte[] arg6, byte[] arg7, long arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
        this.f$7 = arg8;
    }

    public final void run() {
        org.telegram.messenger.ImageLoader$2.lambda$fileDidUploaded$2(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}

