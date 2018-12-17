package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

@TargetApi(value=16) public interface DrmSession {
    public class DrmSessionException extends Exception {
        public DrmSessionException(Throwable arg1) {
            super(arg1);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface State {
    }

    public static final int STATE_ERROR = 1;
    public static final int STATE_OPENED = 3;
    public static final int STATE_OPENED_WITH_KEYS = 4;
    public static final int STATE_OPENING = 2;
    public static final int STATE_RELEASED;

    DrmSessionException getError();

    ExoMediaCrypto getMediaCrypto();

    byte[] getOfflineLicenseKeySetId();

    int getState();

    Map queryKeyStatus();
}

