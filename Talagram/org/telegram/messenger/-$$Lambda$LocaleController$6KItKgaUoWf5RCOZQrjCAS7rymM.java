package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_langPackDifference;

public final class -$$Lambda$LocaleController$6KItKgaUoWf5RCOZQrjCAS7rymM implements Runnable {
    public -$$Lambda$LocaleController$6KItKgaUoWf5RCOZQrjCAS7rymM(LocaleController arg1, String arg2, TL_langPackDifference arg3, HashMap arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        LocaleController.lambda$saveRemoteLocaleStrings$3(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

