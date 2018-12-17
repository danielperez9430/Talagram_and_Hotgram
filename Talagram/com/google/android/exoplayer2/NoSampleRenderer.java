package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;

public abstract class NoSampleRenderer implements Renderer, RendererCapabilities {
    private RendererConfiguration configuration;
    private int index;
    private int state;
    private SampleStream stream;
    private boolean streamIsFinal;

    public NoSampleRenderer() {
        super();
    }

    public final void disable() {
        boolean v1 = true;
        if(this.state == 1) {
        }
        else {
            v1 = false;
        }

        Assertions.checkState(v1);
        this.state = 0;
        this.stream = null;
        this.streamIsFinal = false;
        this.onDisabled();
    }

    public final void enable(RendererConfiguration arg3, Format[] arg4, SampleStream arg5, long arg6, boolean arg8, long arg9) {
        boolean v0 = this.state == 0 ? true : false;
        Assertions.checkState(v0);
        this.configuration = arg3;
        this.state = 1;
        this.onEnabled(arg8);
        this.replaceStream(arg4, arg5, arg9);
        this.onPositionReset(arg6, arg8);
    }

    public final RendererCapabilities getCapabilities() {
        return this;
    }

    protected final RendererConfiguration getConfiguration() {
        return this.configuration;
    }

    protected final int getIndex() {
        return this.index;
    }

    public MediaClock getMediaClock() {
        return null;
    }

    public final int getState() {
        return this.state;
    }

    public final SampleStream getStream() {
        return this.stream;
    }

    public final int getTrackType() {
        return 5;
    }

    public void handleMessage(int arg1, Object arg2) {
    }

    public final boolean hasReadStreamToEnd() {
        return 1;
    }

    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public boolean isEnded() {
        return 1;
    }

    public boolean isReady() {
        return 1;
    }

    public final void maybeThrowStreamError() {
    }

    protected void onDisabled() {
    }

    protected void onEnabled(boolean arg1) {
    }

    protected void onPositionReset(long arg1, boolean arg3) {
    }

    protected void onRendererOffsetChanged(long arg1) {
    }

    protected void onStarted() {
    }

    protected void onStopped() {
    }

    public final void replaceStream(Format[] arg1, SampleStream arg2, long arg3) {
        Assertions.checkState(this.streamIsFinal ^ 1);
        this.stream = arg2;
        this.onRendererOffsetChanged(arg3);
    }

    public final void resetPosition(long arg2) {
        this.streamIsFinal = false;
        this.onPositionReset(arg2, false);
    }

    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    public final void setIndex(int arg1) {
        this.index = arg1;
    }

    public void setOperatingRate(float arg1) {
        Renderer$-CC.$default$setOperatingRate(((Renderer)this), arg1);
    }

    public final void start() {
        boolean v1 = true;
        if(this.state == 1) {
        }
        else {
            v1 = false;
        }

        Assertions.checkState(v1);
        this.state = 2;
        this.onStarted();
    }

    public final void stop() {
        boolean v0 = this.state == 2 ? true : false;
        Assertions.checkState(v0);
        this.state = 1;
        this.onStopped();
    }

    public int supportsFormat(Format arg1) {
        return 0;
    }

    public int supportsMixedMimeTypeAdaptation() {
        return 0;
    }
}

