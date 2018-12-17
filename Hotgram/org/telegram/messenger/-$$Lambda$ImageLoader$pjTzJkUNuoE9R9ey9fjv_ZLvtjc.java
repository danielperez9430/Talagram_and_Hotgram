package org.telegram.messenger;

import java.io.File;

public final class -$$Lambda$ImageLoader$pjTzJkUNuoE9R9ey9fjv_ZLvtjc implements Runnable {
    public -$$Lambda$ImageLoader$pjTzJkUNuoE9R9ey9fjv_ZLvtjc(ImageLoader arg1, String arg2, int arg3, File arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        ImageLoader.lambda$fileDidLoaded$7(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

