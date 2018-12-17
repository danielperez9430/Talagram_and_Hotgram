package org.telegram.messenger;

import android.graphics.drawable.BitmapDrawable;
import java.util.ArrayList;

public final class -$$Lambda$ImageLoader$CacheImage$-4d24Wn72OGrdz-PXIizp3MnMzk implements Runnable {
    public -$$Lambda$ImageLoader$CacheImage$-4d24Wn72OGrdz-PXIizp3MnMzk(CacheImage arg1, BitmapDrawable arg2, ArrayList arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        CacheImage.lambda$setImageAndClear$0(this.f$0, this.f$1, this.f$2);
    }
}

