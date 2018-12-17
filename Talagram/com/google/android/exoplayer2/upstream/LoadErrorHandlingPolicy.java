package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.ParserException;
import java.io.IOException;

public interface LoadErrorHandlingPolicy {
    final class com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$1 implements LoadErrorHandlingPolicy {
        com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$1() {
            super();
        }

        public long getBlacklistDurationMsFor(Loadable arg1, long arg2, IOException arg4, int arg5) {
            arg2 = -9223372036854775807L;
            if((arg4 instanceof InvalidResponseCodeException)) {
                int v1 = ((InvalidResponseCodeException)arg4).responseCode;
                if(v1 != 404 && v1 != 410) {
                    return arg2;
                }

                arg2 = 60000;
            }

            return arg2;
        }

        public int getMinimumLoadableRetryCount(Loadable arg1) {
            return 3;
        }

        public long getRetryDelayMsFor(Loadable arg1, long arg2, IOException arg4, int arg5) {
            long v1 = (arg4 instanceof ParserException) ? -9223372036854775807L : ((long)Math.min((arg5 - 1) * 1000, 5000));
            return v1;
        }
    }

    public static final LoadErrorHandlingPolicy DEFAULT = null;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;

    static {
        LoadErrorHandlingPolicy.DEFAULT = new com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$1();
    }

    long getBlacklistDurationMsFor(Loadable arg1, long arg2, IOException arg3, int arg4);

    int getMinimumLoadableRetryCount(Loadable arg1);

    long getRetryDelayMsFor(Loadable arg1, long arg2, IOException arg3, int arg4);
}

