package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo$CodecCapabilities;

@TargetApi(value=16) public final class ay {
    public final String a;
    public final MediaCodecInfo$CodecCapabilities b;
    public final boolean c;

    ay(String arg1, MediaCodecInfo$CodecCapabilities arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = ay.a(arg2);
    }

    private static boolean a(MediaCodecInfo$CodecCapabilities arg2) {
        boolean v2 = arg2 == null || ft.a < 19 || !ay.b(arg2) ? false : true;
        return v2;
    }

    @TargetApi(value=19) private static boolean b(MediaCodecInfo$CodecCapabilities arg1) {
        return arg1.isFeatureSupported("adaptive-playback");
    }
}

