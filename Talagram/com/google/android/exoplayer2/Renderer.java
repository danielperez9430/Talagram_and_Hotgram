package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.MediaClock;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Renderer extends Target {
    @Retention(value=RetentionPolicy.SOURCE) @public interface State {
    }

    public static final int STATE_DISABLED = 0;
    public static final int STATE_ENABLED = 1;
    public static final int STATE_STARTED = 2;

    void disable();

    void enable(RendererConfiguration arg1, Format[] arg2, SampleStream arg3, long arg4, boolean arg5, long arg6);

    RendererCapabilities getCapabilities();

    MediaClock getMediaClock();

    int getState();

    SampleStream getStream();

    int getTrackType();

    boolean hasReadStreamToEnd();

    boolean isCurrentStreamFinal();

    boolean isEnded();

    boolean isReady();

    void maybeThrowStreamError();

    void render(long arg1, long arg2);

    void replaceStream(Format[] arg1, SampleStream arg2, long arg3);

    void resetPosition(long arg1);

    void setCurrentStreamFinal();

    void setIndex(int arg1);

    void setOperatingRate(float arg1);

    void start();

    void stop();
}

