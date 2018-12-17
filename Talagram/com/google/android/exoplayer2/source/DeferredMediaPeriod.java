package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import java.io.IOException;

public final class DeferredMediaPeriod implements Callback, MediaPeriod {
    public interface PrepareErrorListener {
        void onPrepareError(MediaPeriodId arg1, IOException arg2);
    }

    private final Allocator allocator;
    private Callback callback;
    public final MediaPeriodId id;
    private PrepareErrorListener listener;
    private MediaPeriod mediaPeriod;
    public final MediaSource mediaSource;
    private boolean notifiedPrepareError;
    private long preparePositionOverrideUs;
    private long preparePositionUs;

    public DeferredMediaPeriod(MediaSource arg1, MediaPeriodId arg2, Allocator arg3) {
        super();
        this.id = arg2;
        this.allocator = arg3;
        this.mediaSource = arg1;
        this.preparePositionOverrideUs = -9223372036854775807L;
    }

    public boolean continueLoading(long arg2) {
        boolean v2 = this.mediaPeriod == null || !this.mediaPeriod.continueLoading(arg2) ? false : true;
        return v2;
    }

    public void createPeriod(MediaPeriodId arg3) {
        this.mediaPeriod = this.mediaSource.createPeriod(arg3, this.allocator);
        if(this.callback != null) {
            this.mediaPeriod.prepare(((Callback)this), this.preparePositionUs);
        }
    }

    public void discardBuffer(long arg2, boolean arg4) {
        this.mediaPeriod.discardBuffer(arg2, arg4);
    }

    public long getAdjustedSeekPositionUs(long arg2, SeekParameters arg4) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(arg2, arg4);
    }

    public long getBufferedPositionUs() {
        return this.mediaPeriod.getBufferedPositionUs();
    }

    public long getNextLoadPositionUs() {
        return this.mediaPeriod.getNextLoadPositionUs();
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public void maybeThrowPrepareError() {
        try {
            if(this.mediaPeriod != null) {
                this.mediaPeriod.maybeThrowPrepareError();
                return;
            }

            this.mediaSource.maybeThrowSourceInfoRefreshError();
        }
        catch(IOException v0) {
            if(this.listener != null) {
                if(this.notifiedPrepareError) {
                    return;
                }

                this.notifiedPrepareError = true;
                this.listener.onPrepareError(this.id, v0);
                return;
            }

            throw v0;
        }
    }

    public void onContinueLoadingRequested(MediaPeriod arg1) {
        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
    }

    public void onContinueLoadingRequested(SequenceableLoader arg1) {
        this.onContinueLoadingRequested(((MediaPeriod)arg1));
    }

    public void onPrepared(MediaPeriod arg1) {
        this.callback.onPrepared(((MediaPeriod)this));
    }

    public void prepare(Callback arg1, long arg2) {
        this.callback = arg1;
        this.preparePositionUs = arg2;
        if(this.mediaPeriod != null) {
            this.mediaPeriod.prepare(((Callback)this), arg2);
        }
    }

    public long readDiscontinuity() {
        return this.mediaPeriod.readDiscontinuity();
    }

    public void reevaluateBuffer(long arg2) {
        this.mediaPeriod.reevaluateBuffer(arg2);
    }

    public void releasePeriod() {
        if(this.mediaPeriod != null) {
            this.mediaSource.releasePeriod(this.mediaPeriod);
        }
    }

    public long seekToUs(long arg2) {
        return this.mediaPeriod.seekToUs(arg2);
    }

    public long selectTracks(TrackSelection[] arg13, boolean[] arg14, SampleStream[] arg15, boolean[] arg16, long arg17) {
        long v10;
        DeferredMediaPeriod v0 = this;
        long v3 = -9223372036854775807L;
        if(v0.preparePositionOverrideUs == v3 || arg17 != 0) {
            v10 = arg17;
        }
        else {
            long v1 = v0.preparePositionOverrideUs;
            v0.preparePositionOverrideUs = v3;
            v10 = v1;
        }

        return v0.mediaPeriod.selectTracks(arg13, arg14, arg15, arg16, v10);
    }

    public void setDefaultPreparePositionUs(long arg6) {
        long v2 = 0;
        if(this.preparePositionUs == v2 && arg6 != v2) {
            this.preparePositionOverrideUs = arg6;
            this.preparePositionUs = arg6;
        }
    }

    public void setPrepareErrorListener(PrepareErrorListener arg1) {
        this.listener = arg1;
    }
}

