package com.google.android.gms.location;

import java.util.Comparator;

final class zzh implements Comparator {
    zzh() {
        super();
    }

    public final int compare(Object arg3, Object arg4) {
        int v0 = Integer.valueOf(((DetectedActivity)arg4).getConfidence()).compareTo(Integer.valueOf(((DetectedActivity)arg3).getConfidence()));
        if(v0 == 0) {
            return Integer.valueOf(((DetectedActivity)arg3).getType()).compareTo(Integer.valueOf(((DetectedActivity)arg4).getType()));
        }

        return v0;
    }
}

