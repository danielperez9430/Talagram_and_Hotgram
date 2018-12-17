package com.google.android.exoplayer2.ext.opus;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;

public final class OpusLibrary {
    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.opus");
    }

    private OpusLibrary() {
        super();
    }

    public static String getVersion() {
        return OpusLibrary.opusGetVersion();
    }

    public static native String opusGetVersion() {
    }
}

