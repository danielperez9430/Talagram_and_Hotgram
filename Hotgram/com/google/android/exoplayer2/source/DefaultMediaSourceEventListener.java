package com.google.android.exoplayer2.source;

import java.io.IOException;

public abstract class DefaultMediaSourceEventListener implements MediaSourceEventListener {
    public DefaultMediaSourceEventListener() {
        super();
    }

    public void onDownstreamFormatChanged(int arg1, MediaPeriodId arg2, MediaLoadData arg3) {
    }

    public void onLoadCanceled(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4) {
    }

    public void onLoadCompleted(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4) {
    }

    public void onLoadError(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4, IOException arg5, boolean arg6) {
    }

    public void onLoadStarted(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4) {
    }

    public void onMediaPeriodCreated(int arg1, MediaPeriodId arg2) {
    }

    public void onMediaPeriodReleased(int arg1, MediaPeriodId arg2) {
    }

    public void onReadingStarted(int arg1, MediaPeriodId arg2) {
    }

    public void onUpstreamDiscarded(int arg1, MediaPeriodId arg2, MediaLoadData arg3) {
    }
}

