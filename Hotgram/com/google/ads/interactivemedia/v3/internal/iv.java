package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;

public class iv extends ji {
    protected final ContentProgressProvider a;

    public iv(ContentProgressProvider arg1, long arg2) {
        super(arg2);
        this.a = arg1;
    }

    public VideoProgressUpdate a() {
        VideoProgressUpdate v0 = this.a.getContentProgress();
        if(v0 == null) {
            Log.w("IMASDK", "ContentProgressProvider.getContentProgress() is null. Use VideoProgressUpdate.VIDEO_TIME_NOT_READY instead.");
            v0 = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }

        return v0;
    }
}

