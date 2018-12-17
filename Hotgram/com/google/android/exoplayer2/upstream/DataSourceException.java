package com.google.android.exoplayer2.upstream;

import java.io.IOException;

public final class DataSourceException extends IOException {
    public static final int POSITION_OUT_OF_RANGE;
    public final int reason;

    public DataSourceException(int arg1) {
        super();
        this.reason = arg1;
    }
}

