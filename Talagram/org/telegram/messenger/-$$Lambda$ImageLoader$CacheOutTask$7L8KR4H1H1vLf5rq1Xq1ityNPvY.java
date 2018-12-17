package org.telegram.messenger;

import android.graphics.drawable.BitmapDrawable;

public final class -$$Lambda$ImageLoader$CacheOutTask$7L8KR4H1H1vLf5rq1Xq1ityNPvY implements Runnable {
    public -$$Lambda$ImageLoader$CacheOutTask$7L8KR4H1H1vLf5rq1Xq1ityNPvY(CacheOutTask arg1, BitmapDrawable arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        CacheOutTask.lambda$onPostExecute$1(this.f$0, this.f$1);
    }
}

