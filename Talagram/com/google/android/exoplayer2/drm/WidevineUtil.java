package com.google.android.exoplayer2.drm;

import android.util.Pair;
import java.util.Map;

public final class WidevineUtil {
    public static final String PROPERTY_LICENSE_DURATION_REMAINING = "LicenseDurationRemaining";
    public static final String PROPERTY_PLAYBACK_DURATION_REMAINING = "PlaybackDurationRemaining";

    private WidevineUtil() {
        super();
    }

    private static long getDurationRemainingSec(Map arg0, String arg1) {
        if(arg0 != null) {
            try {
                Object v0 = arg0.get(arg1);
                if(v0 != null) {
                    return Long.parseLong(((String)v0));
                }

                return -9223372036854775807L;
            }
            catch(NumberFormatException ) {
                return -9223372036854775807L;
            }
        }

        return -9223372036854775807L;
    }

    public static Pair getLicenseDurationRemainingSec(DrmSession arg4) {
        Map v4 = arg4.queryKeyStatus();
        if(v4 == null) {
            return null;
        }

        return new Pair(Long.valueOf(WidevineUtil.getDurationRemainingSec(v4, "LicenseDurationRemaining")), Long.valueOf(WidevineUtil.getDurationRemainingSec(v4, "PlaybackDurationRemaining")));
    }
}

