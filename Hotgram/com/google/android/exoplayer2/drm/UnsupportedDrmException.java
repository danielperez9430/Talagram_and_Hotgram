package com.google.android.exoplayer2.drm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class UnsupportedDrmException extends Exception {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Reason {
    }

    public static final int REASON_INSTANTIATION_ERROR = 2;
    public static final int REASON_UNSUPPORTED_SCHEME = 1;
    public final int reason;

    public UnsupportedDrmException(int arg1) {
        super();
        this.reason = arg1;
    }

    public UnsupportedDrmException(int arg1, Exception arg2) {
        super(((Throwable)arg2));
        this.reason = arg1;
    }
}

