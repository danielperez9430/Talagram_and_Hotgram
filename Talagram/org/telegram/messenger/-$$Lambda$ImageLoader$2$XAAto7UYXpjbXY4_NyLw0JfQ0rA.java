package org.telegram.messenger;

import java.io.File;

public final class -$$Lambda$ImageLoader$2$XAAto7UYXpjbXY4_NyLw0JfQ0rA implements Runnable {
    public -$$Lambda$ImageLoader$2$XAAto7UYXpjbXY4_NyLw0JfQ0rA(org.telegram.messenger.ImageLoader$2 arg1, File arg2, String arg3, int arg4, int arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        org.telegram.messenger.ImageLoader$2.lambda$fileDidLoaded$5(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

