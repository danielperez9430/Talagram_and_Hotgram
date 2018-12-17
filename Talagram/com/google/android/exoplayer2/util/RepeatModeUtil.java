package com.google.android.exoplayer2.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RepeatModeUtil {
    @Retention(value=RetentionPolicy.SOURCE) @public interface RepeatToggleModes {
    }

    public static final int REPEAT_TOGGLE_MODE_ALL = 2;
    public static final int REPEAT_TOGGLE_MODE_NONE = 0;
    public static final int REPEAT_TOGGLE_MODE_ONE = 1;

    private RepeatModeUtil() {
        super();
    }

    public static int getNextRepeatMode(int arg3, int arg4) {
        int v0;
        for(v0 = 1; v0 <= 2; ++v0) {
            int v1 = (arg3 + v0) % 3;
            if(RepeatModeUtil.isRepeatModeEnabled(v1, arg4)) {
                return v1;
            }
        }

        return arg3;
    }

    public static boolean isRepeatModeEnabled(int arg2, int arg3) {
        boolean v0 = false;
        switch(arg2) {
            case 0: {
                return 1;
            }
            case 1: {
                goto label_8;
            }
            case 2: {
                goto label_4;
            }
        }

        return 0;
    label_4:
        if((arg3 & 2) != 0) {
            v0 = true;
        }

        return v0;
    label_8:
        if((arg3 & 1) != 0) {
            v0 = true;
        }

        return v0;
    }
}

