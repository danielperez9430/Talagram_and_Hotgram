package com.google.android.exoplayer2.util;

import android.annotation.TargetApi;
import android.os.Trace;

public final class TraceUtil {
    private TraceUtil() {
        super();
    }

    public static void beginSection(String arg2) {
        if(Util.SDK_INT >= 18) {
            TraceUtil.beginSectionV18(arg2);
        }
    }

    @TargetApi(value=18) private static void beginSectionV18(String arg0) {
        Trace.beginSection(arg0);
    }

    public static void endSection() {
        if(Util.SDK_INT >= 18) {
            TraceUtil.endSectionV18();
        }
    }

    @TargetApi(value=18) private static void endSectionV18() {
        Trace.endSection();
    }
}

