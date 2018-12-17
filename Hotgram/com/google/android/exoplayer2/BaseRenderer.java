package com.google.android.exoplayer2;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;

public abstract class BaseRenderer implements Renderer, RendererCapabilities {
    private RendererConfiguration configuration;
    private int index;
    private boolean readEndOfStream;
    private int state;
    private SampleStream stream;
    private Format[] streamFormats;
    private boolean streamIsFinal;
    private long streamOffsetUs;
    private final int trackType;

    public BaseRenderer(int arg1) {
        super();
        this.trackType = arg1;
        this.readEndOfStream = true;
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
        this.streamFormats = null;
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

    protected final Format[] getStreamFormats() {
        return this.streamFormats;
    }

    public final int getTrackType() {
        return this.trackType;
    }

    public void handleMessage(int arg1, Object arg2) {
    }

    public final boolean hasReadStreamToEnd() {
        return this.readEndOfStream;
    }

    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    protected final boolean isSourceReady() {
        boolean v0 = this.readEndOfStream ? this.streamIsFinal : this.stream.isReady();
        return v0;
    }

    public final void maybeThrowStreamError() {
        this.stream.maybeThrowError();
    }

    protected void onDisabled() {
    }

    protected void onEnabled(boolean arg1) {
    }

    protected void onPositionReset(long arg1, boolean arg3) {
    }

    protected void onStarted() {
    }

    protected void onStopped() {
    }

    protected void onStreamChanged(Format[] arg1, long arg2) {
    }

    protected final int readSource(FormatHolder arg6, DecoderInputBuffer arg7, boolean arg8) {
        int v8 = this.stream.readData(arg6, arg7, arg8);
        int v0 = -4;
        if(v8 != v0) {
            if(v8 != -5) {
                return v8;
            }

            Format v7 = arg6.format;
            if(v7.subsampleOffsetUs == 9223372036854775807L) {
                return v8;
            }

            arg6.format = v7.copyWithSubsampleOffsetUs(v7.subsampleOffsetUs + this.streamOffsetUs);
        }
        else if(arg7.isEndOfStream()) {
            this.readEndOfStream = true;
            if(this.streamIsFinal) {
            }
            else {
                v0 = -3;
            }

            return v0;
        }
        else {
            arg7.timeUs += this.streamOffsetUs;
        }

        return v8;
    }

    public final void replaceStream(Format[] arg2, SampleStream arg3, long arg4) {
        Assertions.checkState(this.streamIsFinal ^ 1);
        this.stream = arg3;
        this.readEndOfStream = false;
        this.streamFormats = arg2;
        this.streamOffsetUs = arg4;
        this.onStreamChanged(arg2, arg4);
    }

    public final void resetPosition(long arg2) {
        this.streamIsFinal = false;
        this.readEndOfStream = false;
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

    protected int skipSource(long arg4) {
        return this.stream.skipData(arg4 - this.streamOffsetUs);
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

    protected static boolean supportsFormatDrm(DrmSessionManager arg0, DrmInitData arg1) {
        if(arg1 == null) {
            return 1;
        }

        if(arg0 == null) {
            return 0;
        }

        return arg0.canAcquireSession(arg1);
    }

    public int supportsMixedMimeTypeAdaptation() {
        return 0;
    }
}

