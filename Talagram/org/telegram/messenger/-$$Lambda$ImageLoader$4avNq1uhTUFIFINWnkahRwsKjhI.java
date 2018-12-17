package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$FileLocation;

public final class -$$Lambda$ImageLoader$4avNq1uhTUFIFINWnkahRwsKjhI implements Runnable {
    public -$$Lambda$ImageLoader$4avNq1uhTUFIFINWnkahRwsKjhI(ImageLoader arg1, String arg2, String arg3, FileLocation arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        ImageLoader.lambda$replaceImageInCache$3(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

