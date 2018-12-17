package org.telegram.ui;

import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$PhotoSize;

public final class -$$Lambda$ChannelEditInfoActivity$OmA0wWcVOlhJgDZghYHhf4k1sWk implements Runnable {
    public -$$Lambda$ChannelEditInfoActivity$OmA0wWcVOlhJgDZghYHhf4k1sWk(ChannelEditInfoActivity arg1, InputFile arg2, PhotoSize arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        ChannelEditInfoActivity.lambda$didUploadedPhoto$15(this.f$0, this.f$1, this.f$2);
    }
}

